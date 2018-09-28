package cn.test.bus.service.impl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.bus.domain.Goods;
import cn.test.bus.domain.Outport;
import cn.test.bus.mapper.GoodsMapper;
import cn.test.bus.mapper.OutportMapper;
import cn.test.bus.service.OutportService;
import cn.test.bus.vo.OutportVo;
import cn.test.sys.utils.DataGridView;

@Service
public class OutportServiceImpl implements OutportService {

	@Autowired
	private OutportMapper outportMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public DataGridView queryAllOutport(OutportVo outportVo) {
		Page<Object> page = PageHelper.startPage(outportVo.getPage(), outportVo.getLimit());
		List<Outport> data = this.outportMapper.queryAllOutport(outportVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delOutport(OutportVo outportVo) {
		return this.outportMapper.deleteByPrimaryKey(outportVo.getId());
	}

	@Override
	public List<Outport> queryAllOutportForList(OutportVo outportVo) {
		return this.outportMapper.queryAllOutport(outportVo);
	}
    //添加退货单时 更新对应商品的数量
	@Override
	public int addOutport(OutportVo outportVo) {
		Goods goods = this.goodsMapper.selectByPrimaryKey(outportVo.getGoodsid());
		goods.setNumber(goods.getNumber()-outportVo.getNumber());
		this.goodsMapper.updateByPrimaryKeySelective(goods);
		return this.outportMapper.insert(outportVo);
	}

	@Override
	public Outport queryOutportById(Integer id) {
		return this.outportMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delOutports(OutportVo outportVo) {
		return this.outportMapper.delOutports(outportVo.getIds());
	}
    //修改进货单时 修正进退货数量
	@Override
	public int updateOutport(OutportVo outportVo, Goods goods) {
		this.goodsMapper.updateByPrimaryKey(goods);
		return this.outportMapper.updateByPrimaryKeySelective(outportVo);
	}
}
