package project;

import project.Controllers.Controller;
import project.Controllers.ExLuckController;
import project.Controllers.SvaController;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, FileNotFoundException {
        Controller controller = new ExLuckController();
        controller.launch();
    }
}
