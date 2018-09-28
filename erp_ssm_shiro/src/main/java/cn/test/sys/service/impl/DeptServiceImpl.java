package cn.test.sys.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.sys.domain.Dept;
import cn.test.sys.mapper.DeptMapper;
import cn.test.sys.service.DeptService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.DeptVo;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public DataGridView queryAllDept(DeptVo deptVo) {
		Page<Object> page = PageHelper.startPage(deptVo.getPage(), deptVo.getLimit());
		List<Dept> data = this.deptMapper.queryAllDept(deptVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delDept(DeptVo deptVo) {
		return this.deptMapper.deleteByPrimaryKey(deptVo.getId());
	}

	@Override
	public List<Dept> queryAllDeptForList(DeptVo deptVo) {
		return this.deptMapper.queryAllDept(deptVo);
	}

	@Override
	public int addDept(DeptVo deptVo) {
		return this.deptMapper.insert(deptVo);
	}

	@Override
	public int updateDept(DeptVo deptVo) {
		return this.deptMapper.updateByPrimaryKey(deptVo);
	}

	@Override
	public Dept queryDeptById(Integer id) {
		return this.deptMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delDepts(DeptVo deptVo) {
		return this.deptMapper.delDepts(deptVo.getIds());
	}
}
