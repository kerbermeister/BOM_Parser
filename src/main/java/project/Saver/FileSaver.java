package project.Saver;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import project.Parts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class FileSaver {
    private Map<Row, Parts> mainPartsMap;
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("Parts list");

    public FileSaver(Map<Row, Parts> mainPartsMap) {
        this.mainPartsMap = mainPartsMap;
    }

    public boolean save() throws FileNotFoundException, IOException {
        Iterator<Row> iterator = mainPartsMap.keySet().iterator();
        int savedFileRowNum = 0;

        while(iterator.hasNext()) {
            Row row = iterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Row savedFileRow = sheet.createRow(savedFileRowNum);
            savedFileRowNum++;
            int savedFileCellNum = 0;
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Cell savedFileCell = savedFileRow.createCell(savedFileCellNum);
                savedFileCell.setCellValue(cell.getStringCellValue());
                savedFileCellNum++;
            }
        }

        File file = new File("C:/Demo/saved.xls");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        System.out.println("file has been saved");
        return true;
    }
}
