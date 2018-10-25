package project.Controllers;

import org.apache.poi.ss.usermodel.Row;
import project.BomBuilder.BomBuilderImpl;
import project.BomBuilder.RowTemplate;
import project.ExcelReader;
import project.Formatters.TextFormatter;
import project.Matchers.Matcher;
import project.Matchers.MatcherImpl;
import project.Parts;
import project.PartsPatterns.TvPartsPatterns;
import project.Saver.TestFileSaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SvaController implements Controller {

    public void launch() throws IOException, FileNotFoundException {

        File file = new File("C:/demo/original BOMs/18IRL0302_BBK_BOM_list.xls");
        FileInputStream fis = new FileInputStream(file);
        ExcelReader excelReader = new ExcelReader(fis);



        Matcher testMatcher = new MatcherImpl(new TvPartsPatterns());

        Map<Row, Parts> map = testMatcher.getMainParts(excelReader.getExcelList(1));


        Iterator<Row> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            System.out.println("part: " + row + " | " + map.get(row));
        }

        BomBuilderImpl bomBuilderImpl = new BomBuilderImpl(2,3,4, -1);
        ArrayList<RowTemplate> rowTemplateArrayList = bomBuilderImpl.createRowTemplateList(map);


        TestFileSaver testFileSaver = new TestFileSaver(0,
                1 , 4 , 5, 6 , 13, file.getName());
        rowTemplateArrayList = TextFormatter.formatCells(rowTemplateArrayList);
        testFileSaver.save(rowTemplateArrayList);

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
