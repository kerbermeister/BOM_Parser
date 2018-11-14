package project.XlsxConverter.Exceptions;

public class InvalidPathException extends Exception {

    public void printMsg() {
        System.out.println("/$ : ERROR!!! Provided path does not exist!");
    }
}
