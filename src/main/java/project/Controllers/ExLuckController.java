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
import project.Saver.Exceptions.EmptyFileToSaveException;
import project.Saver.FileSaver;
import project.XlsxConverter.Exceptions.InvalidPathException;
import project.XlsxConverter.XlsxConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ExLuckController implements Controller {

    private Config config;

    public ExLuckController(Config config) {
        this.config = config;
    }

    public void launch() throws IOException, InvalidFormatException {
        String processedFolder;

        try{
            processedFolder = XlsxConverter.convertFiles(config.getFilePath());
        } catch (InvalidPathException e) {
            System.out.println("/$ : ERROR!!! Path you entered does not exist!");
            return;
        }

        File folder = new File(processedFolder);

        File[] files = folder.listFiles();
        Matcher testMatcher = new MatcherImpl(new TvPartsPatterns(), new PatternsToIgnore());


        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            ExcelReader excelReader = new ExcelReader(new HSSFWorkbook(fis));


            Map<Row, Parts> map = testMatcher.getMainParts(excelReader.getExcelList(config.getSheetIndex()), config.getDescColumn());




            Iterator<Row> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                System.out.println("part: " + row + " | " + map.get(row));
            }



            BomBuilderImpl bomBuilderImpl = new BomBuilderImpl(config.getPartNumberColumn(),
                                                                config.getDescColumn(),
                                                                config.getSpecColumn(),
                                                                config.getPartNumberColumnOffset());

            ArrayList<RowTemplate> rowTemplateArrayList = bomBuilderImpl.createRowTemplateList(map);

            Workbook workbook = new HSSFWorkbook();

            FileSaver fileSaver = new FileSaver(workbook,0,
                    1 , 4 , 5, 6 , 13, file.getName());
            rowTemplateArrayList = TextFormatter.formatCells(rowTemplateArrayList);
            try {
                fileSaver.save(rowTemplateArrayList, processedFolder);
            } catch (EmptyFileToSaveException e) {
                System.out.println("File " + file.getName() + " has a sheet without any founds, file has not been saved");
            }





            for (RowTemplate rowTemplate : rowTemplateArrayList) {
                System.out.println(rowTemplate.getSection());
                System.out.println(rowTemplate.getSectionPart());
                System.out.println(rowTemplate.getPart());
                System.out.println(rowTemplate.getDesc());
                System.out.println(rowTemplate.getSpec());
                System.out.println(rowTemplate.getRl());
                System.out.println("-----");
            }
            fis.close();
        }
    }
}
