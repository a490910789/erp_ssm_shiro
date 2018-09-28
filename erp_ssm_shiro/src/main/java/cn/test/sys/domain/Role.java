package cn.test.sys.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Role {
    private Integer id;

    private String name;

    private String remark;

    private Integer available;
    
    @JsonProperty("LAY_CHECKED")
	private Boolean LAY_CHECKED;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

	public Boolean getLAY_CHECKED() {
		return LAY_CHECKED;
	}

	public void setLAY_CHECKED(Boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}
    
}