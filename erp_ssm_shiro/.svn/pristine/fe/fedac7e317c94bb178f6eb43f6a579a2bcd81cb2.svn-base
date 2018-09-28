package cn.test.bus.mapper;

import java.util.List;

import cn.test.bus.domain.Outport;
import cn.test.bus.vo.OutportVo;

public interface OutportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Outport record);

    int insertSelective(Outport record);

    Outport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Outport record);

    int updateByPrimaryKey(Outport record);

	int delOutports(Integer[] ids);

	List<Outport> queryAllOutport(OutportVo outportVo);
}