package cn.test.bus.mapper;

import java.util.List;

import cn.test.bus.domain.Goods;
import cn.test.bus.vo.GoodsVo;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

	int delGoodses(Integer[] ids);

	List<Goods> queryAllGoods(GoodsVo goodsVo);


}