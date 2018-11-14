package project.UI;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import project.Controllers.Config;
import project.Controllers.Exceptions.IllegalColumnNumException;
import project.Controllers.AbstractController;
import project.Controllers.OneSheetController;
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
                e.printMsg();
                continue;
            }

            AbstractController controller = new OneSheetController(config, new TvPartsPatterns(), new PatternsToIgnore());
            controller.launch();
            System.out.println();
        }
    }
}
