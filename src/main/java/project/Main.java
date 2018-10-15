package project;

import org.apache.poi.ss.usermodel.Row;
import project.CellCheckers.TvCellCheckerImpl;
import project.Formatters.TextFormatter;
import project.Matchers.TvMatcher;
import project.PartsPatterns.TvPartsPatterns;
import project.Saver.FileSaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:/demo/книга122.xls"));
        ExcelReader excelReader = new ExcelReader(new TvMatcher(new TvCellCheckerImpl(new TvPartsPatterns())), fis);
        System.out.println(excelReader.getMainPartsRowTable().size());

        Map<Row, Parts> map = excelReader.getMainPartsRowTable();
        Iterator<Row> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            System.out.println("part: " + row + " | " + map.get(row));
        }

        FileSaver fileSaver = new FileSaver(map);
        fileSaver.save();

    }
}
