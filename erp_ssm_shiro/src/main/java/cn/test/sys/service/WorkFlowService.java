package cn.test.sys.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.test.sys.domain.LeaveBill;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.WorkFlowVo;

public interface WorkFlowService {

	DataGridView loadAllProcessDeployment(WorkFlowVo workFlowVo);

	DataGridView loadAllProcessDef(WorkFlowVo workFlowVo);

	void addDeployment(WorkFlowVo workFlowVo, InputStream inputStream);

	void delDeployment(WorkFlowVo workFlowVo);

	void submitApply(Integer id);

	DataGridView loadMyTasks(WorkFlowVo workFlowVo);

	LeaveBill queryLeaveBillByTaskId(String taskId);

	DataGridView loadCommentsByTaskId(String taskId);

	List<String> queryOutcomesByTaskId(String taskId);

	void doTask(WorkFlowVo workFlowVo);

	InputStream viewProcessImage(String deploymentId);

	String queryDeploymentIdByTaskId(String taskId);

	Map<String, Object> queryCoordinateByTaskId(String taskId);

	DataGridView loadCommentsByLeaveBillId(Integer id);

	DataGridView loadMyHisTasks(WorkFlowVo workFlowVo);

	LeaveBill queryLeaveBillByHisTaskId(String taskId);
}
