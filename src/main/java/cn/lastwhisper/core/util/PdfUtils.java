package cn.lastwhisper.core.util;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtils {

    /**
     * <pre>createTxt(单纯插入文字)
     * 创建人：冷夏曦
     * 创建时间：2017年10月21日 上午11:12:37
     * 修改人：冷夏曦
     * 修改时间：2017年10月21日 上午11:12:37
     * 修改备注：
     * @throws IOException
     */
    public static void createTxt() throws DocumentException, IOException{
        // 1.新建document对象
        Document document = new Document();

        //转格式，添加支持汉字，无需支持汉字的话，new Paragraph("String string")只需string一个参数即可
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        //蓝色字体
        Font blueFont = new Font(bfChinese);
//        blueFont.setColor(BaseColor.BLUE); 无需设置颜色可不用添加

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("F:/test.pdf"));

        // 3.打开文档
        document.open();

        // 4.添加一个内容段落
        document.add(new Paragraph("测试中文 !测试中文 !测试中文 !测试中文 !",blueFont));

        // 5.关闭文档
        document.close();
        System.err.println("createTxt 已执行");
    }

    /**
     * <pre>createPdfWithDetail(插入内容，以及文档属性)
     */
    public static void createPdfWithDetail() throws FileNotFoundException, DocumentException{
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("F:/test2.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("write here whats you need"));
        //设置属性
        //标题
        document.addTitle("冷夏曦标题");
        //作者
        document.addAuthor("who write this");
        //主题
        document.addSubject("this is subject");
        //关键字
        document.addKeywords("Keywords");
        //创建时间
        document.addCreationDate();
        //应用程序
        document.addCreator("hd.com");

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
        System.err.println("createPdfWithDetail 已执行");
    }

    /**
     * <pre>createPdfAddImg(图片加入已存在的pdf)
     */
    public static void createPdfAddImg() throws  DocumentException, IOException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("F:/test2.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("write here whats you need"));

        //图片1
        Image image1 = Image.getInstance("F:/2.JPG");
        //设置图片位置的x轴和y周
        image1.setAbsolutePosition(100f, 550f);
        //设置图片的宽度和高度
        image1.scaleAbsolute(200, 200);
        //将图片1添加到pdf文件中
        document.add(image1);

        //加入网络路径的图片
        Image image2 = Image.getInstance(new URL("http://static.cnblogs.com/images/adminlogo.gif"));
        //将图片2添加到pdf文件中
        document.add(image2);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();

        System.err.println("createPdfAddImg 已执行");
    }

    /**
     * <pre>createTable(生成table表格)
     */
    public static void createTable() throws DocumentException, FileNotFoundException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("I:/test4.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("借支单"));

        // 3列的表.
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距

        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = { 1f, 2f, 3f };
        table.setWidths(columnWidths);

        //行1
        PdfPCell cells1[]= new PdfPCell[3];
        PdfPRow row1 = new PdfPRow(cells1);

        //单元格
        cells1[0] = new PdfPCell(new Paragraph("111"));//单元格内容
//        cells1[0].setBorderColor(BaseColor.BLUE);//边框验证
//        cells1[0].setPaddingLeft(20);//左填充20
        cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

        cells1[1] = new PdfPCell(new Paragraph("222"));//[0/1/2]分别表示td的下标位置
        cells1[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

        cells1[2] = new PdfPCell(new Paragraph("333"));
        cells1[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

        //行2
        PdfPCell cells2[]= new PdfPCell[3];
        PdfPRow row2 = new PdfPRow(cells2);

        cells2[0] = new PdfPCell(new Paragraph("444"));
        cells2[1] = new PdfPCell(new Paragraph("555"));
        cells2[2] = new PdfPCell(new Paragraph("666"));

        //把第一行添加到集合
        listRow.add(row1);
        listRow.add(row2);
        //把表格添加到文件中
        document.add(table);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();

        System.err.println("createTable 已执行");
    }

    /**
     * <pre>buildChineseCharactor(带段落格式输出，支持中文）
     */
    public static void buildChineseCharactor() throws DocumentException, IOException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("F:/test6.pdf"));
        //打开文件
        document.open();

        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

        //蓝色字体
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLUE);
        //段落文本
        Paragraph paragraphBlue = new Paragraph("paragraphOne blue front", blueFont);
        document.add(paragraphBlue);

        //绿色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.GREEN);
        //创建章节
        Paragraph chapterTitle = new Paragraph("段落标题xxxx", greenFont);
        Chapter chapter1 = new Chapter(chapterTitle, 1);
        chapter1.setNumberDepth(0);

        Paragraph sectionTitle = new Paragraph("部分标题", greenFont);
        Section section1 = chapter1.addSection(sectionTitle);

        Paragraph sectionContent = new Paragraph("部分内容", blueFont);
        section1.add(sectionContent);

        //将章节添加到文章中
        document.add(chapter1);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();

        System.err.println("buildChineseCharactor 已执行");
    }

    /**
     * <pre>createWithPassword(创建带密码的文档)
     */
    public static void createWithPassword() throws DocumentException, IOException {
        // 创建文件
        Document document = new Document();
        // 建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("F:/test8.pdf"));

        //用户密码
        String userPassword = "123456";
        //拥有者密码
        String ownerPassword = "lxx";
        writer.setEncryption(userPassword.getBytes(), ownerPassword.getBytes(), PdfWriter.ALLOW_PRINTING,
                PdfWriter.ENCRYPTION_AES_128);

        // 打开文件
        document.open();

        //添加内容
        document.add(new Paragraph("open Document SUCCESS!"));

        // 关闭文档
        document.close();
        // 关闭书写器
        writer.close();
    }

    /**
     * <pre>fillTemplate(根据表格模板生成pdf)
     */
    public static void fillTemplate() {
        // 模板路径
        String templatePath = "F:/basicPersonalInfo.pdf";
        // 生成的新文件路径
        String newPDFPath = "F:/Test1New.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            String[] str = { "123", "456", "789", "1705c", "test", "jinke" };
            int i = 0;
            //迭代器取键值循环，加入文本域
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                System.out.println("文本域:"+name+"填入ok");//打印pdf表单自定义文本域的名称，验证是否加入
                form.setField(name, str[i++]);
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }

    }

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        createTable();
        System.err.println("----------█导出pdf完成█------------");
    }


}
