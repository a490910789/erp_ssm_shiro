package cn.test.sys.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.sys.domain.LogInfo;
import cn.test.sys.mapper.LogInfoMapper;
import cn.test.sys.service.LogInfoService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.LogInfoVo;

@Service
public class LogInfoServiceImpl implements LogInfoService {

	@Autowired
	private LogInfoMapper logInfoMapper;

	@Override
	public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
		Page<Object> page = PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
		List<LogInfo> data = this.logInfoMapper.queryAllLogInfo(logInfoVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delLogInfos(LogInfoVo logInfoVo) {
		return this.logInfoMapper.delLogInfos(logInfoVo.getIds());
	}

	@Override
	public int delLogInfo(LogInfoVo logInfoVo) {
		return this.logInfoMapper.deleteByPrimaryKey(logInfoVo.getId());
	}

	@Override
	public void addLogInfo(LogInfoVo logInfoVo) {
		this.logInfoMapper.insert(logInfoVo);
	}
}
