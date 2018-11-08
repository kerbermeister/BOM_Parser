package project.Saver;

import org.apache.poi.ss.usermodel.*;
import project.BomBuilder.RowTemplate;
import project.Saver.Exceptions.EmptyFileToSaveException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileSaver {
    private Workbook workbook;
    private Sheet sheet;
    private int sectionColumn;
    private int sectionPartColumn;
    private int partNumberColumn;
    private int descColumn;
    private int specColumn;
    private int repairLvlColumn;
    private String fileName;
    private final int COLUMN_QTY = 15;

    public FileSaver(Workbook workbook,
                     int sectionColumn,
                     int sectionPartColumn,
                     int partNumberColumn,
                     int descColumn,
                     int specColumn,
                     int repairLvlColumn,
                     String sheetName,
                     String fileName) {
        this.workbook = workbook;
        this.sectionColumn = sectionColumn;
        this.sectionPartColumn = sectionPartColumn;
        this.partNumberColumn = partNumberColumn;
        this.descColumn = descColumn;
        this.specColumn = specColumn;
        this.repairLvlColumn = repairLvlColumn;
        this.sheet = workbook.createSheet(sheetName);
        this.fileName = fileName;
    }

    public void save(List<RowTemplate> rowTemplateList, String folderToSave) throws IOException, EmptyFileToSaveException {
        if (rowTemplateList.isEmpty()) {
            throw new EmptyFileToSaveException();
        }

        int rowNum = 0;
        for (RowTemplate rowTemplate : rowTemplateList) {
            Row row = createEmptyRow(sheet.createRow(rowNum));
            row.getCell(sectionColumn).setCellValue(rowTemplate.getSection());
            row.getCell(sectionPartColumn).setCellValue(rowTemplate.getSectionPart());
            row.getCell(partNumberColumn).setCellValue(rowTemplate.getPart());
            row.getCell(descColumn).setCellValue(rowTemplate.getDesc());
            row.getCell(specColumn).setCellValue(rowTemplate.getSpec());
            row.getCell(repairLvlColumn).setCellValue(rowTemplate.getRl());
            rowNum++;
        }

        addCustomRowTemplate();
        File file = new File(folderToSave + "BOM_" + fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        workbook.write(fileOutputStream);
        System.out.println("file " + file.getName() + " has been saved");

        fileOutputStream.close();
    }

    private void addCustomRowTemplate() {
        Row row = createEmptyRow(sheet.createRow(sheet.getLastRowNum()+1));
        row.createCell(sectionColumn).setCellValue("UNI");
        row.createCell(sectionPartColumn).setCellValue("P-LEVEL4");
        row.createCell(partNumberColumn).setCellValue("P-LEVEL4");
        row.createCell(descColumn).setCellValue("for C, D, R, L");
        row.createCell(specColumn).setCellValue("for C, D, R, L");
        row.createCell(repairLvlColumn).setCellValue("4");

        row = createEmptyRow(sheet.createRow(sheet.getLastRowNum()+1));
        row.createCell(sectionColumn).setCellValue("UNI");
        row.createCell(sectionPartColumn).setCellValue("P-LEVEL4");
        row.createCell(partNumberColumn).setCellValue("P-LEVEL5");
        row.createCell(descColumn).setCellValue("for IC, CPU");
        row.createCell(specColumn).setCellValue("for IC, CPU");
        row.createCell(repairLvlColumn).setCellValue("5");
    }

    private Row createEmptyRow(Row row) {
        for (int i = 0; i <= COLUMN_QTY-1; i++) {
            Cell cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
        }
        return row;
    }
}
