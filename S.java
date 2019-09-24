package com.example.myapplication05.ResumePackage.Activity.ActivityResume;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Base64;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class S {
    public static void CreateParagraphAlignRight(Paragraph paragraph, Document document) {
        Font paraFontName = new Font();
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph.setFont(paraFontName);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void CreateParagraphAlignRightBold(Paragraph paragraph, Document document) {
        Font paraFontName = new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph.setFont(paraFontName);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void CreateParagraphAlignLeftUnderLine(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.ITALIC);
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setUnderline(0.5f, -2f);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        paragraph.setPaddingTop(5);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void CreateParagraphAlignLeftUnderLineSmall(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD | Font.ITALIC);
        paragraph.setTabSettings(new TabSettings(30f));
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setUnderline(0.0f,-2f);
        paragraph.setExtraParagraphSpace(5);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void CreateParagraphAlignLeftUnderLineBold(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.ITALIC);
        Chunk sigUnderline = new Chunk(value, boldFont);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void CreateParagraphAlignRightUnderLineBold(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.ITALIC);
        Chunk sigUnderline = new Chunk(value, boldFont);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void CreateParagraphAlignLeftWithTabBold(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        paragraph.setTabSettings(new TabSettings(35f));
        paragraph.add(Chunk.TABBING);
        Chunk sigUnderline = new Chunk(value, boldFont);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphAlignLeftUnderLineWithTab(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD | Font.ITALIC);
        paragraph.setTabSettings(new TabSettings(35f));
        paragraph.add(Chunk.TABBING);
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setUnderline(0.5f, -2f);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphAlignLeftUnderLineMinSpace(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.ITALIC);
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setUnderline(0.5f, -2f);
        paragraph.setSpacingBefore(2);
        paragraph.setSpacingAfter(2);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphAlignLeftUnderLineCenter(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD | Font.ITALIC);
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setUnderline(0.5f, -2f);
        paragraph.setSpacingBefore(2);
        paragraph.setSpacingAfter(2);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphAlignLeftUnderLineCenterWithColor(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD | Font.ITALIC, BaseColor.RED);
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setUnderline(0.5f, -2f);
        paragraph.setSpacingBefore(2);
        paragraph.setSpacingAfter(2);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphBoxWithColor(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD | Font.ITALIC, BaseColor.WHITE);
        Chunk sigUnderline = new Chunk(value, boldFont);
//        sigUnderline.setBackground(BaseColor.DARK_GRAY);
        sigUnderline.setBackground(BaseColor.DARK_GRAY, 500, 2, 500, 2);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphBoxWithColorAlignLeft(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD | Font.ITALIC, BaseColor.WHITE);
        Chunk sigUnderline = new Chunk(value, boldFont);
//        sigUnderline.setBackground(BaseColor.DARK_GRAY);
        sigUnderline.setBackground(BaseColor.DARK_GRAY, 10, 2, 500, 2);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphAlignLeftBoxWithColor(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD | Font.ITALIC, BaseColor.WHITE);
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setBackground(BaseColor.DARK_GRAY, 5, 2, 10, 2);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        paragraph.setExtraParagraphSpace(4);
        paragraph.add(sigUnderline);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphAlignLeft(Paragraph paragraph, Document document) {
        Font paraFontName = new Font(Font.FontFamily.HELVETICA, 12);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph.setFont(paraFontName);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphAlignLeftWithTab(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        paragraph.setTabSettings(new TabSettings(35f));
        paragraph.setExtraParagraphSpace(35f);
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setFont(boldFont);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph.add(sigUnderline);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void CreateParagraphAlignLeftWithTabBol(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
        paragraph.setTabSettings(new TabSettings(35f));
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setFont(boldFont);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph.add(sigUnderline);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void T(Context c, String msg) {
        Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
    }
    public static void CreateParagraphAlignLeftWithDoubleTab(Paragraph paragraph, Document document, String value) {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        paragraph.setTabSettings(new TabSettings(45f));
        Chunk sigUnderline = new Chunk(value, boldFont);
        sigUnderline.setFont(boldFont);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph.add(sigUnderline);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void CreateTableWithColumn(Document doc, String str1, String str2) {
        try {
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{0.05f, 1, 0.1f, 3});
            table.addCell(createCellFourth());
            table.addCell(createTextCellFourth(str1));
            table.addCell(createTextCellFourth(" : "));
            table.addCell(createTextCellFourth(str2));
            doc.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CreateParagraphAlignCenterBorder(Document document, PdfWriter writer) {
        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect = new Rectangle(30, 30, 559, 806);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2);
        canvas.rectangle(rect);
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public static PdfPCell createImageCell(Image path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        cell.setFixedHeight(80f);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
    public static PdfPCell createImageCellRight(Image path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        cell.setFixedHeight(100f);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
    public static PdfPCell createTextCellHeading(String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD | Font.ITALIC);
        Paragraph p = new Paragraph(text);
        p.setFont(boldFont);
        p.setAlignment(Element.ALIGN_RIGHT);

        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
    public static PdfPCell createTextCellHeadingLeft(String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD | Font.ITALIC);
        Paragraph p = new Paragraph(text);
        p.setFont(boldFont);
        p.setAlignment(Element.ALIGN_LEFT);

        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
    public static Image convertBitmapToImage(String strBitmap) {
        Image myImg = null;
        try {
            byte[] encodeByte = Base64.decode(strBitmap.getBytes(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

            //remove Bitmap black background
            Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            Canvas canvas = new Canvas(newBitmap);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap, 0, 0, null);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(newBitmap, 75, 50, false);
            resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            myImg = Image.getInstance(stream.toByteArray());
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myImg;
    }
    public static PdfPCell createTextCell(String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Font font=new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD);

        Paragraph p = new Paragraph(text);
        p.setFont(font);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static PdfPCell createTextCellLeft(String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }

    public static PdfPCell createTextCellFourth(String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static PdfPCell createCellFourth() throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph();
        p.setTabSettings(new TabSettings());
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static void createTable(Document document, int numColumn, float[] floats, String key, String value) {
        PdfPTable table = new PdfPTable(numColumn);
        table.setWidthPercentage(100);
        try {
            table.setWidths(floats);
            table.setPaddingTop(6);
            table.addCell(S.createTextCellLeft(key));
            table.addCell(S.createTextCellLeft(value));
            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTableResumeFive(Document document, int numColumn, float[] floats, String key, String value) {
        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{0.4f, 1, 3});
            table.addCell(createCellFourth());
            table.addCell(S.createTextCellLeft(key));
            table.addCell(S.createTextCellLeft(value));
            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void createLineSeparator(Document document) {
        LineSeparator ls = new LineSeparator();
        try {
            document.add(new Chunk(ls));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void createLineSeparatorWithColor(Document document) {
        LineSeparator ls = new LineSeparator();
        ls.setLineColor(BaseColor.DARK_GRAY);
        try {
            document.add(new Chunk(ls));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
