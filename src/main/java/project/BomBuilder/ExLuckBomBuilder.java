package project.BomBuilder;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import project.Parts;

import java.util.*;

public class ExLuckBomBuilder extends AbstractBomBuilder {

    public ExLuckBomBuilder(int partNumberColumn, int descColumn, int specColumn) {
        this.partNumberColumn = partNumberColumn;
        this.descColumn = descColumn;
        this.specColumn = specColumn;
    }

    public HSSFWorkbook buildBom(ArrayList<RowTemplate> rowTemplatesList) {
        return null;
    }

    public ArrayList<RowTemplate> createRowTemplateList(Map<Row, Parts> mainPartsMap) {
        ArrayList<RowTemplate> rowTemplatesList = new ArrayList<RowTemplate>();

        Set<Map.Entry<Row, Parts>> set = mainPartsMap.entrySet();
        Iterator<Map.Entry<Row, Parts>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Row, Parts> entry = iterator.next();
            Parts partType = entry.getValue();
            Row row = entry.getKey();

            String section = partType.getSection();
            System.out.println(section);  // just a logger, to be deleted
            RowTemplate rowTemplate = buildBomRow(section, row, partNumberColumn, descColumn, specColumn, partType);
            rowTemplatesList.add(rowTemplate);
        }
        return rowTemplatesList;
    }

    protected RowTemplate buildBomRow(String section, Row row, int partNumberColumn, int descColumn, int specColumn, Parts partType)
    {
        RowTemplate rowTemplate = new RowTemplate();
        rowTemplate.setSection(section);

        Iterator<Cell> cellIterator = row.cellIterator();
        ArrayList<Cell> cellArrayList = new ArrayList<Cell>();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            cellArrayList.add(cell);
        }

        rowTemplate.setPart(cellArrayList.get(partNumberColumn-1).getStringCellValue());
        rowTemplate.setDesc(cellArrayList.get(descColumn-1).getStringCellValue());
        rowTemplate.setSpec(cellArrayList.get(specColumn-1).getStringCellValue());
        rowTemplate.setRl(Integer.toString(partType.getRepairLevel()));

        return rowTemplate;
    }
    
}
