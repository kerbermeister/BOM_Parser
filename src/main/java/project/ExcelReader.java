package project;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import project.XlsxConverter.Exceptions.IllegalSheetIndexException;

import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    private Workbook workbook;

    public ExcelReader(Workbook workbook) {
        this.workbook = workbook;
    }

    public int getNumberOfSheets() {
        return workbook.getNumberOfSheets();
    }

    public Iterator<Row> getExcelList(int sheetIndex) throws IllegalSheetIndexException {
        if (sheetIndex + 1 > workbook.getNumberOfSheets())
            throw new IllegalSheetIndexException();
        return workbook.getSheetAt(sheetIndex).rowIterator();
    }

    public String getSheetName(int sheetIndex) {
        return workbook.getSheetAt(sheetIndex).getSheetName();
    }
}
