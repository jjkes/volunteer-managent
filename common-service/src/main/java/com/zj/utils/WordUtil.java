package com.zj.utils;

import org.apache.poi.hssf.record.aggregates.PageSettingsBlock;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFPieChartData;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class WordUtil {

    /**
     * @description: 定义xWPFDocument实例
     */
    public static XWPFDocument xWPFDocument = null;

    /**
     * @description: 页眉页脚的位置
     */
    public static final Long HEADER_FOOTER_POSITION =1600L;

    /**
     * @description: 全局字体
     */
    public static final String GENERAL_FONT_FAMILY = "仿宋";
    /**
     * @description: 根据模板地址初始化xwpfDocument
     * @param: [filePath]
     * @return: int
     * @author: 赵健
     * @date: 2022/9/12 11:52
     */
    private int initByTemplate(String filePath){
        int i=0;
        try(
                InputStream is = new FileInputStream(filePath);
                ){
            xWPFDocument = new XWPFDocument(is);
            i=1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }
    /**
     * @description: 初始化xwpfDocument
     * @author: 赵健
     * @date: 2022/9/12 11:55
     */
    public void init(){
        xWPFDocument = new XWPFDocument();
    }

    /**
     * @description: 初始化页面设置
     * @return: void
     * @author: 赵健
     * @date: 2022/9/18 10:28
     */
    public void initPage(){
        CTSectPr sectPr = xWPFDocument.getDocument().getBody().addNewSectPr();
        CTPageMar pgMar = sectPr.addNewPgMar();
        pgMar.setHeader(BigInteger.valueOf(HEADER_FOOTER_POSITION));
        pgMar.setFooter(BigInteger.valueOf(HEADER_FOOTER_POSITION));
    }

    /**
     * @description: 创建页眉
     * @param: [LogoPath -左侧图片, rightStr -右侧字符串]
     * @return: void
     * @author: 赵健
     * @date: 2022/9/18 10:39
     */
    public void createHeader(String firstHeaderSrc,String logoPath,String rightStr){
        if(xWPFDocument.getHeaderList().isEmpty()){
            XWPFHeader header = xWPFDocument.createHeader(HeaderFooterType.FIRST);
            if(firstHeaderSrc.isEmpty()){
                XWPFParagraph hp1 = header.createParagraph();
                hp1.setBorderBottom(Borders.DOUBLE);
                XWPFRun hr1 = hp1.createRun();
                hr1.setText(firstHeaderSrc);
            }

            XWPFHeader defaultHeader = xWPFDocument.createHeader(HeaderFooterType.DEFAULT);
            XWPFParagraph defaultHeaderParagraph = defaultHeader.createParagraph();
            defaultHeaderParagraph.setBorderBottom(Borders.DOUBLE);
            XWPFRun defaultHeaderParagraphRun = defaultHeaderParagraph.createRun();
            defaultHeaderParagraphRun.setText(rightStr);
        }

    }

    /**
     * @description: 创建页脚
     * @param: [leftStr]
     * @return: void
     * @author: 赵健
     * @date: 2022/9/18 10:39
     */
    public void createFooter(String leftStr){
        if(xWPFDocument.getFooterList().isEmpty()&&leftStr != null){
            XWPFFooter footer = xWPFDocument.createFooter(HeaderFooterType.DEFAULT);
            PageSettingsBlock block = new PageSettingsBlock();
            XWPFParagraph fp1 = footer.createParagraph();
            XWPFHyperlinkRun hyperlinkRun = fp1.createHyperlinkRun("http://"+leftStr);
            hyperlinkRun.setText(leftStr);
            hyperlinkRun.setColor("0000ff");
            fp1.setSpacingBefore(0);
            fp1.setBorderTop(Borders.DOUBLE);
            fp1.setVerticalAlignment(TextAlignment.BOTTOM);
            XWPFParagraph fp2 = footer.createParagraph();
            fp2.setAlignment(ParagraphAlignment.NUM_TAB);
            fp2.setVerticalAlignment(TextAlignment.BOTTOM);
            fp2.setSpacingBefore(0);
            XWPFRun run = fp2.createRun();
            run.setText("dadsfa");
        }
    }
    public static void main(String[] args) throws IOException, InvalidFormatException {
        WordUtil wordUtil = new WordUtil();
        wordUtil.init();
        XWPFDocument xWPFDocument = wordUtil.xWPFDocument;
        // 页面初始化设置
        wordUtil.initPage();

        // footer and header
        wordUtil.createHeader("沃达丰",null,"我是默认的header");

        wordUtil.createFooter("www.baidu.com");

        XWPFRun r1 = xWPFDocument.createParagraph().createRun();
        r1.addBreak(BreakType.TEXT_WRAPPING);
        r1.addBreak(BreakType.TEXT_WRAPPING);
        r1.addBreak(BreakType.TEXT_WRAPPING);
        r1.addBreak(BreakType.TEXT_WRAPPING);
        r1.addBreak(BreakType.TEXT_WRAPPING);

        XWPFParagraph mp1 = xWPFDocument.createParagraph();
        mp1.setAlignment(ParagraphAlignment.CENTER);
        mp1.setVerticalAlignment(TextAlignment.CENTER);
        mp1.setKeepNext(false);
        System.err.println(mp1.isKeepNext());

        XWPFRun mp1R1 = mp1.createRun();
        mp1R1.setText("青少年科技中心安全报告");
        mp1R1.setBold(true);
        mp1R1.setFontSize(40);
        mp1R1.setFontFamily(GENERAL_FONT_FAMILY);
        mp1R1.setColor("000000");

        XWPFParagraph paragraph = xWPFDocument.createParagraph();
        System.err.println(paragraph.isKeepNext());
        XWPFRun run = paragraph.createRun();
        run.setText("一、数据饼图");
        run.setFontFamily(GENERAL_FONT_FAMILY);
        run.setFontSize(20);

        XWPFChart chart = xWPFDocument.createChart(run,10 * Units.EMU_PER_CENTIMETER,
                10 * Units.EMU_PER_CENTIMETER);
        chart.setTitleText("sdsd");

        XDDFPieChartData pieChart = (XDDFPieChartData) chart.createData(ChartTypes.PIE, null, null);

        xWPFDocument.createParagraph().createRun().addBreak(BreakType.PAGE);
        XWPFHyperlinkRun hyperlinkRun = xWPFDocument.createParagraph().createHyperlinkRun("http://www.baidu.com");
        hyperlinkRun.setText("百度");
        hyperlinkRun.setColor("0000FF");
        hyperlinkRun.setTextPosition(-800);

        xWPFDocument.createParagraph().createRun().addBreak(BreakType.PAGE);
        XWPFHyperlinkRun hyperlinkRun1 = xWPFDocument.createParagraph().createHyperlinkRun("http://www.baidu.com");
        hyperlinkRun1.setText("百度");
        hyperlinkRun1.setColor("0000FF");
        hyperlinkRun1.setTextPosition(-800);


//        // 保存到本地
//        File file = new File("C:/Users/a1204/Desktop/wordTest/1.doc");
//            if(file==null || !file.exists()){
//            file.mkdirs();
//        }
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(new File("C:/Users/a1204/Desktop/wordTest/1.doc"))){
            xWPFDocument.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
