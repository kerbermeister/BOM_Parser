package project.Controllers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import project.BomBuilder.BomBuilderImpl;
import project.BomBuilder.RowTemplate;
import project.ExcelReader;
import project.Formatters.TextFormatter;
import project.Matchers.Matcher;
import project.Matchers.MatcherImpl;
import project.Parts;
import project.PartsPatterns.PatternsToIgnore;
import project.PartsPatterns.TvPartsPatterns;
import project.Saver.FileSaver;
import project.XlsxConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SvaController implements Controller {

    private ConfigEntity configEntity;

    public SvaController(ConfigEntity configEntity) {
        this.configEntity = configEntity;
    }

    public void launch() throws IOException, FileNotFoundException, InvalidFormatException {
        String processedFolder = XlsxConverter.convertFiles(configEntity.getFilePath());
        File folder = new File(processedFolder);

        File[] files = folder.listFiles();
        Matcher testMatcher = new MatcherImpl(new TvPartsPatterns(), new PatternsToIgnore());
        int fileNumber = 1;

        for (File file : files) {

            FileInputStream fis = new FileInputStream(file);
            ExcelReader excelReader = new ExcelReader(new HSSFWorkbook(fis));
            int numberOfSheets = excelReader.getNumberOfSheets();


            for (int i = 0; i < numberOfSheets; i++) {
                BomBuilderImpl bomBuilderImpl;
                Map<Row, Parts> map = testMatcher.getMainParts(excelReader.getExcelList(i), configEntity.getDescColumn()-1);

                if (map.isEmpty()) {
                    map = testMatcher.getMainParts(excelReader.getExcelList(i), configEntity.getDescColumn());
                    bomBuilderImpl = new BomBuilderImpl(configEntity.getPartNumberColumn()+1,
                            configEntity.getDescColumn()+1,
                            configEntity.getSpecColumn()+1,
                            configEntity.getPartNumberColumnOffset());
                } else {
                    bomBuilderImpl = new BomBuilderImpl(configEntity.getPartNumberColumn(),
                            configEntity.getDescColumn(),
                            configEntity.getSpecColumn(),
                            configEntity.getPartNumberColumnOffset());
                }



                Iterator<Row> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    System.out.println("part: " + row + " | " + map.get(row));
                }



                ArrayList<RowTemplate> rowTemplateArrayList = bomBuilderImpl.createRowTemplateList(map);

                System.out.println("Size of row template list is: " + rowTemplateArrayList.size());
                Workbook workbook = new HSSFWorkbook();

                FileSaver fileSaver = new FileSaver(workbook, 0,
                        1 , 4 , 5, 6 , 13, excelReader.getSheetName(i) + "(" + fileNumber + ")" + ".xls");
                rowTemplateArrayList = TextFormatter.formatCells(rowTemplateArrayList);
                fileSaver.save(rowTemplateArrayList, processedFolder);



                for (RowTemplate rowTemplate : rowTemplateArrayList) {
                    System.out.println(rowTemplate.getSection());
                    System.out.println(rowTemplate.getSectionPart());
                    System.out.println(rowTemplate.getPart());
                    System.out.println(rowTemplate.getDesc());
                    System.out.println(rowTemplate.getSpec());
                    System.out.println(rowTemplate.getRl());
                    System.out.println("-----");
                }
            }
            fileNumber++;
            fis.close();
        }
    }
}
