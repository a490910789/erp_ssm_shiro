package cn.test.bus.service;

import java.util.List;

import cn.test.bus.domain.Goods;
import cn.test.bus.domain.Inport;
import cn.test.bus.vo.InportVo;
import cn.test.sys.utils.DataGridView;

public interface InportService {
	public DataGridView queryAllInport(InportVo inportVo);

	public int delInport(InportVo inportVo);

	public List<Inport> queryAllInportForList(InportVo inportVo);

	public int addInport(InportVo inportVo);

	public Inport queryInportById(Integer id);

	public int delInports(InportVo inportVo);

	public int updateInport(InportVo inportVo, Goods goods);
}
