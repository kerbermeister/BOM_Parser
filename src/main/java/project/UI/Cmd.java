package project.UI;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import project.Controllers.Config;
import project.Controllers.Controller;
import project.Controllers.ExLuckController;
import project.Controllers.Exceptions.IllegalColumnNumException;
import project.Controllers.SvaController;

import java.io.IOException;
import java.util.Scanner;

public class Cmd {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        while(true) {
            int chooseManufacturer;
            String path;
            int partNumberColumn, descColumn, specColumn, partNumberColumnOffset, sheetIndex;
            Config config;

            Scanner scanner = new Scanner(System.in);
            System.out.println("<information to be added>");
            System.out.println("Enter '1' if you'd like to parse ExpressLuck bom-files");
            System.out.println("Enter '2' if you'd like to parse SVA bom-files");
            chooseManufacturer = scanner.nextInt();

            System.out.print("Enter part number column: ");
            partNumberColumn = scanner.nextInt();
            System.out.print("Enter description column: ");
            descColumn = scanner.nextInt();
            System.out.print("Enter specification column: ");
            specColumn = scanner.nextInt();
            System.out.print("Enter part number column offset: ");
            partNumberColumnOffset = scanner.nextInt();

            switch (chooseManufacturer) {
                case 1:
                    System.out.print("Enter the path where ExpressLuck BOM-files stored (path should be without spaces: ");
                    path = scanner.next();
                    System.out.print("Enter sheet index (starts from 0) with BOM-list: ");
                    sheetIndex = scanner.nextInt();

                    try {
                        config = new Config(path, partNumberColumn, descColumn, specColumn, partNumberColumnOffset, sheetIndex);
                    } catch (IllegalColumnNumException e) {
                        System.out.println("/$ : ERROR!!! One of entered column is incorrect!");
                        System.out.println("/$ : 1) Columns should not be equal to each other!");
                        System.out.println("/$ : 2) P/N column offset + P/N column should not be equal to DESC or SPEC column!");
                        System.out.println("/$ : 3) P/N, DESC, SPEC columns should not be negative or equal to 0");
                        continue;
                    }

                    Controller exLuckController = new ExLuckController(config);
                    exLuckController.launch();
                    break;

                case 2:
                    System.out.print("Enter the path where SVA BOM-files stored (path should be without spaces): ");
                    path = scanner.next();
                    System.out.println(path);

                    try {
                        config = new Config(path, partNumberColumn, descColumn, specColumn, partNumberColumnOffset);
                    } catch (IllegalColumnNumException e) {
                        System.out.println("/$ : ERROR!!! One of entered column is incorrect! ");
                        System.out.println("/$ : 1) Columns should not be equal to each other!");
                        System.out.println("/$ : 2) P/N column offset + P/N column should not be equal to DESC or SPEC column!");
                        System.out.println("/$ : 3) P/N, DESC, SPEC columns should not be negative or equal to 0");
                        continue;
                    }

                    Controller svaController = new SvaController(config);
                    svaController.launch();
                    break;
            }
        }
    }
}
