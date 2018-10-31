package project;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    private Workbook workbook;

    public ExcelReader(Workbook workbook) throws IOException {
        this.workbook = workbook;
    }

    public int getNumberOfSheets() {
        return workbook.getNumberOfSheets();
    }

    public Iterator<Row> getExcelList(int sheetIndex) {
        return workbook.getSheetAt(sheetIndex).rowIterator();
    }

    public String getSheetName(int sheetIndex) {
        return workbook.getSheetAt(sheetIndex).getSheetName();
    }
}
