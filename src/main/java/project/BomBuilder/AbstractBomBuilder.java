package project.BomBuilder;

import org.apache.poi.ss.usermodel.Row;
import project.Parts;
import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractBomBuilder {

     int partNumberColumn;
     int descColumn;
     int specColumn;

    public abstract ArrayList<RowTemplate> createRowTemplateList(Map<Row, Parts> mainPartsMap);

    abstract RowTemplate buildBomRow(Row row, int partNumberColumn, int descColumn, int specColumn, Parts partType);

}
