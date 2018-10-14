package project;

import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:/demo/employee.xls"));
        ExcelReader excelReader = new ExcelReader(new TvMatcher(new TvCellChecker()), fis);
        System.out.println(excelReader.getMainPartsRowTable().size());

        Map<Row, Parts> map = excelReader.getMainPartsRowTable();
        Iterator<Row> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            System.out.println("part: " + row + " | " + map.get(row));
        }
    }
}
