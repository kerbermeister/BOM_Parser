package project.CellCheckers;

import org.apache.poi.ss.usermodel.Cell;

public interface TvCellChecker {

    boolean containsMainboard(Cell cell);
    boolean containsLcd(Cell cell);
    boolean containsSpeaker(Cell cell);
}
