package com.example.demo.itext;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class itestModule {
    @Test
    public void createPDF() throws DocumentException, FileNotFoundException {
        Document doc=new Document(PageSize.A4);

        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Administrator\\Desktop\\sheet.pdf"));

        //打开文档对象
        doc.open();

        // 加入文字
//        doc.add(new Paragraph(""));

        doc.add(addTable());

        //  关闭文档对象，释放资源
        doc.close();
    }

    public PdfPTable addTable() throws BadElementException {
        PdfPTable table = new PdfPTable(7);
        // 锁定表格宽度
        table.setLockedWidth(true);





        for (int i = 0; i < 217; i++) {
//            table.addCell(cell);
        }
//        cell.setHeader(true);
//
//        //分列
//        cell.setColspan(2);
//
//        cell.setBackgroundColor(Color.blue);
//        table.addCell(cell);

        return table;
    }

//    public PdfPCell addCell()
//    {
//        PdfPCell cell = new PdfPCell().addElement();
//        return  cell;
//    }
//
//    public ColumnText addColumnText()
//    {
////        PdfContentByte
////        ColumnText columnText = new ColumnText();
////        return columnText;
//    }


}
