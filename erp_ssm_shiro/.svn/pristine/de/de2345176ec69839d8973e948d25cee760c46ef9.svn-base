package cn.test.bus.mapper;

import java.util.List;

import cn.test.bus.domain.Provider;
import cn.test.bus.vo.ProviderVo;

public interface ProviderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

	List<Provider> queryAllProvider(ProviderVo providerVo);

	int delProviders(Integer[] ids);
}