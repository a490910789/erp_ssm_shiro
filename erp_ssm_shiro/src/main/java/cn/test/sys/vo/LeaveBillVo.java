package cn.test.sys.vo;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import cn.test.sys.domain.LeaveBill;

public class LeaveBillVo extends LeaveBill{
	/**
	 * 时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	/**
	 * 分页参数
	 */
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
}
