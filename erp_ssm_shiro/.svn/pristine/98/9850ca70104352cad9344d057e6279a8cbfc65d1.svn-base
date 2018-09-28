package cn.test.bus.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.bus.domain.Provider;
import cn.test.bus.mapper.ProviderMapper;
import cn.test.bus.service.ProviderService;
import cn.test.bus.vo.ProviderVo;
import cn.test.sys.utils.DataGridView;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private ProviderMapper providerMapper;

	@Override
	public DataGridView queryAllProvider(ProviderVo providerVo) {
		Page<Object> page = PageHelper.startPage(providerVo.getPage(), providerVo.getLimit());
		List<Provider> data = this.providerMapper.queryAllProvider(providerVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delProvider(ProviderVo providerVo) {
		return this.providerMapper.deleteByPrimaryKey(providerVo.getId());
	}

	@Override
	public List<Provider> queryAllProviderForList(ProviderVo providerVo) {
		return this.providerMapper.queryAllProvider(providerVo);
	}

	@Override
	public int addProvider(ProviderVo providerVo) {
		return this.providerMapper.insert(providerVo);
	}

	@Override
	public int updateProvider(ProviderVo providerVo) {
		return this.providerMapper.updateByPrimaryKey(providerVo);
	}

	@Override
	public Provider queryProviderById(Integer id) {
		return this.providerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delProviders(ProviderVo providerVo) {
		return this.providerMapper.delProviders(providerVo.getIds());
	}
}
