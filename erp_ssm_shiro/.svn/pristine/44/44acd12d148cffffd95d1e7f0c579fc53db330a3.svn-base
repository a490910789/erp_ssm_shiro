package cn.test.sys.mapper;

import java.util.List;

import cn.test.sys.domain.Dept;
import cn.test.sys.vo.DeptVo;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

	List<Dept> queryAllDept(DeptVo deptVo);

	int delDepts(Integer[] ids);
	
}