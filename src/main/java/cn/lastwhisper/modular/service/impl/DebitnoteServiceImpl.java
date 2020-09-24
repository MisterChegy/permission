package cn.lastwhisper.modular.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import cn.lastwhisper.modular.pojo.Debitnote;
import cn.lastwhisper.modular.pojo.Log;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lastwhisper.modular.mapper.DebitnoteMapper;
import cn.lastwhisper.modular.pojo.Debitnote;
import cn.lastwhisper.modular.service.DebitnoteService;
import cn.lastwhisper.modular.vo.GlobalResult;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletOutputStream;

@Service("debitnoteService")
public class DebitnoteServiceImpl implements DebitnoteService {

    @Autowired
    private DebitnoteMapper debitnoteMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public List<Debitnote> findDebitnoteList() {

        return null;
    }

    @Override
    public List<Debitnote> findDebitnoteById(String debitnoteid) {

        return null;
    }

    @Override
    public GlobalResult addDebitnote(Debitnote debitnote) {
        System.out.println("debitnote = " + debitnote);
        // 设置默认添加的菜单的状态为使用中
        Integer insertCount = debitnoteMapper.insertDebitnote(debitnote);
        System.out.println("insertCount = " + insertCount);
        if (insertCount != null && insertCount > 0) {
            return new GlobalResult(200, "数据添加成功", null);
        } else {
            return new GlobalResult(400, "数据添加失败", null);
        }
    }

    @Override
    public GlobalResult updateDebitnoteById(Debitnote Debitnote) {
        Integer updateCount = debitnoteMapper.updateDebitnoteById(Debitnote);
        System.out.println("updateCount = " + updateCount);
        if (updateCount != null && updateCount > 0) {
            batchDel();
            return new GlobalResult(200, "数据修改成功", null);
        } else {
            return new GlobalResult(400, "数据修改失败", null);
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
    public GlobalResult findDebitnote(Debitnote debitnote) {

        return null;
    }

    @Override
    public EasyUIDataGridResult findDebitnoteByPage(Integer page, Integer rows, Debitnote debitnote) {
        PageHelper.startPage(page, rows);
        List<Debitnote> list = debitnoteMapper.selectDebitnotelistByPage(debitnote);
        PageInfo<Debitnote> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        return result;
    }

    @Override
    public void export(OutputStream os, Debitnote debit) {
        // 获取所有供应商信息
        List<Debitnote> list = debitnoteMapper.selectDebitnotelistByPage(debit);
        // 1.创建excel工作薄
        HSSFWorkbook wk = new HSSFWorkbook();
        // 2.创建一个工作表
        HSSFSheet sheet = wk.createSheet("借支单");
        // 3.写入表头
        HSSFRow row = sheet.createRow(0);
        // 表头
        String[] headerName = {"借支人", "职位", "借支事由", "人民币", "金额", "核准", "会计", "出纳"};
        // 列宽
        int[] columnWidths = {6000, 6000, 6000, 6000, 6000, 6000, 6000, 6000};
        HSSFCell cell = null;
        for (int i = 0; i < headerName.length; i++) {
            // 创建表头单元格
            cell = row.createCell(i);
            // 向表头单元格写值
            cell.setCellValue(headerName[i]);
            sheet.setColumnWidth(i, columnWidths[i]);
        }
        // 4.向内容单元格写值
        int i = 1;
        for (Debitnote u : list) {
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(u.getDebitname());// 借支人姓名
            row.createCell(1).setCellValue(u.getPosition());
            row.createCell(2).setCellValue(u.getReason());
            row.createCell(3).setCellValue(u.getMoney());
            row.createCell(4).setCellValue(u.getMember());
            row.createCell(5).setCellValue(u.getCheck());
            row.createCell(6).setCellValue(u.getNote());
            row.createCell(7).setCellValue(u.getCashier());

            i++;
        }
        try {
            // 写入到输出流中
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭工作簿
                wk.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public GlobalResult deleteDebitnote(String debitid) {
        Integer integer = debitnoteMapper.deleteDebitnote(debitid);
        if (integer == 0) {
            return new GlobalResult(400, "删除失败", null);
        } else {
            return new GlobalResult(200, "删除成功", null);
        }
    }

    @Override
    public void print(ServletOutputStream os, Debitnote debit) throws IOException, DocumentException {
        System.out.println("debit = " + debit);
        //中文字体
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 12, Font.NORMAL);
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, os);
        //打开文件
        document.open();
        //添加标题
        document.addTitle("借支单");
        // 4列的表.
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距

//        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = {1f, 2f, 1f, 2f};
        table.setWidths(columnWidths);

        //行1
        PdfPCell cells1[] = new PdfPCell[4];
        PdfPRow row1 = new PdfPRow(cells1);

        //单元格
        cells1[0] = new PdfPCell(new Paragraph("借支人", font));//单元格内容
        cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells1[0]);
        
        cells1[1] = new PdfPCell(new Paragraph(debit.getDebitname(), font));//[0/1/2]分别表示td的下标位置
        cells1[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells1[1]);
        
        cells1[2] = new PdfPCell(new Paragraph("职位", font));
        cells1[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells1[2]);
        
        cells1[3] = new PdfPCell(new Paragraph(debit.getPosition(), font));
        cells1[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells1[3]);

        //行2
        PdfPCell cells2[] = new PdfPCell[4];

        //单元格
        cells2[0] = new PdfPCell(new Paragraph("人民币", font));//单元格内容
        cells2[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells2[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells2[0]);

        cells2[1] = new PdfPCell(new Paragraph(debit.getMoney(), font));//[0/1/2]分别表示td的下标位置
        cells2[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells2[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells2[1]);

        cells2[2] = new PdfPCell(new Paragraph("￥", font));
        cells2[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells2[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells2[2]);

        cells2[3] = new PdfPCell(new Paragraph(debit.getMember(), font));
        cells2[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells2[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells2[3]);

        //行3
        PdfPCell cells3[] = new PdfPCell[4];

        //单元格
        cells3[0] = new PdfPCell(new Paragraph("核准", font));//单元格内容
        cells3[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells3[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells3[0]);

        cells3[1] = new PdfPCell(new Paragraph(debit.getCheck(), font));//[0/1/2]分别表示td的下标位置
        cells3[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells3[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells3[1]);

        cells3[2] = new PdfPCell(new Paragraph("会计", font));
        cells3[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells3[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells3[2]);

        cells3[3] = new PdfPCell(new Paragraph(debit.getNote(), font));
        cells3[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells3[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells3[3]);

        //行4
        PdfPCell cells4[] = new PdfPCell[4];

        //单元格
        cells4[0] = new PdfPCell(new Paragraph("出纳", font));//单元格内容
        cells4[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells4[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells4[0]);

        cells4[1] = new PdfPCell(new Paragraph(debit.getCashier(), font));//[0/1/2]分别表示td的下标位置
        cells4[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells4[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells4[1]);

        cells4[2] = new PdfPCell(new Paragraph("借支人", font));
        cells4[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells4[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells4[2]);

        cells4[3] = new PdfPCell(new Paragraph(debit.getCashier(), font));
        cells4[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells4[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells4[3]);

        //行5
        PdfPCell cells5[] = new PdfPCell[4];

        //单元格
        cells5[0] = new PdfPCell(new Paragraph("借支事由", font));//单元格内容
        cells5[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells5[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        table.addCell(cells5[0]);

        cells5[1] = new PdfPCell(new Paragraph(debit.getReason(), font));//[0/1/2]分别表示td的下标位置
        cells5[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells5[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cells5[1].setRowspan(3);
        table.addCell(cells5[1]);

        //把表格添加到文件中
        document.add(table);

        //关闭书写器
        writer.close();
        //关闭文档
        document.close();

        System.err.println("createTable 已执行");
    }

    @Override
    public GlobalResult deleteByIds(Integer[] debitid) {
        Integer integer = debitnoteMapper.deleteByIds(debitid);
        if (integer == 0) {
            return new GlobalResult(400, "删除失败", null);
        } else {
            return new GlobalResult(200, "删除成功", null);
        }
    }

}
