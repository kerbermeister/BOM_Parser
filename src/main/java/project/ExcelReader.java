package project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    private FileInputStream fileInputStream;
    private HSSFWorkbook hssfWorkbook;

    public ExcelReader() {

    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public HSSFWorkbook getHssfWorkbook() {
        return hssfWorkbook;
    }

    public void setHssfWorkbook(HSSFWorkbook hssfWorkbook) {
        this.hssfWorkbook = hssfWorkbook;
    }


    public ExcelReader(FileInputStream fileInputStream) throws IOException {
        this.fileInputStream = fileInputStream;
        hssfWorkbook = new HSSFWorkbook(fileInputStream);
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
