package project.Saver;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import project.BomBuilder.RowTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileSaver {
    Workbook workbook;
    Sheet sheet;
    private int sectionColumn;
    private int sectionPartColumn;
    private int partNumberColumn;
    private int descColumn;
    private int specColumn;
    private int repairLvlColumn;

    public FileSaver(Workbook workbook,
                     int sectionColumn,
                     int sectionPartColumn,
                     int partNumberColumn,
                     int descColumn,
                     int specColumn,
                     int repairLvlColumn,
                     String sheetName) {
        this.workbook = workbook;
        this.sectionColumn = sectionColumn;
        this.sectionPartColumn = sectionPartColumn;
        this.partNumberColumn = partNumberColumn;
        this.descColumn = descColumn;
        this.specColumn = specColumn;
        this.repairLvlColumn = repairLvlColumn;
        this.sheet = workbook.createSheet(sheetName);

    }

    public boolean save(List<RowTemplate> rowTemplateList, String folderToSave) throws IOException, FileNotFoundException {
        int rowNum = 0;
        for (RowTemplate rowTemplate : rowTemplateList) {
            Row row = sheet.createRow(rowNum);
            row.createCell(sectionColumn).setCellValue(rowTemplate.getSection());
            row.createCell(sectionPartColumn).setCellValue(rowTemplate.getSectionPart());
            row.createCell(partNumberColumn).setCellValue(rowTemplate.getPart());
            row.createCell(descColumn).setCellValue(rowTemplate.getDesc());
            row.createCell(specColumn).setCellValue(rowTemplate.getSpec());
            row.createCell(repairLvlColumn).setCellValue(rowTemplate.getRl());
            rowNum++;
        }


        addCustomRowTemplate();

        File file = new File(folderToSave + "BOM_" + sheet.getSheetName().toUpperCase());
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        workbook.write(fileOutputStream);
        System.out.println("file has been saved");
        return true;
    }

    private void addCustomRowTemplate() {
        Row row = sheet.createRow(sheet.getLastRowNum()+1);
        row.createCell(sectionColumn).setCellValue("UNI");
        row.createCell(sectionPartColumn).setCellValue("P-LEVEL4");
        row.createCell(partNumberColumn).setCellValue("P-LEVEL4");
        row.createCell(descColumn).setCellValue("for C, D, R, L");
        row.createCell(specColumn).setCellValue("for C, D, R, L");
        row.createCell(repairLvlColumn).setCellValue("4");

        row = sheet.createRow(sheet.getLastRowNum()+1);
        row.createCell(sectionColumn).setCellValue("UNI");
        row.createCell(sectionPartColumn).setCellValue("P-LEVEL4");
        row.createCell(partNumberColumn).setCellValue("P-LEVEL5");
        row.createCell(descColumn).setCellValue("for IC, CPU");
        row.createCell(specColumn).setCellValue("for IC, CPU");
        row.createCell(repairLvlColumn).setCellValue("5");
    }

}
