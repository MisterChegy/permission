package cn.lastwhisper.modular.controller;

import java.io.IOException;
import java.util.List;

import cn.lastwhisper.core.util.UserUtils;
import cn.lastwhisper.modular.pojo.Log;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.vo.Convert;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lastwhisper.core.annotation.LogAnno;
import cn.lastwhisper.modular.pojo.Debitnote;
import cn.lastwhisper.modular.service.DebitnoteService;
import cn.lastwhisper.modular.vo.GlobalResult;

import javax.servlet.http.HttpServletResponse;

@Controller
public class DebitnoteController {
	
	@Autowired
	private DebitnoteService debitnoteService;

	/**
	 *
	 * @Title: userexport
	 * @Description: 根据user条件，导出对应的数据
	 * @return
	 */
	@LogAnno(operateType = "导出信息Excel")
	@RequestMapping(value = "/debit/debitexport", method = RequestMethod.POST)
	@ResponseBody
	public void export(Debitnote debit, HttpServletResponse response) {
		String filename = "借支单" + UserUtils.getSubjectUser().getUser_name() + ".xls";
		// 响应对象
		try {
			// 设置输出流,实现下载文件
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));

			debitnoteService.export(response.getOutputStream(), debit);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Title: export
	 * @Description: 根据debit条件，导出对应的数据
	 * @return
	 */
	@LogAnno(operateType = "打印信息Excel")
	@RequestMapping(value = "/debit/debitprint", method = RequestMethod.POST)
	@ResponseBody
	public void print(Debitnote debit, HttpServletResponse response) {
		String filename = "借支单" + UserUtils.getSubjectUser().getUser_name() + ".pdf";
		// 响应对象
		try {
			// 设置输出流,实现下载文件
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));

			debitnoteService.print(response.getOutputStream(), debit);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Title: rolelistByPage
	 * @Description: 返回分页日志
	 * @param debit
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/debit/debitlistByPage")
	@ResponseBody
	public EasyUIDataGridResult rolelistByPage(Debitnote debit,
											   @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
											   @RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows) {
		EasyUIDataGridResult result = debitnoteService.findDebitnoteByPage(page, rows, debit);
		return result;
	}

	/**
	 * 查找所有
	 * @return
	 */
	@RequestMapping(value="/debit/debitlist")
	@ResponseBody
	public List<Debitnote> findAll() {
		return debitnoteService.findDebitnoteList(); 
	}
	
	/**
	 * 根据菜单id查找菜单，显示菜单详情
	 * @param debitid 主键
	 * @return
	 */
	@RequestMapping("/debit/debitfindById")
	@ResponseBody
	public List<Debitnote> findById(String debitid) {
		return debitnoteService.findDebitnoteById(debitid);
	}
	/**
	 * 添加数据
	 * @param debit 菜单对象
	 * @return
	 */
	@LogAnno(operateType = "添加借支人")
	@RequestMapping(value="/debit/debitadd", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult insert(Debitnote debit) {
		return debitnoteService.addDebitnote(debit);
	}
	
	/**
	 * 根据id删除数据[修改状态]
	 * @param debitid 主键
	 * @return
	 */
	@LogAnno(operateType = "删除借支人")
	@RequestMapping(value="/debit/debitdelete", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult deleteById(String debitid) {
		return (GlobalResult) debitnoteService.deleteDebitnote(debitid);
	}

	/**
	 * 根据ids删除数据[修改状态]
	 * @param debitids 主键
	 * @return
	 */
	@LogAnno(operateType = "删除借支人")
	@RequestMapping(value="/debit/debitdeleteByIds", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult deleteByIds(String debitids) {
		System.out.println("debitids = "+ debitids);
		Integer[] ids = Convert.toIntArray(debitids);
		System.out.println("ids = "+ ids);
		return (GlobalResult) debitnoteService.deleteByIds(ids);
	}
	
	/**
	 * 根据id修改数据
	 * @param debit 菜单对象
	 * @return
	 */
	@LogAnno(operateType = "更新借支人")
	@RequestMapping(value="/debit/debitupdate", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult updateById(Debitnote debit) {
		System.out.println("debit = "+ debit);
		return debitnoteService.updateDebitnoteById(debit);
	}
	
}
