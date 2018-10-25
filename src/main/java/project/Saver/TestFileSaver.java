package project.Saver;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import project.BomBuilder.RowTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TestFileSaver {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet;
    private int sectionColumn;
    private int sectionPartColumn;
    private int partNumberColumn;
    private int descColumn;
    private int specColumn;
    private int repairLvlColumn;

    public TestFileSaver(int sectionColumn,
                         int sectionPartColumn,
                         int partNumberColumn,
                         int descColumn,
                         int specColumn,
                         int repairLvlColumn,
                         String sheetName) {
        this.sectionColumn = sectionColumn;
        this.sectionPartColumn = sectionPartColumn;
        this.partNumberColumn = partNumberColumn;
        this.descColumn = descColumn;
        this.specColumn = specColumn;
        this.repairLvlColumn = repairLvlColumn;
        this.sheet = workbook.createSheet(sheetName);
    }

    public boolean save(List<RowTemplate> rowTemplateList) throws IOException, FileNotFoundException {
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

        File file = new File("C:/Demo/BOM_" + sheet.getSheetName().toUpperCase());
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        workbook.write(fileOutputStream);
        System.out.println("file has been saved");
        return true;
    }
}
