package project.Controllers.TestControllers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import project.BomBuilder.RowTemplate;
import project.Controllers.Config;
import project.Controllers.TestControllers.Exceptions.EmptyProcessedFolderException;
import project.ExcelReader;
import project.Formatters.TextFormatter;
import project.Matchers.Matcher;
import project.Matchers.MatcherImpl;
import project.PartsPatterns.Patterns;
import project.PartsPatterns.PatternsToIgnore;
import project.Saver.Exceptions.EmptyFileToSaveException;
import project.Saver.FileSaver;
import project.XlsxConverter.Exceptions.IllegalSheetIndexException;
import project.XlsxConverter.Exceptions.InvalidPathException;
import project.XlsxConverter.XlsxConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractController {

    Config config;
    private Patterns patterns;
    private PatternsToIgnore patternsToIgnore;

    AbstractController(Config config, Patterns patterns, PatternsToIgnore patternsToIgnore) {
        this.config = config;
        this.patterns = patterns;
        this.patternsToIgnore = patternsToIgnore;
    }

    public void launch() throws IOException, InvalidFormatException {
        String processedFolder = convertFilesToXls(config.getFilePath());
        File[] convertedFiles;
        try {
            convertedFiles = getConvertedFilesList(processedFolder);
        } catch (EmptyProcessedFolderException e) {
            System.out.println("/$ : ERROR!!! No any files found for processing...");
            return;
        }

        Matcher matcher = new MatcherImpl(patterns, patternsToIgnore);
        int savedFiles = 0;

        Map<String, String> notSavedFiles = new HashMap<String, String>();

        for (File file : convertedFiles) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ExcelReader excelReader = new ExcelReader(new HSSFWorkbook(fileInputStream));
            int numberOfSheets = excelReader.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                ArrayList<RowTemplate> rowTemplateArrayList;
                try {
                    rowTemplateArrayList = processFile(excelReader, matcher, i);
                } catch (IllegalSheetIndexException e) {
                    System.out.println("/$ : ERROR!!! Sheet #(" + i + ") does not exist!!!");
                    return;
                }

                System.out.println("#" + i + " sheet from file " + file.getName() + " processed, total parts found: " + rowTemplateArrayList.size());

                Workbook workbook = new HSSFWorkbook();

                FileSaver fileSaver = new FileSaver(workbook, 0,
                        1 , 4 , 5, 6 , 13,  excelReader.getSheetName(i), "#" + i + " " + file.getName());



                rowTemplateArrayList = TextFormatter.formatCells(rowTemplateArrayList);
                try {
                    fileSaver.save(rowTemplateArrayList, processedFolder);
                    savedFiles++;
                    System.out.println("/$ : Sheet with parts has name: " + excelReader.getSheetName(i));
                    System.out.println("------------------------------------------------------------------------");
                } catch (EmptyFileToSaveException e) {
                    notSavedFiles.put(excelReader.getSheetName(i), file.getName());
                    System.out.println("/$ : WARNING!!! File " + file.getName() + " has a sheet #(" + i + ") without any founds, file has not been saved");
                    System.out.println("------------------------------------------------------------------------");
                }

//                for (RowTemplate rowTemplate : rowTemplateArrayList) {
//                    System.out.println(rowTemplate.getSection());
//                    System.out.println(rowTemplate.getSectionPart());
//                    System.out.println(rowTemplate.getPart());
//                    System.out.println(rowTemplate.getDesc());
//                    System.out.println(rowTemplate.getSpec());
//                    System.out.println(rowTemplate.getRl());
//                    System.out.println("-----");
//                }
            }
            System.out.println("/$ : Total files saved: " + savedFiles);
            System.out.println("/$ : Not saved sheets/files: ");

            for (Map.Entry entry : notSavedFiles.entrySet()) {
                System.out.println(entry.getKey() + " | file name: " + entry.getValue());
            }

            fileInputStream.close();
        }
    }

    abstract ArrayList<RowTemplate> processFile(ExcelReader excelReader, Matcher matcher, int sheetIndex) throws IllegalSheetIndexException;

    private String convertFilesToXls(String path) throws IOException, InvalidFormatException {
        try {
            return XlsxConverter.convertFiles(config.getFilePath());
        } catch (InvalidPathException e) {
            System.out.println("/$ : ERROR!!! Provided path does not exist!");
            return null;
        }
    }

    private File[] getConvertedFilesList(String processedFolder) throws EmptyProcessedFolderException {
        File folder = new File(processedFolder);

        if (folder.listFiles().length == 0)
            throw new EmptyProcessedFolderException();
        else
         return folder.listFiles();
    }
}
