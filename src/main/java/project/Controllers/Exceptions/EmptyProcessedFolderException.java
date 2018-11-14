package project.Controllers.Exceptions;

public class EmptyProcessedFolderException extends Exception {

    public void printMsg() {
        System.out.println("/$ : ERROR!!! No any files found for processing...");
    }
}
