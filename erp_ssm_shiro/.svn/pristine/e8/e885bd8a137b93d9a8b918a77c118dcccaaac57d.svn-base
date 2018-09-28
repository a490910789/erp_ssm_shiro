package cn.test.sys.mapper;

import java.util.List;

import cn.test.sys.domain.LeaveBill;
import cn.test.sys.vo.LeaveBillVo;

public interface LeaveBillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveBill record);

    int insertSelective(LeaveBill record);

    LeaveBill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaveBill record);

    int updateByPrimaryKey(LeaveBill record);

	List<LeaveBill> queryAllLeaveBills(LeaveBillVo leaveBillVo);
}