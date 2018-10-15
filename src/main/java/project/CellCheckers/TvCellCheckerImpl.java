package project.CellCheckers;

import org.apache.poi.ss.usermodel.Cell;
import project.PartsPatterns.TvPartsPatterns;

public class TvCellCheckerImpl implements TvCellChecker {
    private TvPartsPatterns tvPartsPatterns;

    public TvCellCheckerImpl(TvPartsPatterns tvPartsPatterns) {
        this.tvPartsPatterns = tvPartsPatterns;
    }

    public boolean containsMainboard(Cell cell) {
        for (String str : tvPartsPatterns.mainBoardTemplates) {
            if (checkForMainboard(cell.getStringCellValue(), str)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsLcd(Cell cell) {
        for (String str : tvPartsPatterns.ledPanelTemplates) {
            if (checkForLcd(cell.getStringCellValue(), str)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsSpeaker(Cell cell) {
        for (String str : tvPartsPatterns.speakerTemplates) {
            if (checkForSpeaker(cell.getStringCellValue(), str)) {
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

    private boolean checkForLcd(String cellString, String template) {
        if (cellString.contains(template))
            return true;
        else
            return false;
    }

    private boolean checkForSpeaker(String cellString, String template) {
        if (cellString.contains(template))
            return true;
        else
            return false;
    }
}
