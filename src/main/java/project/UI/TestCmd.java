package project.UI;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import project.Controllers.Config;
import project.Controllers.Exceptions.IllegalColumnNumException;
import project.Controllers.TestControllers.AbstractController;
import project.Controllers.TestControllers.OneSheetController;
import project.PartsPatterns.PatternsToIgnore;
import project.PartsPatterns.TvPartsPatterns;

import java.io.IOException;
import java.util.Scanner;

public class TestCmd {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        while (true) {
            String path;
            int partNumberColumn, descColumn, specColumn, partNumberColumnOffset;
            Config config;

            Scanner scanner = new Scanner(System.in);
            System.out.println("<information to be added>");
            System.out.print("Enter part number column: ");
            partNumberColumn = scanner.nextInt();
            System.out.print("Enter description column: ");
            descColumn = scanner.nextInt();
            System.out.print("Enter specification column: ");
            specColumn = scanner.nextInt();
            System.out.print("Enter part number column offset: ");
            partNumberColumnOffset = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the path where BOM-files stored: ");
            path = scanner.nextLine();

            try {
                config = new Config(path, partNumberColumn, descColumn, specColumn, partNumberColumnOffset);
            } catch (IllegalColumnNumException e) {
                System.out.println("/$ : ERROR!!! One of entered column is incorrect!");
                System.out.println("/$ : 1) Columns should not be equal to each other!");
                System.out.println("/$ : 2) P/N column offset + P/N column should not be equal to DESC or SPEC column!");
                System.out.println("/$ : 3) P/N, DESC, SPEC columns should not be negative or equal to 0");
                continue;
            }

            AbstractController controller = new OneSheetController(config, new TvPartsPatterns(), new PatternsToIgnore());
            controller.launch();
        }
    }
}
