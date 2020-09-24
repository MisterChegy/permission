/**  
 * @Title:  departController.java   
 * @Package cn.lastwhisper.controller   
 * @version V1.0 
 */
package cn.lastwhisper.modular.controller;

import java.util.List;

import cn.lastwhisper.core.annotation.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.Tree;
import cn.lastwhisper.modular.pojo.Department;
import cn.lastwhisper.modular.service.DepartmentService;

/**   
 * @ClassName:  departController   
 * @Description:菜单管理
 */
@Controller
public class DepartController {
	
	@Autowired
	private DepartmentService departService;
	/**
	 * 查找所有
	 * @return
	 */
	@RequestMapping(value="/depart/departlist", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findAll() {
		return departService.findDepartmentList(); 
	}
	
	/**
	 * 根据菜单id查找菜单，显示菜单详情
	 * @return
	 */
	@RequestMapping(value = "/depart/departfindById", method = RequestMethod.POST)
	@ResponseBody
	public List<Department> findById(String departmentid) {
		return departService.findDepartmentById(departmentid);
	}
	/**
	 * 添加数据
	 * @param depart 菜单对象
	 * @return
	 */
	@LogAnno(operateType = "添加权限菜单")
	@RequestMapping(value="/depart/departadd")
	@ResponseBody
	public GlobalResult insert(Department depart) {
		return departService.addDepartment(depart);
	}
	
	/**
	 * 根据id删除数据[修改状态]
	 * @param departid 主键
	 * @return
	 */
	@LogAnno(operateType = "修改权限菜单")
	@RequestMapping(value="/depart/departdelete")
	@ResponseBody
	public GlobalResult deleteById(String departid) {
		return departService.deleteDepartmentById(departid);
	}
	
	/**
	 * 根据id修改数据
	 * @param depart 菜单对象
	 * @return
	 */
	@LogAnno(operateType = "更新权限菜单")
	@RequestMapping(value="/depart/departupdate")
	@ResponseBody
	public GlobalResult updateById(Department depart) {
		return departService.updateDepartmentById(depart);
	}
	
	
	/**
	 * 
	* @Title: loaddepart 
	* @Description: 根据session中的user_id加载菜单
	* @return depart
	 */
	@RequestMapping(value="/depart/loaddeparts")
	@ResponseBody
	public Department loaddeparts() {
		return null;
	}
}
