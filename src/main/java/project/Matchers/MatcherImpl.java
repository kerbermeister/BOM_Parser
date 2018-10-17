/*
    This is an implementation of Matcher and this implementation can actually work with any rowIterator and
    Pattern implementation given; That means it is not bound to any specific type of pattern. It will result
    with HashMap<Row, Parts> made after comparing all the elements of row-Iterator and pattern given;
 */

package project.Matchers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import project.Parts;
import project.PartsPatterns.Patterns;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MatcherImpl implements Matcher {

    private Patterns patterns;

    public MatcherImpl(Patterns patterns) {
        this.patterns = patterns;
    }

    public Map<Row, Parts> getMainParts(Iterator<Row> rowIterator) {
        Map<Row, Parts> mainPartsRowTable = new HashMap<Row, Parts>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.setCellType(CellType.STRING);
                Parts partType = checkCell(cell);
                if (partType == null) {
                    continue;
                } else {
                    mainPartsRowTable.put(row, partType);
                    continue;
                }
            }
        }
        return mainPartsRowTable;
    }

    private Parts checkCell(Cell cell) {
        Iterator<Map.Entry<List<String>, Parts>> mapIterator = patterns.getPatternsMap().entrySet().iterator();
        String cellString = cell.getStringCellValue();

        while(mapIterator.hasNext()) {
            Map.Entry<List<String>, Parts> entry = mapIterator.next();
            Parts partType = entry.getValue();
            List<String> partPatternList = entry.getKey();
            for (String str : partPatternList) {
                if (cellString.contains(str)) {
                    return partType;
                }
            }
        }
        return null;
    }
}
