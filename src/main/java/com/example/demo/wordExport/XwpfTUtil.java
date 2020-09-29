package com.example.demo.wordExport;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XwpfTUtil {



    /**
     * 替换段落里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    public void replaceInPara(XWPFDocument doc, Map<String, Object> params) {

        // 遍历段落
//        for(XWPFParagraph p : doc.getParagraphs())
//        {
//            System.out.print(p.getParagraphText());
//        }

        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
//            System.out.println(para);
            this.replaceInPara(para, params);
        }
    }

    /**
     * 替换段落里面的变量
     *
     * @param para   要替换的段落
     * @param params 参数
     */
    public void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher = this.matcher(para.getParagraphText());
        if (matcher.find()) {
            runs = para.getRuns();

            int start = -1;
            int end = -1;
            String str = "";
            for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                System.out.println("------>>>>>>>>>" + runText);
                System.out.println(runText.length());
                if (runText.length() > 0 &&'$' == runText.charAt(0) && '{' == runText.charAt(1)) {
                    start = i;
                }
                if ((start != -1)) {
                    str += runText;
                }
                if (runText.length() > 0 && '}' == runText.charAt(runText.length() - 1)) {
                    if (start != -1) {
                        end = i;
                        break;
                    }
                }
            }
//            System.out.println("start--->"+start);
//            System.out.println("end--->"+end);

            System.out.println("str---->>>" + str);

            for (int i = start; i <= end; i++) {
                para.removeRun(i);
                i--;
                end--;
//                System.out.println("remove i="+i);
            }


            for (String key : params.keySet()) {
                if (str.equals(key)) {
                    System.out.println("设置 " + key + " = " + (String) params.get(key));
                    para.createRun().setText((String) params.get(key));
                    break;
                }
            }


        }
    }

    /**
     * 替换表格里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    public void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        this.replaceInPara(para, params);
                    }
                }
            }
        }
    }

    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    public void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    public void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void wordToPdf() throws IOException {
        File docxFile = new File("C:\\Users\\Administrator\\Desktop\\20200925154403.doc");
        File pdfFile = new File("C:\\Users\\Administrator\\Desktop\\20200925154403.pdf");

        //转换pdf文件
        if (docxFile.exists()) {
            if (!pdfFile.exists()) {
                InputStream inStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\20200925154403.doc");
                XWPFDocument document = new XWPFDocument(inStream);
                //HWPFDocument document = new HWPFDocument(inStream);
                OutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\20200925154403.pdf");
                PdfOptions options = PdfOptions.create();
//                ExtITextFontRegistry fontProvider = ExtITextFontRegistry.getRegistry();
//                options.fontProvider(fontProvider);
                PdfConverter.getInstance().convert(document, out, options);
            } else {
                System.out.println("PDF文件已存在，无需再次转换");
            }
        }
    }
}
