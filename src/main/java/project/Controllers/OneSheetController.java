package project.Controllers;

import org.apache.poi.ss.usermodel.Row;
import project.BomBuilder.AbstractBomBuilder;
import project.BomBuilder.BomBuilderImpl;
import project.BomBuilder.RowTemplate;
import project.ExcelReader;
import project.Matchers.Matcher;
import project.Parts;
import project.PartsPatterns.Patterns;
import project.PartsPatterns.PatternsToIgnore;
import project.XlsxConverter.Exceptions.IllegalSheetIndexException;

import java.util.ArrayList;
import java.util.Map;

public class OneSheetController extends AbstractController {

    public OneSheetController(Config config, Patterns patterns, PatternsToIgnore patternsToIgnore) {
        super(config, patterns, patternsToIgnore);
    }

    protected ArrayList<RowTemplate> processFile(ExcelReader excelReader, Matcher matcher, int sheetindex) throws IllegalSheetIndexException {
        AbstractBomBuilder bomBuilder;
        Map<Row, Parts> map;

        map = matcher.getMainParts(excelReader.getExcelList(sheetindex), config.getDescColumn());

        bomBuilder = new BomBuilderImpl(config.getPartNumberColumn(), config.getDescColumn(), config.getSpecColumn(), config.getPartNumberColumnOffset());

        return bomBuilder.createRowTemplateList(map);
    }
}
