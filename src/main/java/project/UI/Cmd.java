package project.UI;

import project.Controllers.ConfigEntity;
import project.Controllers.Controller;
import project.Controllers.ExLuckController;
import project.Controllers.SvaController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Cmd {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        while(true) {
            int chooseManufacturer;
            Scanner scanner = new Scanner(System.in);
            ConfigEntity configEntity = new ConfigEntity();
            System.out.println("<information to be added>");
            System.out.println("Enter '1' if you'd like to parse ExpressLuck bom-files");
            System.out.println("Enter '2' if you'd like to parse SVA bom-files");
            chooseManufacturer = scanner.nextInt();
            System.out.print("Select Excel version: 1 - below 2007, 2 - higher than 2007: ");
            configEntity.setExcelVersion(scanner.nextInt());
            System.out.print("Enter part number column: ");
            configEntity.setPartNumberColumn(scanner.nextInt());
            System.out.print("Enter description column: ");
            configEntity.setDescColumn(scanner.nextInt());
            System.out.print("Enter specification column: ");
            configEntity.setSpecColumn(scanner.nextInt());
            System.out.print("Enter part number column offset: ");
            configEntity.setPartNumberColumnOffset(scanner.nextInt());
            System.out.print("Enter folder to save processed BOM-files: ");
            configEntity.setFolderToSave(scanner.next());

            switch (chooseManufacturer) {
                case 1:
                    System.out.print("Enter folder where ExpressLuck BOM-files stored: ");
                    configEntity.setFilePath(scanner.next());
                    System.out.print("Enter sheet index (starts from 0) with BOM-list: ");
                    configEntity.setSheetIndex(scanner.nextInt());
                    Controller exLuckController = new ExLuckController(configEntity);
                    exLuckController.launch();
                    break;

                case 2:
                    System.out.print("Enter the full name of folder and file name of SVA BOM-file: ");
                    configEntity.setFilePath(scanner.next());
                    Controller svaController = new SvaController(configEntity);
                    svaController.launch();
                    break;
            }
        }
    }
}
