package project;

import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;
import java.util.List;

public class TvCellChecker {
    private List<String> mainBoardTemplates;

    public TvCellChecker() {
        mainBoardTemplates = new ArrayList<String>();
        mainBoardTemplates.add("mainboard");
        mainBoardTemplates.add("main board");
        mainBoardTemplates.add("maineboard");
        mainBoardTemplates.add("MAINBOARD");
        mainBoardTemplates.add("Mainboard");
        mainBoardTemplates.add("Main board");
    }

    public boolean containsMainboard(Cell cell) {
        for (String str : mainBoardTemplates) {
            if (checkForMainboard(cell.getStringCellValue(), str)) {
                return true;
            }
        }
        return false;
    }


    private boolean checkForMainboard(String cellString, String template) {
        if (cellString.contains(template))
                return true;
        else
            return false;
    }
}
