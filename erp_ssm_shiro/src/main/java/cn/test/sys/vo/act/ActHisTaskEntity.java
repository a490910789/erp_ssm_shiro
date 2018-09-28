package cn.test.sys.vo.act;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActHisTaskEntity {
	  private String id;
	  private String name;
	  private String assignee;
	  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	  private Date startTime;
	  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	  private Date endTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	  
}
