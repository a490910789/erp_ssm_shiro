package cn.test.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.test.sys.domain.Notice;
import cn.test.sys.domain.User;
import cn.test.sys.service.NoticeService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.NoticeVo;

@Controller
@RequestMapping("notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("toNoticeManager")
	public String toNoticeManager() {
		return "system/notice/noticeManager";
	}

	@RequestMapping("toAddOrUpdateNotice")
	public String toAddNotice(NoticeVo noticeVo,Model model) {
		if(null==noticeVo.getId()) {
			return "system/notice/addOrUpdateNotice";
		}else {
			Notice notice = this.noticeService.queryNoticeById(noticeVo.getId());
			model.addAttribute("notice", notice);
			return "system/notice/addOrUpdateNotice";
		}
	}

/*	@RequestMapping("toUpdateNotice")
	public String toUpdateNotice(NoticeVo noticeVo,Model model) {
		Notice notice=this.noticeService.queryNoticeById(noticeVo.getId());
		model.addAttribute("notice", notice);
		return "system/notice/updateNotice";
	}*/

	// 查询所有公告 分页 模糊
	@RequestMapping("loadAllNotice")
	@ResponseBody
	public DataGridView loadAllNotice(NoticeVo noticeVo) {
		return this.noticeService.queryAllNotice(noticeVo);
	}

	// 删除单个公告
	@RequestMapping("delNotice")
	@ResponseBody
	public Map<String, Object> delNotice(NoticeVo noticeVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.noticeService.delNotice(noticeVo);
			if (i > 0) {
				msg = "删除成功";
			} else {
				msg = "删除失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		map.put("msg", msg);
		return map;
	}

	// 添加公告
	@RequestMapping("addOrUpdateNotice")
	@ResponseBody
	public Map<String, Object> addOrUpdateNotice(NoticeVo noticeVo,HttpSession session) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		if(null==noticeVo.getId()) {
			try {
				noticeVo.setCreatetime(new Date());
				noticeVo.setOpername(((User)session.getAttribute("user")).getName());
				int i = this.noticeService.addNotice(noticeVo);
				if (i > 0) {
					msg = "添加成功";
				} else {
					msg = "添加失败";
				}

			} catch (Exception e) {
				e.printStackTrace();
				msg = "添加失败";
			}
			map.put("msg", msg);
			return map;
		}else {
		return this.updateNotice(noticeVo);
		}
	}

	// 修改公告
	@RequestMapping("updateNotice")
	@ResponseBody
	public Map<String, Object> updateNotice(NoticeVo noticeVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.noticeService.updateNotice(noticeVo);
			if (i > 0) {
				msg = "修改成功";
			} else {
				msg = "修改失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		map.put("msg", msg);
		return map;
	}
	// 删除单个公告
		@RequestMapping("delNotices")
		@ResponseBody
		public Map<String, Object> delNotices(NoticeVo noticeVo) {
			String msg = "";
			Map<String, Object> map = new HashMap<>();
			try {
				int i = this.noticeService.delNotices(noticeVo);
				if (i > 0) {
					msg = "删除成功";
				} else {
					msg = "删除失败";
				}

			} catch (Exception e) {
				e.printStackTrace();
				msg = "删除失败";
			}
			map.put("msg", msg);
			return map;
		}
	    //查看详细内容
	@RequestMapping("findContent")
		public String findContent(NoticeVo noticeVo,Model model){
			Notice notice = this.noticeService.queryNoticeById(noticeVo.getId());
			model.addAttribute("notice",notice);
			return "system/notice/content";
		}
   } 
