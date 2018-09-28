package cn.test.sys.controller;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.test.sys.domain.LeaveBill;
import cn.test.sys.service.LeaveBillService;
import cn.test.sys.service.WorkFlowService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.WorkFlowVo;

@Controller
@RequestMapping("workFlow")
public class WorkFlowController {

	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private  LeaveBillService leaveBillService;
    //跳转部署管理
	@RequestMapping("toWorkFlowManager")
	public String toWorkFlowManager() {
		return "system/workFlow/workFlowManager";
	}
    //跳转添加页面
	@RequestMapping("toAddDeployment")
	public String toAddDeployment() {
		return "system/workFlow/addWorkFlow";
	}
	
    //跳转查询代办任务页面
	@RequestMapping("toTaskManager")
	public String toTaskManager(WorkFlowVo workFlowVo) {
		return "system/workFlow/taskManager";
	}
	

    //跳转办理任务页面
	@RequestMapping("toDoTask")
	public String toDoTask(WorkFlowVo workFlowVo,Model model) {
		//根据任务ID查询请假单ID
		LeaveBill leaveBill = this.workFlowService.queryLeaveBillByTaskId(workFlowVo.getTaskId());
		//根据任务ID查询按钮集合
		List<String> outcomes=this.workFlowService.queryOutcomesByTaskId(workFlowVo.getTaskId());
		model.addAttribute("leaveBill", leaveBill);
		model.addAttribute("outcomes", outcomes);
		return "system/workFlow/doTask";
	}
	
	 //查询个人待办任务
	@RequestMapping("loadMyTasks")
	@ResponseBody
	public DataGridView loadMyTasks(WorkFlowVo  workFlowVo) {
		return this.workFlowService.loadMyTasks(workFlowVo);
	}
	//查询任务批注
	@RequestMapping("loadCommentsByTaskId")
	@ResponseBody
	public DataGridView loadCommentsByTaskId(WorkFlowVo  workFlowVo) {
		return this.workFlowService.loadCommentsByTaskId(workFlowVo.getTaskId());
	}
	// 查询所有部署 分页 模糊
	@RequestMapping("loadAllProcessDeployment")
	@ResponseBody
	public DataGridView loadAllProcessDeployment(WorkFlowVo workFlowVo) {
		return this.workFlowService.loadAllProcessDeployment(workFlowVo);
	}
	// 查询所有流程定义 分页 模糊
	@RequestMapping("loadAllProcessDef")
	@ResponseBody
	public DataGridView loadAllProcessDef(WorkFlowVo workFlowVo) {
		return this.workFlowService.loadAllProcessDef(workFlowVo);
	}
	//跳转到查看流程图页面
	@RequestMapping("toViewProcessImage")
	public String toViewProcessImage(WorkFlowVo workFlowVo) {
		return "system/workFlow/viewProcessImage";
	}
	//跳转审批查询页面
	@RequestMapping("toViewApprovals")
	public String toViewApprovals(WorkFlowVo workFlowVo,Model model) {
		LeaveBill leaveBill = this.leaveBillService.queryLeaveBillById(workFlowVo.getId());
		model.addAttribute("leaveBill", leaveBill);
		return "system/workFlow/viewApprovals";
	}
	//跳转审批查询页面
	@RequestMapping("loadCommentsByLeaveBillId")
	@ResponseBody
	public DataGridView loadCommentsByLeaveBillId(WorkFlowVo workFlowVo) {
		return this.workFlowService.loadCommentsByLeaveBillId(workFlowVo.getId());
	}
	
	 //查询个人待办任务
	@RequestMapping("loadMyHisTasks")
	@ResponseBody
	public DataGridView loadMyHisTasks(WorkFlowVo  workFlowVo) {
		return this.workFlowService.loadMyHisTasks(workFlowVo);
	}
	//跳转历史流程记录页面
	@RequestMapping("toViewHisProcess")
	public String toViewHisProcess(WorkFlowVo workFlowVo) {
		return "system/workFlow/viewHisProcess";
	}
	//跳转历史办理任务页面
	@RequestMapping("toViewHisTask")
	public String toViewHisTask(WorkFlowVo workFlowVo) {
		return "system/workFlow/viewHisTask";
	}
	//跳转任务详情页面
	@RequestMapping("toViewHisTaskInfo")
	public String toViewHisTaskInfo(WorkFlowVo workFlowVo,Model model) {
	//根据任务ID查询请假单ID
	LeaveBill leaveBill = this.workFlowService.queryLeaveBillByHisTaskId(workFlowVo.getTaskId());
	model.addAttribute("leaveBill", leaveBill);
	return "system/workFlow/viewHisTaskInfo";
	}
	//跳转到查看流程图页面
	@RequestMapping("toViewProcessImageForTask")
	public String toViewProcessImageForTask(WorkFlowVo workFlowVo,Model model) {
		//通过任务ID查询流程部署ID
		String deploymentId=this.workFlowService.queryDeploymentIdByTaskId(workFlowVo.getTaskId());
		//给vo赋值部署ID
		workFlowVo.setDeploymentId(deploymentId);
		//根据任务ID查询当前活动节点坐标
		Map<String,Object> coordinate=this.workFlowService.queryCoordinateByTaskId(workFlowVo.getTaskId());
		model.addAttribute("c", coordinate);
		return "system/workFlow/viewProcessImage";
	}
	// 查询流程图
	@RequestMapping("viewProcessImage")
	public void viewProcessImage(WorkFlowVo workFlowVo,HttpServletResponse response) {
		InputStream in=this.workFlowService.viewProcessImage(workFlowVo.getDeploymentId());
		try {
			BufferedImage image=ImageIO.read(in);
			ImageIO.write(image, "PNG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 添加部署
	@RequestMapping("doTask")
	@ResponseBody
	public Map<String, Object> doTask(WorkFlowVo workFlowVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			this.workFlowService.doTask(workFlowVo);
				msg = "任务完成成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "任务完成失败";
		}
		map.put("msg", msg);
		return map;
	}
	
	
	// 删除单个部署
	@RequestMapping("delDeployment")
	@ResponseBody
	public Map<String, Object> delDeployment(WorkFlowVo workFlowVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
		 this.workFlowService.delDeployment(workFlowVo);
				msg = "删除成功";

		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		map.put("msg", msg);
		return map;
	}

	// 添加部署
	@RequestMapping("addDeployment")
	@ResponseBody
	public Map<String, Object> addDeployment(MultipartFile mf,WorkFlowVo workFlowVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			InputStream inputStream = mf.getInputStream();
			this.workFlowService.addDeployment(workFlowVo,inputStream);
				msg = "添加成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		map.put("msg", msg);
		return map;
	}
	
	// 提交请假单 开启请假流程
		@RequestMapping("submitApply")
		@ResponseBody
		public Map<String, Object> submitApply(WorkFlowVo workFlowVo) {
			String msg = "";
			Map<String, Object> map = new HashMap<>();
			try {
				this.workFlowService.submitApply(workFlowVo.getId());
					msg = "提交成功";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "提交失败";
			}
			map.put("msg", msg);
			return map;
		}

}
