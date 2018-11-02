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
import project.XlsxConverter.Exceptions.InvalidPathException;
import project.XlsxConverter.XlsxConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SvaController implements Controller {

    private Config config;

    public SvaController(Config config) {
        this.config = config;
    }

    public void launch() throws IOException, InvalidFormatException {
        String processedFolder;

        try {
            processedFolder = XlsxConverter.convertFiles(config.getFilePath());
        } catch (InvalidPathException e) {
            System.out.println("/$ : ERROR!!! Provided path does not exist!");
            return;
        }

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
                Map<Row, Parts> map = testMatcher.getMainParts(excelReader.getExcelList(i), config.getDescColumn());

                if (map.isEmpty()) {
                    map = testMatcher.getMainParts(excelReader.getExcelList(i), config.getDescColumn());
                    bomBuilderImpl = new BomBuilderImpl(config.getPartNumberColumn()+1,
                            config.getDescColumn()+1,
                            config.getSpecColumn()+1,
                            config.getPartNumberColumnOffset());
                } else {
                    bomBuilderImpl = new BomBuilderImpl(config.getPartNumberColumn(),
                            config.getDescColumn(),
                            config.getSpecColumn(),
                            config.getPartNumberColumnOffset());
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
