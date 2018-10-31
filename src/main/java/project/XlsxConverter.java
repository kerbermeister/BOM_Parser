package project;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Iterator;

public class XlsxConverter {
    private static final int maxColumnNum = 255;
    private static final int maxRowNum = 1024;
    private static final String processedDirectoryName = "\\processed";

    public static String convertFiles(String directoryPath) throws IOException, FileNotFoundException, InvalidFormatException {
        Long startTime = System.currentTimeMillis();

        File directory = new File(directoryPath);

        File directoryToDelete = new File(directoryPath + processedDirectoryName);
        if (directoryToDelete.exists()) {
            deleteProcessedDirectory(directoryToDelete);
            System.out.println("/$ the old processed directory and all the nested directories and files have been deleted recursively");
        }

        new File(directoryPath + processedDirectoryName).mkdir();
        System.out.println("/$ the new directory for processed files has been created");
        File[] files = directory.listFiles();
        int fileNumber = 1;

        for (File file : files) {
            if (!file.isFile()) {
                continue;
            } else if (file.isHidden()) {
                System.out.println("file " + file.getName() + " is hidden, cannot be processed");
                continue;
            } else if (!getFileExtension(file.getName()).equals("xlsx") && !getFileExtension(file.getName()).equals("xls")) {
                continue;
            }

            Long fileProcessingStartTime = System.currentTimeMillis();

            Workbook workbook;
            if (getFileExtension(file.getName()).equals("xlsx")) {
                workbook = new XSSFWorkbook(file);
            } else {
                FileInputStream fileInputStream = new FileInputStream(file);
                workbook = new HSSFWorkbook(fileInputStream);
            }

            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
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

            File outputFile = new File(directoryPath + processedDirectoryName + "\\" + "(" + fileNumber + ")" + file.getName().substring(0, file.getName().lastIndexOf(".")) + ".xls");
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();

            Long fileProcessingEndTime = System.currentTimeMillis();
            Long totalFileProcessingTime = fileProcessingEndTime - fileProcessingStartTime;
            System.out.println("/$ file " + file.getName() + " has been succesfully converted and saved as " + outputFile.getName() + ", it took " + totalFileProcessingTime + " ms");
            fileNumber++;
        }
        Long endTime = System.currentTimeMillis();
        Long totalWorkingTime = (endTime - startTime);
        System.out.println("/$ Total time is : " + totalWorkingTime + " ms");
        return directoryPath + processedDirectoryName + "\\";
    }


    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".")+1);
        }
        return null;
    }


    private static void deleteProcessedDirectory(File file) {
        if(!file.exists())
            return;
        if(file.isDirectory())
        {
            for(File f : file.listFiles())
                deleteProcessedDirectory(f);
            file.delete();
        }
        else
        {
            file.delete();
        }
    }
}
