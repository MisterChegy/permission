package cn.lastwhisper.modular.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lastwhisper.modular.mapper.DepartmentMapper;
import cn.lastwhisper.modular.pojo.Department;
import cn.lastwhisper.modular.pojo.Menu;
import cn.lastwhisper.modular.service.DepartmentService;
import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.Tree;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service("departmentBiz")
public class DepartmentServiceImpl implements DepartmentService {

    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private DepartmentMapper departmentDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public List<Tree> findDepartmentList() {

        return departmentDao.selectDepartmentList();
    }

    @Override
    public List<Department> findDepartmentById(String departmentid) {
        return departmentDao.selectDepartmentById(departmentid);
    }

    @Override
    public GlobalResult addDepartment(Department Department) {

        // 设置默认添加的菜单的状态为使用中
        Integer insertCount = departmentDao.insertDepartment(Department);
        if (insertCount != null && insertCount > 0) {
            // 更新标签为父标签
            Department m = new Department();
            m.setDepartmentid(Department.getPid());
            m.setIs_parent(1);
            if (200 == updateDepartmentById(m).getStatus()) {
                batchDel();
                return new GlobalResult(200, "数据添加成功", null);
            } else {
                return new GlobalResult(400, "数据添加失败", null);
            }
        } else {
            return new GlobalResult(400, "数据添加失败", null);
        }
    }

    /**
     * 根据key前缀批量删除缓存
     *
     * @return
     */
    private void batchDel() {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.flushAll();
        } catch (Exception ignored) {
        }
    }

    @Override
    public GlobalResult deleteDepartmentById(String Departmentid) {
        Integer deleteCount = departmentDao.deleteDepartmentById(Departmentid);
        if (deleteCount != null && deleteCount > 0) {
            batchDel();
            return new GlobalResult(200, "数据删除成功", null);
        } else {
            return new GlobalResult(400, "数据删除失败", null);
        }
    }

    @Override
    public GlobalResult updateDepartmentById(Department Department) {
        Integer updateCount = departmentDao.updateDepartmentById(Department);
        if (updateCount != null && updateCount > 0) {
            batchDel();
            return new GlobalResult(200, "数据修改成功", null);
        } else {
            return new GlobalResult(400, "数据修改失败", null);
        }
    }

    @Override
    public Department findDepartmentByUserid(Integer userid) {

        return null;
    }

    @Override
    public List<Department> findDepartmentListByUserid(Integer userid) {

        return null;
    }


}
