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
    private static final int maxColumnNum = 255;
    private static final int maxRowNum = 1024;

    public static void main(String[] args) throws IOException, FileNotFoundException, InvalidFormatException {
        Long startTime = System.currentTimeMillis();

        String directoryPath = "E:\\!DOCUMENTATION\\exluck\\87-2018-7\\T.D7\\bom\\";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        for (File file : files) {
            if (!file.isFile()) {
                continue;
            } else if (file.isHidden()) {
                System.out.println("file " + file.getName() + " is hidden, cannot be processed");
                continue;
            } else if (!getFileExtension(file.getName()).equals("xlsx")) {
                continue;
            }

            Long fileProcessingStartTime = System.currentTimeMillis();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

            Iterator<Sheet> sheetIterator = xssfWorkbook.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet xssfSheet = sheetIterator.next();
                Iterator<Row> rowIterator = xssfSheet.rowIterator();
                Sheet hssfSheet = hssfWorkbook.createSheet(xssfSheet.getSheetName());
                int rowNum = 0;

                while (rowIterator.hasNext()) {
                    Row xssfRow = rowIterator.next();
                    Iterator<Cell> cellIterator = xssfRow.cellIterator();
                    Row hssfRow = hssfSheet.createRow(rowNum);
                    rowNum++;
                    if (rowNum >= maxRowNum) {
                        break;
                    }
                    int cellNum = 0;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        cell.setCellType(CellType.STRING);
                        Cell hssfCell = hssfRow.createCell(cellNum);
                        hssfCell.setCellType(CellType.STRING);
                        hssfCell.setCellValue(cell.getStringCellValue());
                        cellNum++;
                        if (cellNum >= maxColumnNum) {
                            break;
                        }
                    }
                }
            }

            File outputFile = new File(directoryPath + file.getName().substring(0, file.getName().lastIndexOf(".")) + ".xls");
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();

            Long fileProcessingEndTime = System.currentTimeMillis();
            Long totalFileProcessingTime = fileProcessingEndTime - fileProcessingStartTime;
            System.out.println("file " + file.getName() + " has been succesfully converted and saved as " + outputFile.getName() + ", it took " + totalFileProcessingTime + " ms");
        }
        Long endTime = System.currentTimeMillis();
        Long totalWorkingTime = (endTime - startTime);
        System.out.println("Total time is : " + totalWorkingTime + " ms");
    }

    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".")+1);
        }
        return null;
    }
}
