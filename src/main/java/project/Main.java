package project;

import project.Controllers.Controller;
import project.Controllers.ExLuckController;
import project.Controllers.SvaController;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, FileNotFoundException {
        Controller controller = new ExLuckController("C:/Users/jegbs/Desktop/bom/exluck", "C:/Demo/");
//        Controller controller = new SvaController("C:/Users/jegbs/Desktop/bom/sva/BBK_72_2018-7nd_18IRL0601_BBK_BOM_list_V1.0.xls", "C:/Demo/");
        controller.launch();
    }
}
