package cn.test.bus.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.bus.domain.Goods;
import cn.test.bus.mapper.GoodsMapper;
import cn.test.bus.service.GoodsService;
import cn.test.bus.vo.GoodsVo;
import cn.test.sys.utils.DataGridView;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public DataGridView queryAllGoods(GoodsVo goodsVo) {
		Page<Object> page = PageHelper.startPage(goodsVo.getPage(), goodsVo.getLimit());
		List<Goods> data = this.goodsMapper.queryAllGoods(goodsVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delGoods(GoodsVo goodsVo) {
		return this.goodsMapper.deleteByPrimaryKey(goodsVo.getId());
	}

	@Override
	public List<Goods> queryAllGoodsForList(GoodsVo goodsVo) {
		return this.goodsMapper.queryAllGoods(goodsVo);
	}

	@Override
	public int addGoods(GoodsVo goodsVo) {
		return this.goodsMapper.insert(goodsVo);
	}

	@Override
	public int updateGoods(GoodsVo goodsVo) {
		return this.goodsMapper.updateByPrimaryKey(goodsVo);
	}

	@Override
	public Goods queryGoodsById(Integer id) {
		return this.goodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delGoodses(GoodsVo goodsVo) {
		return this.goodsMapper.delGoodses(goodsVo.getIds());
	}

	@Override
	public List<Goods> queryGoodsByProviderid(GoodsVo goodsVo) {
		return this.goodsMapper.queryAllGoods(goodsVo);
	}
}
