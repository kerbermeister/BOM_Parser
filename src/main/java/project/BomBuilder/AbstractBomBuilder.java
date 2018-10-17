package project.BomBuilder;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import project.Parts;

import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractBomBuilder {

    protected int partNumberColumn;
    protected int descColumn;
    protected int specColumn;

    abstract ArrayList<RowTemplate> createRowTemplateList(Map<Row, Parts> mainPartsMap);

    private Sections matchSection(Parts partType) {
        return Sections.DEFAULT;
    }

    abstract HSSFWorkbook buildBom(ArrayList<RowTemplate> rowTemplateList);

    abstract RowTemplate buildBomRow(Sections sections, Row row, int partNumberColumn, int descColumn, int specColumn, Parts partType);

}
