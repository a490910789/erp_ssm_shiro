package cn.test.sys.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.test.sys.domain.LogInfo;

public class LogInfoVo extends LogInfo {
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	private Integer[] ids;
	private Integer page;
	private Integer limit;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public LogInfoVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LogInfoVo(String loginname, String loginip, Date logintime) {
		super(loginname, loginip, logintime);
		// TODO Auto-generated constructor stub
	}
}
