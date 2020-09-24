package cn.lastwhisper.modular.service;

import java.util.List;

import cn.lastwhisper.modular.pojo.Department;
import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.Tree;

public interface DepartmentService {
	/**
	 * 
	 * @Title: findDepartmentList   
	 * @Description: 查找所有数据 
	 * @return
	 */
	List<Tree> findDepartmentList();

	/**
	 * 
	 * @Title: findDepartmentById   
	 * @Description: 根据菜单id查找菜单，显示菜单详情
	 * @param Departmentid
	 * @return
	 */
	List<Department> findDepartmentById(String Departmentid);

	/**
	 * 
	 * @Title: addDepartment   
	 * @Description: 添加数据
	 * @param Department
	 * @return
	 */
	GlobalResult addDepartment(Department Department);
	
	/**
	 * 
	 * @Title: deleteDepartmentById   
	 * @Description: 根据id删除数据
	 * @param Departmentid
	 * @return
	 */
	GlobalResult deleteDepartmentById(String Departmentid);

	/**
	 * 
	 * @Title: updateDepartmentById   
	 * @Description: 根据id修改数据
	 * @param Department
	 * @return
	 */
	GlobalResult updateDepartmentById(Department Department);
	/**
	 * 
	* @Title: findDepartmentByUserid 
	* @Description: 根据userid加载对应菜单 
	* @param userid
	* @return Department
	 */
	Department findDepartmentByUserid(Integer userid);
	/**
	 * 
	* @Title: findDepartmentListByUserid 
	* @Description: 根据userid加载对应菜单无序列表 
	* @param userid
	* @return List<Department>
	 */
	List<Department> findDepartmentListByUserid(Integer userid);
}
