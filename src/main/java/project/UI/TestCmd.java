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
    private static void startMessage() {
        System.out.println("====================Bom parser=====================");
        System.out.println("=======This is just a beta 0.1 version, guys=======");
        System.out.println("=====================by jeer0======================");
        System.out.println("===========any questions and suggestions===========");
        System.out.println("============find me on telegram @jeeero============");
        System.out.println("===================================================");
        System.out.println();
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        startMessage();

        while (true) {
            String path;
            int partNumberColumn, descColumn, specColumn, partNumberColumnOffset;
            Config config;

            Scanner scanner = new Scanner(System.in);

            System.out.print("/$ : [CONFIG] Enter part number column: ");
            partNumberColumn = scanner.nextInt();
            System.out.print("/$ : [CONFIG] Enter description column: ");
            descColumn = scanner.nextInt();
            System.out.print("/$ : [CONFIG] Enter specification column: ");
            specColumn = scanner.nextInt();
            System.out.print("/$ : [CONFIG] Enter part number column offset: ");
            partNumberColumnOffset = scanner.nextInt();
            scanner.nextLine();
            System.out.print("/$ : [CONFIG] Enter the path where BOM-files stored: ");
            path = scanner.nextLine();


            try {
                config = new Config(path, partNumberColumn, descColumn, specColumn, partNumberColumnOffset);
            } catch (IllegalColumnNumException e) {
                e.printMsg();
                continue;
            }
            System.out.println();
            System.out.println("Starting...");
            long startTime = System.currentTimeMillis();
            AbstractController controller = new OneSheetController(config, new TvPartsPatterns(), new PatternsToIgnore());
            controller.launch();
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("========================================================================");
            System.out.println("Process has finished, total time: " + endTime + " ms");
            System.out.println("========================================================================");
            System.out.println();
        }
    }
}
