package project;

import org.apache.poi.ss.usermodel.Row;
import project.BomBuilder.ExLuckBomBuilder;
import project.BomBuilder.RowTemplate;
import project.CellCheckers.TvCellCheckerImpl;
import project.Matchers.TestMatcherImpl;
import project.Matchers.TvMatcher;
import project.PartsPatterns.Patterns;
import project.PartsPatterns.TestTvPartsPatterns;
import project.PartsPatterns.TvPartsPatterns;
import project.Saver.FileSaver;

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
        ExcelReader excelReader = new ExcelReader(new TvMatcher(new TvCellCheckerImpl(new TvPartsPatterns())), fis);

        Map<Row, Parts> map = excelReader.getMainPartsRowTable();
        TestMatcherImpl testMatcher = new TestMatcherImpl(new TestTvPartsPatterns());

        Map<Row, Parts> map2 = testMatcher.getMainParts(excelReader.getExcelList());
        Iterator<Row> iterator = map2.keySet().iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            System.out.println("part: " + row + " | " + map2.get(row));
        }

//        Iterator<Row> iterator = map.keySet().iterator();
//        while (iterator.hasNext()) {
//            Row row = iterator.next();
//            System.out.println("part: " + row + " | " + map.get(row));
//        }
//
//        FileSaver fileSaver = new FileSaver(map);
//        fileSaver.save();
//
        ExLuckBomBuilder exLuckBomBuilder = new ExLuckBomBuilder(2,4,5);
        ArrayList<RowTemplate> rowTemplateArrayList = exLuckBomBuilder.createRowTemplateList(map2);
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
