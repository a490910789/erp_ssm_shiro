package cn.test.sys.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.sys.domain.LeaveBill;
import cn.test.sys.mapper.LeaveBillMapper;
import cn.test.sys.service.LeaveBillService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.LeaveBillVo;

@Service
public class LeaveBillServiceImpl implements LeaveBillService {

	@Autowired
	private LeaveBillMapper leaveBillMapper;

	@Override
	public DataGridView queryAllLeaveBills(LeaveBillVo leaveBillVo) {
		Page<Object> page = PageHelper.startPage(leaveBillVo.getPage(), leaveBillVo.getLimit());
		List<LeaveBill> data = this.leaveBillMapper.queryAllLeaveBills(leaveBillVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delLeaveBill(LeaveBillVo leaveBillVo) {
		return this.leaveBillMapper.deleteByPrimaryKey(leaveBillVo.getId());
	}

	@Override
	public List<LeaveBill> queryAllLeaveBillForList(LeaveBillVo leaveBillVo) {
		return this.leaveBillMapper.queryAllLeaveBills(leaveBillVo);
	}

	@Override
	public int addLeaveBill(LeaveBillVo leaveBillVo) {
		return this.leaveBillMapper.insert(leaveBillVo);
	}

	@Override
	public int updateLeaveBill(LeaveBillVo leaveBillVo) {
		return this.leaveBillMapper.updateByPrimaryKeySelective(leaveBillVo);
	}

	@Override
	public LeaveBill queryLeaveBillById(Integer id) {
		return this.leaveBillMapper.selectByPrimaryKey(id);
	}

}
