package cn.test.sys.service;

import java.util.List;

import cn.test.sys.domain.Dept;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.DeptVo;

public interface DeptService {
	public DataGridView queryAllDept(DeptVo deptVo);

	public int delDept(DeptVo deptVo);

	public List<Dept> queryAllDeptForList(DeptVo deptVo);

	public int addDept(DeptVo deptVo);

	public int updateDept(DeptVo deptVo);

	public Dept queryDeptById(Integer id);

	public int delDepts(DeptVo deptVo);
}
