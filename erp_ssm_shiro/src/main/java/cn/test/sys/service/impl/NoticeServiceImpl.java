package cn.test.sys.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.sys.domain.Notice;
import cn.test.sys.mapper.NoticeMapper;
import cn.test.sys.service.NoticeService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.NoticeVo;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public DataGridView queryAllNotice(NoticeVo noticeVo) {
		Page<Object> page = PageHelper.startPage(noticeVo.getPage(), noticeVo.getLimit());
		List<Notice> data = this.noticeMapper.queryAllNotice(noticeVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delNotice(NoticeVo noticeVo) {
		return this.noticeMapper.deleteByPrimaryKey(noticeVo.getId());
	}

	@Override
	public List<Notice> queryAllNoticeForList(NoticeVo noticeVo) {
		return this.noticeMapper.queryAllNotice(noticeVo);
	}

	@Override
	public int addNotice(NoticeVo noticeVo) {
		return this.noticeMapper.insert(noticeVo);
	}

	@Override
	public int updateNotice(NoticeVo noticeVo) {
		return this.noticeMapper.updateByPrimaryKeySelective(noticeVo);
	}

	@Override
	public Notice queryNoticeById(Integer id) {
		return this.noticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delNotices(NoticeVo noticeVo) {
		return this.noticeMapper.delNotices(noticeVo.getIds());
	}
}
