package project.Controllers;

import org.apache.poi.ss.usermodel.Row;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ExLuckController implements Controller {

    private String filesFolder;
    private String folderToSave;

    public ExLuckController(String filesFolder, String folderToSave) {
        this.filesFolder = filesFolder;
        this.folderToSave = folderToSave;
    }

    public void launch() throws FileNotFoundException, IOException {


        File folder = new File(filesFolder);

        File[] files = folder.listFiles();
        Matcher testMatcher = new MatcherImpl(new TvPartsPatterns(), new PatternsToIgnore());


        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            ExcelReader excelReader = new ExcelReader(fis);


            Map<Row, Parts> map = testMatcher.getMainParts(excelReader.getExcelList(1));




            Iterator<Row> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                System.out.println("part: " + row + " | " + map.get(row));
            }



            BomBuilderImpl bomBuilderImpl = new BomBuilderImpl(2,4,5, 1);
            ArrayList<RowTemplate> rowTemplateArrayList = bomBuilderImpl.createRowTemplateList(map);


            FileSaver fileSaver = new FileSaver(0,
                    1 , 4 , 5, 6 , 13, file.getName());
            rowTemplateArrayList = TextFormatter.formatCells(rowTemplateArrayList);
            fileSaver.save(rowTemplateArrayList, folderToSave);




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
    }
}
