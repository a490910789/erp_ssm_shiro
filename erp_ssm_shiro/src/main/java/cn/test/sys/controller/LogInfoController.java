package cn.test.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.sys.service.LogInfoService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.LogInfoVo;

@Controller
@RequestMapping("logInfo")
public class LogInfoController {

	@Autowired
	private LogInfoService logInfoService;

	@RequestMapping("toLogInfoManager")
	public String toLogInfoManager() {
		return "system/logInfo/logInfoManager";
	}

	@RequestMapping("loadAllLogInfo")
	@ResponseBody
	public DataGridView loadAllLogInfo(LogInfoVo logInfoVo) {
		return this.logInfoService.queryAllLogInfo(logInfoVo);
	}

	@RequestMapping("delLogInfos")
	@ResponseBody
	public Map<String, Object> delLogInfos(LogInfoVo logInfoVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.logInfoService.delLogInfos(logInfoVo);
			if (i > 0) {
				msg = "删除成功";
			} else {
				msg = "删除失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping("delLogInfo")
	@ResponseBody
	public Map<String, Object> delLogInfo(LogInfoVo logInfoVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.logInfoService.delLogInfo(logInfoVo);
			if (i > 0) {
				msg = "删除成功";
			} else {
				msg = "删除失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		map.put("msg", msg);
		return map;
	}
}
