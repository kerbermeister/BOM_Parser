package project.Controllers.TestControllers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import project.Controllers.Config;
import project.Controllers.TestControllers.Exceptions.EmptyProcessedFolderException;
import project.ExcelReader;
import project.Matchers.Matcher;
import project.Matchers.MatcherImpl;
import project.PartsPatterns.Patterns;
import project.PartsPatterns.PatternsToIgnore;
import project.XlsxConverter.Exceptions.InvalidPathException;
import project.XlsxConverter.XlsxConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class AbstractController {

    private Config config;
    private Patterns patterns;
    private PatternsToIgnore patternsToIgnore;

    public AbstractController(Config config, Patterns patterns, PatternsToIgnore patternsToIgnore) {
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
        int fileNumber = 1;

        for (File file : convertedFiles) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ExcelReader excelReader = new ExcelReader(new HSSFWorkbook(fileInputStream));
            int numberOfSheets = excelReader.getNumberOfSheets();


        }

    }


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
