package project.XlsxConverter.Exceptions;

public class NativeException extends Exception {

    public void printMsg() {
        System.out.println("/$ : [ERROR!!!] some native file system problem occured while creating a folder, please, try again...");
    }
}
