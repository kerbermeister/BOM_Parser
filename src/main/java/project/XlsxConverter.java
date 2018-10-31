package project;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class XlsxConverter {
    public static void main(String[] args) throws IOException, FileNotFoundException, InvalidFormatException {
        Long startTime = System.currentTimeMillis();

        String directoryPath = "E:\\!DOCUMENTATION\\exluck\\87-2018-7\\T.D7\\bom\\";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        for (File file : files) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

            Iterator<Sheet> sheetIterator = xssfWorkbook.sheetIterator();
            while (sheetIterator.hasNext()) {
                System.out.println("new sheet");
                Sheet xssfSheet = sheetIterator.next();
                Iterator<Row> rowIterator = xssfSheet.rowIterator();
                Sheet hssfSheet = hssfWorkbook.createSheet();
                int rowNum = 0;

                while (rowIterator.hasNext()) {
                    Row xssfRow = rowIterator.next();
                    Iterator<Cell> cellIterator = xssfRow.cellIterator();
                    Row hssfRow = hssfSheet.createRow(rowNum);
                    rowNum++;
                    System.out.println(rowNum);
                    System.out.println("new row");
                    int cellNum = 0;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        cell.setCellType(CellType.STRING);
                        Cell hssfCell = hssfRow.createCell(cellNum);
                        hssfCell.setCellType(CellType.STRING);
                        hssfCell.setCellValue(cell.getStringCellValue());
                        cellNum++;
                        System.out.println(cellNum);
                        if (cellNum > 20) {
                            break;
                        }
                    }
                }
            }

            File outputFile = new File(directoryPath + file.getName().substring(0, file.getName().indexOf(".")) + ".xls");
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("kek");

            Long endTime = System.currentTimeMillis();
            Long totalWorkingTime = (endTime - startTime);
            System.out.println(totalWorkingTime);
        }
    }
}
