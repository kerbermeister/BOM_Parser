package project.Controllers;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Controller {

    void launch() throws FileNotFoundException, IOException, InvalidFormatException;
}
