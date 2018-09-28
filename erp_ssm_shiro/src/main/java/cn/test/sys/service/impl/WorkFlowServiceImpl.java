package cn.test.sys.service.impl;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.LeaveBill;
import cn.test.sys.domain.User;
import cn.test.sys.mapper.LeaveBillMapper;
import cn.test.sys.service.WorkFlowService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.utils.SessionUtils;
import cn.test.sys.vo.WorkFlowVo;
import cn.test.sys.vo.act.ActCommentEntity;
import cn.test.sys.vo.act.ActDeploymentEntity;
import cn.test.sys.vo.act.ActHisTaskEntity;
import cn.test.sys.vo.act.ActProcessDefEntity;
import cn.test.sys.vo.act.ActTaskEntity;

@Service
public class WorkFlowServiceImpl implements WorkFlowService {
	@Autowired
	private LeaveBillMapper leaveBillMapper;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private IdentityService IdentityService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private FormService formService;
	@Autowired
	private ManagementService managementService;
    //流程部署
	@Override
	public void addDeployment(WorkFlowVo workFlowVo, InputStream inputStream) {
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		this.repositoryService.createDeployment().name(workFlowVo.getDeploymentName()).addZipInputStream(zipInputStream)
				.deploy();
	}
    //删除流程部署
	@Override
	public void delDeployment(WorkFlowVo workFlowVo) {
		this.repositoryService.deleteDeployment(workFlowVo.getDeploymentId());
	}

	// 查询流程部署 +模糊
	@Override
	public DataGridView loadAllProcessDeployment(WorkFlowVo workFlowVo) {
		String deploymentName = workFlowVo.getDeploymentName();
		if (null == deploymentName) {
			deploymentName = "";
		}
		long count = this.repositoryService.createDeploymentQuery().deploymentNameLike("%" + deploymentName + "%")
				.count();
		List<Deployment> list = this.repositoryService.createDeploymentQuery()
				.deploymentNameLike("%" + deploymentName + "%")
				.listPage((workFlowVo.getPage() - 1) * workFlowVo.getLimit(), workFlowVo.getLimit());
		List<ActDeploymentEntity> data = new ArrayList<>();
		for (Deployment deployment : list) {
			ActDeploymentEntity dp = new ActDeploymentEntity();
			BeanUtils.copyProperties(deployment, dp);
			data.add(dp);
		}
		return new DataGridView(count, data);
	}
    //查询所有流程定义
	@Override
	public DataGridView loadAllProcessDef(WorkFlowVo workFlowVo) {
		String deploymentName = workFlowVo.getDeploymentName();
		if (null == deploymentName) {
			deploymentName = "";
		}
		List<Deployment> list = this.repositoryService.createDeploymentQuery()
				.deploymentNameLike("%" + deploymentName + "%").list();
		if (list.size() == 0) {
			return new DataGridView(0L, null);
		}
		Set<String> deploymentIds = new HashSet<>();
		for (Deployment deployment : list) {
			deploymentIds.add(deployment.getId());
		}
		long count = this.repositoryService.createProcessDefinitionQuery().deploymentIds(deploymentIds).count();
		List<ProcessDefinition> processDefinitions = this.repositoryService.createProcessDefinitionQuery()
				.deploymentIds(deploymentIds)
				.listPage((workFlowVo.getPage() - 1) * workFlowVo.getLimit(), workFlowVo.getLimit());
		List<ActProcessDefEntity> data = new ArrayList<>();
		for (ProcessDefinition processDefinition : processDefinitions) {
			ActProcessDefEntity pd = new ActProcessDefEntity();
			BeanUtils.copyProperties(processDefinition, pd);
			data.add(pd);
		}
		return new DataGridView(count, data);
	}

	/*
	 * @Override public String CatProcessImg(String deploymentId,String path) {
	 * ProcessDefinition processDefinition =
	 * this.repositoryService.createProcessDefinitionQuery()
	 * .deploymentId(deploymentId).singleResult(); String diagramResourceName =
	 * processDefinition.getDiagramResourceName(); InputStream inputStream =
	 * repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
	 * File f = new File(path+"/"+diagramResourceName); if(f.exists()) { return
	 * diagramResourceName; } OutputStream os = null; try { os = new
	 * FileOutputStream(f); FileCopyUtils.copy(inputStream, os); } catch (Exception
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } return
	 * diagramResourceName; }
	 */
	// 提交请假单 开启流程
	@Override
	public void submitApply(Integer id) {
		String processDefinitionKey = LeaveBill.class.getSimpleName();
		String businessKey = processDefinitionKey + "_" + id;
		User user = SessionUtils.getUserInSession("user");
		Map<String, Object> variables = new HashMap<>();
		variables.put("username", user.getName());
		this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
		LeaveBill record = this.leaveBillMapper.selectByPrimaryKey(id);
		record.setState(String.valueOf(SYS_Constast.TYPE_PUBLIC_ONE));
		this.leaveBillMapper.updateByPrimaryKeySelective(record);
	}

	// 查询待办任务
	@Override
	public DataGridView loadMyTasks(WorkFlowVo workFlowVo) {
		String assignee = SessionUtils.getUserNameInSession("user");
		long count = this.taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().count();
		List<Task> list = this.taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc()
				.listPage((workFlowVo.getPage() - 1) * workFlowVo.getLimit(), workFlowVo.getLimit());
		List<ActTaskEntity> data = new ArrayList<>();
		for (Task task : list) {
			ActTaskEntity entity = new ActTaskEntity();
			BeanUtils.copyProperties(task, entity);
			data.add(entity);
		}
		return new DataGridView(count, data);
	}

	// 根据任务ID查询请假单ID
	@Override
	public LeaveBill queryLeaveBillByTaskId(String taskId) {
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 得到流程定义ID
		String processInstanceId = task.getProcessInstanceId();
		// 根据流程定义ID查询流程
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		// 拿到业务ID
		String businessKey = processInstance.getBusinessKey();
		// 根据业务ID拿到请假单ID
		String leaveBillId = businessKey.split("_")[1];
		// 根据请假单ID查询请假单
		LeaveBill leaveBill = this.leaveBillMapper.selectByPrimaryKey(Integer.valueOf(leaveBillId));
		return leaveBill;
	}

	// 根据历史任务ID查询对应请假单信息
	@Override
	public LeaveBill queryLeaveBillByHisTaskId(String taskId) {
		HistoricTaskInstance hisTask = this.historyService.createHistoricTaskInstanceQuery().taskId(taskId)
				.singleResult();
		// 得到流程实例ID
		String processInstanceId = hisTask.getProcessInstanceId();
		// 根据流程实例ID查询流程
		HistoricProcessInstance processInstance = this.historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		// 拿到业务ID
		String businessKey = processInstance.getBusinessKey();
		// 根据业务ID拿到请假单ID
		String leaveBillId = businessKey.split("_")[1];
		// 根据请假单ID查询请假单
		LeaveBill leaveBill = this.leaveBillMapper.selectByPrimaryKey(Integer.valueOf(leaveBillId));
		return leaveBill;
	}

	// 根据任务ID查询当前流程批注
	@Override
	public DataGridView loadCommentsByTaskId(String taskId) {
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 拿到流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 根据流程实例ID 查询当前流程中产生的所有批注信息
		List<Comment> comments = this.taskService.getProcessInstanceComments(processInstanceId);
		List<ActCommentEntity> entities = new ArrayList<>();
		for (Comment comment : comments) {
			ActCommentEntity entity = new ActCommentEntity();
			BeanUtils.copyProperties(comment, entity);
			entities.add(entity);
		}
		return new DataGridView(Long.valueOf(entities.size()), entities);
	}

	// 根据任务ID查询按钮集合
	@Override
	public List<String> queryOutcomesByTaskId(String taskId) {
		// 定义按钮集合
		List<String> outcomes = new ArrayList<>();
		// 根据任务ID查询任务对象
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 得到流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 取出当前活动ID
		String taskDefinitionKey = task.getTaskDefinitionKey();
		// 根据流程定义ID查询流程定义对象 XML里面的数据
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		// 使用活动ID从流程定义里查询节点
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(taskDefinitionKey);
		// 取出当前活动节点中的连线信息
		List<PvmTransition> outgoingTransitions = activityImpl.getOutgoingTransitions();
		if (null != outgoingTransitions && outgoingTransitions.size() > 0) {
			for (PvmTransition pvmTransition : outgoingTransitions) {
				Object name = pvmTransition.getProperty("name");
				outcomes.add(name.toString());
			}
		}
		return outcomes;
	}

	// 完成任务
	@Override
	public void doTask(WorkFlowVo workFlowVo) {
		// 得到taskID
		String taskId = workFlowVo.getTaskId();
		// 得到请假单ID
		Integer id = workFlowVo.getId();
		// 得到outcome
		String outcome = workFlowVo.getOutcome();
		// 得到批注信息
		String comment = workFlowVo.getComment();
		// 设置流程走向
		Map<String, Object> variables = new HashMap<>();
		variables.put("outcome", outcome);
		// 得到流程实例ID
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		// 得到当前用户
		String username = SessionUtils.getUserNameInSession("user");
		// 设置当前线程用户的局部变量
		Authentication.setAuthenticatedUserId(username);
		// 设置批注信息
		this.taskService.addComment(taskId, processInstanceId, "[" + outcome + "]" + comment);
		// 完成任务
		this.taskService.complete(taskId, variables);
		// 查看历史流程是否结束
		HistoricProcessInstance processInstance = this.historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		// 得到当前请假单 设置请假单状态
		LeaveBill leaveBill = this.leaveBillMapper.selectByPrimaryKey(id);
		if (null == processInstance && outcome.equals("放弃")) {
			leaveBill.setState(String.valueOf(SYS_Constast.TYPE_LEAVEBILL_STATE_THREE));
		} else {
			leaveBill.setState(String.valueOf(SYS_Constast.TYPE_LEAVEBILL_STATE_TOW));
		}
		this.leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
	}

	// 查看流程图
	@Override
	public InputStream viewProcessImage(String deploymentId) {
		// 获取流程定义对象
		ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
				.deploymentId(deploymentId).singleResult();
		String diagramResourceName = processDefinition.getDiagramResourceName();
		// 返回一个输入流
		InputStream in = this.repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
		return in;
	}

	// 根据任务ID查询部署ID
	@Override
	public String queryDeploymentIdByTaskId(String taskId) {
		// 查询任务对象
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 得到流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 查询流程定义
		ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		// 得到部署ID
		String deploymentId = processDefinition.getDeploymentId();
		return deploymentId;
	}

	// 获取当前活动节点坐标宽高
	@Override
	public Map<String, Object> queryCoordinateByTaskId(String taskId) {
		// 根据任务ID查询任务对象
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 得到流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 取出当前活动ID
		String taskDefinitionKey = task.getTaskDefinitionKey();
		// 根据流程定义ID查询流程定义对象 XML里面的数据
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		// 使用活动ID从流程定义里查询节点
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(taskDefinitionKey);
		int x = activityImpl.getX();
		int y = activityImpl.getY();
		int width = activityImpl.getWidth();
		int height = activityImpl.getHeight();
		Map<String, Object> coordinate = new HashMap<>();
		coordinate.put("x", x);
		coordinate.put("y", y);
		coordinate.put("width", width);
		coordinate.put("height", height);
		return coordinate;
	}

	// 根据请假单查询任务ID
	@Override
	public DataGridView loadCommentsByLeaveBillId(Integer id) {
		// 组装业务ID
		String simpleName = LeaveBill.class.getSimpleName();
		String businessKey = simpleName + "_" + id;
		// 得到历史流程实例
		HistoricProcessInstance historicProcessInstance = this.historyService.createHistoricProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey).singleResult();
		// 根据流程实例id查询历史批注信息
		String processInstanceId = historicProcessInstance.getId();
		List<Comment> comments = this.taskService.getProcessInstanceComments(processInstanceId);
		List<ActCommentEntity> entities = new ArrayList<>();
		for (Comment comment : comments) {
			ActCommentEntity entity = new ActCommentEntity();
			BeanUtils.copyProperties(comment, entity);
			entities.add(entity);
		}
		return new DataGridView(Long.valueOf(entities.size()), entities);
	}

	@Override
	public DataGridView loadMyHisTasks(WorkFlowVo workFlowVo) {
		// 得到当前用户
		String assignee = SessionUtils.getUserNameInSession("user");
		long count = this.historyService.createHistoricTaskInstanceQuery().taskAssignee(assignee).count();
		// 查询当前用户历史办理任务
		List<HistoricTaskInstance> historicTaskInstances = this.historyService.createHistoricTaskInstanceQuery()
				.taskAssignee(assignee).orderByHistoricTaskInstanceEndTime().desc()
				.listPage((workFlowVo.getPage() - 1) * workFlowVo.getLimit(), workFlowVo.getLimit());
		List<ActHisTaskEntity> entitys = new ArrayList<>();
		for (HistoricTaskInstance actHisTaskEntity : historicTaskInstances) {
			ActHisTaskEntity entity = new ActHisTaskEntity();
			BeanUtils.copyProperties(actHisTaskEntity, entity);
			entitys.add(entity);
		}
		return new DataGridView(count, entitys);
	}
}
