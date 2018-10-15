package project;

import org.apache.poi.ss.usermodel.Row;
import project.CellCheckers.TvCellCheckerImpl;
import project.Matchers.TvMatcher;
import project.PartsPatterns.TvPartsPatterns;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:/demo/employee.xls"));
        ExcelReader excelReader = new ExcelReader(new TvMatcher(new TvCellCheckerImpl(new TvPartsPatterns())), fis);
        System.out.println(excelReader.getMainPartsRowTable().size());

        Map<Row, Parts> map = excelReader.getMainPartsRowTable();
        Iterator<Row> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            System.out.println("part: " + row + " | " + map.get(row));
        }

//        String s = "бук异体字вы,символы异体字,цифры и тд 异体字и тп";
//        s = s.replaceAll("[^A-Za-zА-Яа-я0-9]", " "); // удалится все кроме букв и цифр
//        System.out.println(s);
    }
}
