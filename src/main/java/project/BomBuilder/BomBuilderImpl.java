package project.BomBuilder;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import project.Parts;

import java.util.*;

public class BomBuilderImpl extends AbstractBomBuilder {
    private int partNumberColumnOffset;

    public BomBuilderImpl(int partNumberColumn, int descColumn, int specColumn, int partNumberColumnOffset) {
        this.partNumberColumn = partNumberColumn;
        this.descColumn = descColumn;
        this.specColumn = specColumn;
        this.partNumberColumnOffset = partNumberColumnOffset;
    }

    public HSSFWorkbook buildBom(ArrayList<RowTemplate> rowTemplatesList) {
        return null;
    }  //to delete

    public ArrayList<RowTemplate> createRowTemplateList(Map<Row, Parts> mainPartsMap) {
        ArrayList<RowTemplate> rowTemplatesList = new ArrayList<RowTemplate>();

        Set<Map.Entry<Row, Parts>> set = mainPartsMap.entrySet();
        Iterator<Map.Entry<Row, Parts>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Row, Parts> entry = iterator.next();
            Parts partType = entry.getValue();
            Row row = entry.getKey();
            RowTemplate rowTemplate = buildBomRow(row, partNumberColumn, descColumn, specColumn, partType);
            rowTemplatesList.add(rowTemplate);
        }
        return rowTemplatesList;
    }

    protected RowTemplate buildBomRow(Row row, int partNumberColumn, int descColumn, int specColumn, Parts partType)
    {
        RowTemplate rowTemplate = new RowTemplate();
        Iterator<Cell> cellIterator = row.cellIterator();
        ArrayList<Cell> cellArrayList = new ArrayList<Cell>();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            cellArrayList.add(cell);
        }

        String partNumber;
        if (cellArrayList.get(partNumberColumn-1).getStringCellValue().contentEquals(""))
            partNumber = cellArrayList.get((partNumberColumn-1)+partNumberColumnOffset).getStringCellValue();
        else
            partNumber = cellArrayList.get(partNumberColumn-1).getStringCellValue();

        rowTemplate.setPart(partNumber);
        rowTemplate.setDesc(cellArrayList.get(descColumn-1).getStringCellValue());
        rowTemplate.setSpec(cellArrayList.get(specColumn-1).getStringCellValue());
        rowTemplate.setRl(Integer.toString(partType.getRepairLevel()));
        rowTemplate.setSection(partType.getSection());
        rowTemplate.setSectionPart(partNumber);
        return rowTemplate;
    }

}
