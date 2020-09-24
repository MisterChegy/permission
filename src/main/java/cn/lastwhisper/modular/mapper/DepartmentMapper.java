package cn.lastwhisper.modular.mapper;

import cn.lastwhisper.modular.pojo.Department;
import cn.lastwhisper.modular.vo.Tree;

import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface DepartmentMapper {
	/**
	 * 
	 * @Title: selectDepartmentList   
	 * @Description: 查找所有数据
	 * @return
	 */
	List<Tree> selectDepartmentList();

	/**
	 * 
	 * @Title: selectDepartmentById   
	 * @Description: 根据菜单id查找菜单，显示菜单详情
	 * @return
	 */
	List<Department> selectDepartmentById(@Param("departmentid") String departmentid);

	/**
	 * 
	 * @Title: insertDepartment   
	 * @Description: 添加数据
	 * @param Department
	 * @return
	 */
	Integer insertDepartment(Department Department);

	/**
	 * 
	 * @Title: deleteDepartmentById   
	 * @Description: 根据id删除数据[修改状态]
	 * @param Departmentid
	 * @return
	 */
	Integer deleteDepartmentById(String Departmentid);

	/**
	 * 
	 * @Title: updateDepartmentById   
	 * @Description: 根据id修改数据
	 * @param Department
	 * @return
	 */
	Integer updateDepartmentById(Department Department);
	
	/**
	 * 
	 * @Title: selectDepartmentIdName
	 * @Description: 根据pid获取所有权限菜单(Departmentid,Departmentname)
	 * @return List<Department>
	 * @date 2019年2月16日下午7:05:10
	 */
	public List<Department> selectDepartmentIdName(@Param("pid") String pid);

	/**
	 * 
	 * @Title: selectDepartmentByUserid
	 * @Description: 根据userid加载对应菜单
	 * @param userid
	 * @return List<Department>
	 * @date 2019年2月16日下午8:40:39
	 */
	public List<Department> selectDepartmentByUserid(@Param("userid") Integer userid);
	
	/**
	 * 
	* @Title: selectDepartment 
	* @Description: 查询所有菜单的所有属性 
	* @param pid
	* @return List<Department>
	* @date 2019年2月16日下午9:04:26
	 */
	public List<Department> selectDepartment(@Param("pid") String pid);

}
