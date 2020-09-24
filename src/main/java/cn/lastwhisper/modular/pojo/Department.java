package cn.lastwhisper.modular.pojo;

import java.util.LinkedList;
import java.util.List;

public class Department {
    private String departmentid;

    private String departname;

    private String status;

    private String pid;// 上一级菜单编号
    
    private Integer is_parent;
    
    private String icon;// 图标样式

    private List<Department> departments = new LinkedList<Department>();
    
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIs_parent() {
		return is_parent;
	}

	public void setIs_parent(Integer is_parent) {
		this.is_parent = is_parent;
	}

	@Override
	public String toString() {
		return "Department{" +
				"departmentid='" + departmentid + '\'' +
				", departname='" + departname + '\'' +
				", status='" + status + '\'' +
				", pid='" + pid + '\'' +
				", is_parent=" + is_parent +
				", icon='" + icon + '\'' +
				", departments=" + departments +
				'}';
	}
}