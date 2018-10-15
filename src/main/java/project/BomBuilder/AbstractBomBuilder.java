package project.BomBuilder;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import project.Parts;

import java.util.Iterator;
import java.util.Map;

public abstract class AbstractBomBuilder {

    abstract HSSFWorkbook buildBom(Map<Row, Parts> mainPartsMap);

    protected RowTemplate buildBomRow(Sections sections, Row row) {
        RowTemplate rowTemplate = new RowTemplate();
        rowTemplate.setSection(sections.name());

        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

        }
        return rowTemplate;
    }
}
