package project.Controllers.Exceptions;

public class IllegalColumnNumException extends Exception {

    public void printMsg() {
        System.out.println("/$ : ERROR!!! One of entered column is incorrect!");
        System.out.println("/$ : 1) Columns should not be equal to each other!");
        System.out.println("/$ : 2) P/N column offset + P/N column should not be equal to DESC or SPEC column!");
        System.out.println("/$ : 3) P/N, DESC, SPEC columns should not be negative or equal to 0");
    }
}
