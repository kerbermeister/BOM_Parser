package project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    private FileInputStream fileInputStream;
    private XSSFWorkbook hssfWorkbook;

    public ExcelReader() {

    }

    public ExcelReader(FileInputStream fileInputStream) throws IOException {
        this.fileInputStream = fileInputStream;
        hssfWorkbook = new XSSFWorkbook(fileInputStream);
    }

    public int getNumberOfSheets() {
        return hssfWorkbook.getNumberOfSheets();
    }


    public Iterator<Row> getExcelList(int sheetIndex) {
        return hssfWorkbook.getSheetAt(sheetIndex).rowIterator();
    }

    public String getSheetName(int sheetIndex) {
        return hssfWorkbook.getSheetAt(sheetIndex).getSheetName();
    }
}
