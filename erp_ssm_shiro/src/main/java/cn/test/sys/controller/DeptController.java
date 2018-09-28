package cn.test.sys.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.Dept;
import cn.test.sys.service.DeptService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.utils.TreeNode;
import cn.test.sys.vo.DeptVo;

@Controller
@RequestMapping("dept")
public class DeptController {

	@Autowired
	private DeptService deptService;

	@RequestMapping("toDeptManager")
	public String toDeptManager() {
		return "system/dept/deptManager";
	}

	@RequestMapping("toDeptLeft")
	public String toDeptLeft() {
		return "system/dept/deptLeft";
	}

	@RequestMapping("toDeptRight")
	public String toDeptRight() {
		return "system/dept/deptRight";
	}

	@RequestMapping("toAddDept")
	public String toAddDept() {
		return "system/dept/addDept";
	}

	@RequestMapping("toUpdateDept")
	public String toUpdateDept(DeptVo deptVo,Model model) {
		Dept dept=this.deptService.queryDeptById(deptVo.getId());
		model.addAttribute("dept", dept);
		return "system/dept/updateDept";
	}

	// 查询所有部门 分页 模糊
	@RequestMapping("loadAllDept")
	@ResponseBody
	public DataGridView loadAllDept(DeptVo deptVo) {
		return this.deptService.queryAllDept(deptVo);
	}

	// 删除单个部门
	@RequestMapping("delDept")
	@ResponseBody
	public Map<String, Object> delDept(DeptVo deptVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.deptService.delDept(deptVo);
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

	// 部门添加页面树
	@RequestMapping("loadTreeForDept")
	@ResponseBody
	public List<TreeNode> loadTreeForDept(DeptVo deptVo) {
		List<Dept> list = this.deptService.queryAllDeptForList(deptVo);
		List<TreeNode> treeNodes = new ArrayList<>();
		for (Dept dept : list) {
			Boolean open = dept.getSpread() == SYS_Constast.TYPE_PUBLIC_ONE ? true : false;
			Boolean isParent = dept.getParent() == SYS_Constast.TYPE_PUBLIC_ONE ? true : false;
			treeNodes.add(new TreeNode(dept.getId(), dept.getPid(), dept.getName(), open, isParent));
		}
		return treeNodes;
	}

	// 添加部门
	@RequestMapping("addDept")
	@ResponseBody
	public Map<String, Object> addDept(DeptVo deptVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.deptService.addDept(deptVo);
			if (i > 0) {
				msg = "添加成功";
			} else {
				msg = "添加失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		map.put("msg", msg);
		return map;
	}

	// 修改部门
	@RequestMapping("updateDept")
	@ResponseBody
	public Map<String, Object> updateDept(DeptVo deptVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.deptService.updateDept(deptVo);
			if (i > 0) {
				msg = "修改成功";
			} else {
				msg = "修改失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		map.put("msg", msg);
		return map;
	}
	// 批量删除
		@RequestMapping("delDepts")
		@ResponseBody
		public Map<String, Object> delDepts(DeptVo deptVo) {
			String msg = "";
			Map<String, Object> map = new HashMap<>();
			try {
				int i = this.deptService.delDepts(deptVo);
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
