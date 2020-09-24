package cn.lastwhisper.modular.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import cn.lastwhisper.modular.pojo.Debitnote;
import cn.lastwhisper.modular.pojo.Log;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.GlobalResult;
import com.itextpdf.text.DocumentException;

import javax.servlet.ServletOutputStream;

public interface DebitnoteService {

	List<Debitnote> findDebitnoteList();

	List<Debitnote> findDebitnoteById(String departid);

	GlobalResult addDebitnote(Debitnote depart);

	GlobalResult updateDebitnoteById(Debitnote Debitnote);

	GlobalResult findDebitnote(Debitnote depart);

	EasyUIDataGridResult findDebitnoteByPage(Integer page, Integer rows, Debitnote debitnote);

	void export(OutputStream os, Debitnote debit);

	GlobalResult deleteDebitnote(String debitid);

	void print(ServletOutputStream outputStream, Debitnote debit) throws IOException, DocumentException;

	GlobalResult deleteByIds(Integer[] debitid);
}
