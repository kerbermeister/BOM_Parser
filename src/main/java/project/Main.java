package project;

import org.apache.poi.ss.usermodel.Row;
import project.BomBuilder.ExLuckBomBuilder;
import project.BomBuilder.RowTemplate;
import project.Formatters.TextFormatter;
import project.Matchers.Matcher;
import project.Matchers.MatcherImpl;
import project.PartsPatterns.TvPartsPatterns;
import project.Saver.FileSaver;
import project.Saver.TestFileSaver;

import javax.xml.soap.Text;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:/demo/книга122.xls"));
        ExcelReader excelReader = new ExcelReader(fis);

        Matcher testMatcher = new MatcherImpl(new TvPartsPatterns());

        Map<Row, Parts> map = testMatcher.getMainParts(excelReader.getExcelList());
        Iterator<Row> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            System.out.println("part: " + row + " | " + map.get(row));
        }

//        Iterator<Row> iterator = map2.keySet().iterator();
//        while (iterator.hasNext()) {
//            Row row = iterator.next();
//            System.out.println("part: " + row + " | " + map2.get(row));
//        }
//

//
        ExLuckBomBuilder exLuckBomBuilder = new ExLuckBomBuilder(2,4,5, 1);
        ArrayList<RowTemplate> rowTemplateArrayList = exLuckBomBuilder.createRowTemplateList(map);
//        FileSaver fileSaver = new FileSaver(map);
//        fileSaver.save();

        TestFileSaver testFileSaver = new TestFileSaver(0,
                1 , 4 , 5, 6 , 13);
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
