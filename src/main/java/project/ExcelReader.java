package project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ExcelReader {
    private Matcher matcher;
    private FileInputStream fileInputStream;
    private HSSFWorkbook hssfWorkbook;
    private HSSFSheet hssfSheet;

    public ExcelReader() {

    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
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

    public HSSFSheet getHssfSheet() {
        return hssfSheet;
    }

    public void setHssfSheet(HSSFSheet hssfSheet) {
        this.hssfSheet = hssfSheet;
    }


    public ExcelReader(Matcher matcher, FileInputStream fileInputStream) throws IOException {
        this.matcher = matcher;
        this.fileInputStream = fileInputStream;
        hssfWorkbook = new HSSFWorkbook(fileInputStream);
        hssfSheet = hssfWorkbook.getSheetAt(0);
    }

    public Map<Row, Parts> getMainPartsRowTable() {
        Iterator<Row> rowIterator = hssfSheet.rowIterator();
        return matcher.getMainParts(rowIterator);
    }
}
