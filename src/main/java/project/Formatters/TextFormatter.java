package project.Formatters;

import project.BomBuilder.RowTemplate;
import java.util.ArrayList;

public final class TextFormatter {

    private static String format(String s) {
        if (s.contains("Ω")) {
            s = s.replaceAll("Ω", " Ohm");
        }
        s = s.replaceAll("[^A-Za-zА-Яа-я-^0-9-/.-/,\\s,\\(,\\),\\_]", "");
        return s;
    }

    public static ArrayList<RowTemplate> formatCells(ArrayList<RowTemplate> rowTemplateList) {
        ArrayList<RowTemplate> formattedRowTemplateList = new ArrayList<RowTemplate>();
        for (RowTemplate rowTemplate : rowTemplateList ) {
            String cellString = rowTemplate.getDesc();
            cellString = format(cellString);
            rowTemplate.setDesc(cellString);

            cellString = rowTemplate.getSpec();
            cellString = format(cellString);
            rowTemplate.setSpec(cellString);
            formattedRowTemplateList.add(rowTemplate);
        }
        return formattedRowTemplateList;
    }
}
