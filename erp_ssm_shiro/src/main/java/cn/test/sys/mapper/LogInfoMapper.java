package cn.test.sys.mapper;

import java.util.List;

import cn.test.sys.domain.LogInfo;
import cn.test.sys.vo.LogInfoVo;

public interface LogInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(LogInfo record);

	int insertSelective(LogInfo record);

	LogInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(LogInfo record);

	int updateByPrimaryKey(LogInfo record);

	List<LogInfo> queryAllLogInfo(LogInfoVo logInfoVo);

	int delLogInfos(Integer[] ids);
}