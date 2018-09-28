package cn.test.sys.service;

import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.LogInfoVo;

public interface LogInfoService {
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

	public int delLogInfos(LogInfoVo logInfoVo);

	public int delLogInfo(LogInfoVo logInfoVo);

	public void addLogInfo(LogInfoVo logInfoVo);
}
