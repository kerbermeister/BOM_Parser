package project.Formatters;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;

public final class TextFormatter {

    public static String deleteChineseSymbols(String s) {
        String formattedString = s.replaceAll("[^A-Za-zА-Яа-я-^0-9-/.-/,]", "");
        return formattedString;
    }
}
