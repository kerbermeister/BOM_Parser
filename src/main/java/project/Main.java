package project;

import project.Controllers.Controller;
import project.Controllers.ExLuckController;
import project.Controllers.SvaController;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, FileNotFoundException {
//        Controller controller = new ExLuckController("C:/demo/Exluck_test", "C:/Demo/");
        Controller controller = new SvaController("C:/demo/original BOMs/18IRL0302_BBK_BOM_list.xls", "C:/Demo/");
        controller.launch();
    }
}
