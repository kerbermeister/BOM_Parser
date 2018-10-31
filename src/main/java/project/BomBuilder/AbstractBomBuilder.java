package project.BomBuilder;

import org.apache.poi.ss.usermodel.Row;
import project.Parts;
import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractBomBuilder {

    protected int partNumberColumn;
    protected int descColumn;
    protected int specColumn;

    abstract ArrayList<RowTemplate> createRowTemplateList(Map<Row, Parts> mainPartsMap);

    abstract RowTemplate buildBomRow(Row row, int partNumberColumn, int descColumn, int specColumn, Parts partType);

}
