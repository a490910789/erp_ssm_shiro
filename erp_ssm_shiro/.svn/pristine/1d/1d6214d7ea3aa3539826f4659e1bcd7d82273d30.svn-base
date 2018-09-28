package cn.test.bus.service.impl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.bus.domain.Goods;
import cn.test.bus.domain.Inport;
import cn.test.bus.mapper.GoodsMapper;
import cn.test.bus.mapper.InportMapper;
import cn.test.bus.service.InportService;
import cn.test.bus.vo.InportVo;
import cn.test.sys.utils.DataGridView;

@Service
public class InportServiceImpl implements InportService {

	@Autowired
	private InportMapper inportMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public DataGridView queryAllInport(InportVo inportVo) {
		Page<Object> page = PageHelper.startPage(inportVo.getPage(), inportVo.getLimit());
		List<Inport> data = this.inportMapper.queryAllInport(inportVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delInport(InportVo inportVo) {
		return this.inportMapper.deleteByPrimaryKey(inportVo.getId());
	}

	@Override
	public List<Inport> queryAllInportForList(InportVo inportVo) {
		return this.inportMapper.queryAllInport(inportVo);
	}
    //添加进货单时 更新对应商品的数量
	@Override
	public int addInport(InportVo inportVo) {
		Goods goods = this.goodsMapper.selectByPrimaryKey(inportVo.getGoodsid());
		goods.setNumber(goods.getNumber()+inportVo.getNumber());
		this.goodsMapper.updateByPrimaryKeySelective(goods);
		return this.inportMapper.insert(inportVo);
	}

	@Override
	public Inport queryInportById(Integer id) {
		return this.inportMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delInports(InportVo inportVo) {
		return this.inportMapper.delInports(inportVo.getIds());
	}
    //修改进货单时 修正进货数量
	@Override
	public int updateInport(InportVo inportVo, Goods goods) {
		this.goodsMapper.updateByPrimaryKey(goods);
		return this.inportMapper.updateByPrimaryKeySelective(inportVo);
	}
}
