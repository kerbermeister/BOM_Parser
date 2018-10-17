package project.Matchers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import project.Parts;
import project.PartsPatterns.Patterns;
import project.PartsPatterns.TestTvPartsPatterns;
import project.PartsPatterns.TvPartsPatterns;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestMatcherImpl {

    private Patterns patterns;

    public TestMatcherImpl(Patterns patterns) {
        this.patterns = patterns;
    }

    public Map<Row, Parts> getMainParts(Iterator<Row> rowIterator) {
        Map<Row, Parts> mainPartsRowTable = new HashMap<Row, Parts>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
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
        String cellText = cell.getStringCellValue();
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
