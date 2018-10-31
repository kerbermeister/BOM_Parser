package project.Matchers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import project.Parts;
import project.PartsPatterns.Patterns;
import project.PartsPatterns.PatternsToIgnore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MatcherImpl implements Matcher {

    private Patterns patterns;
    private PatternsToIgnore patternsToIgnore;

    public MatcherImpl(Patterns patterns, PatternsToIgnore patternsToIgnore) {
        this.patterns = patterns;
        this.patternsToIgnore = patternsToIgnore;
    }

    public Map<Row, Parts> getMainParts(Iterator<Row> rowIterator, int descColumn) {
        Map<Row, Parts> mainPartsRowTable = new HashMap<Row, Parts>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            int currentColumn = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
//                cell.setCellType(CellType.STRING);
                if (currentColumn != descColumn) {
                    currentColumn++;
                    continue;
                } else if (currentColumn == descColumn){
                    Parts partType = checkCell(cell);
                    currentColumn++;
                    if (partType != null) {
                        mainPartsRowTable.put(row, partType);
                    }
                    break;
                }
//                else {
//                    currentColumn++;
//                    break;
//                }
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
                str = str.replaceAll("[^A-Za-zА-Яа-я]", "");
                String cellStringForComparing = cellString.toLowerCase().replaceAll("[^A-Za-zА-Яа-я]", "");
                if (cellStringForComparing.
                        contains(str.toLowerCase())) {
                    for (String ignore : PatternsToIgnore.ignoreList) {
                        ignore = ignore.replaceAll("[^A-Za-zА-Яа-я]", "");
                        if (cellStringForComparing.contains(ignore.toLowerCase())) {
                            return  null;
                        } else {
                            continue;
                        }
                    }
                    return partType;
                }
            }
        }
        return null;
    }
}

