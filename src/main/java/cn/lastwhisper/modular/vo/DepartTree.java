package cn.lastwhisper.modular.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.lastwhisper.modular.pojo.Department;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class DepartTree {
	private String id;//菜单id
	private String text;//菜单名称
	private Integer status;//是否已删除
	private boolean checked;//是否为选中
	private List<Department> children;// 下级菜单
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<Department> getChildren() {
		if(null == children) {
			children = new ArrayList<>();
		}
		return children;
	}
	public void setChildren(List<Department> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Tree [id=" + id + ", text=" + text + ", status=" + status + ", checked=" + checked + ", children="
				+ children + "]";
	}
}

