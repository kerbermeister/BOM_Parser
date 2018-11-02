package project.Controllers;

import project.Controllers.Exceptions.IllegalColumnNumException;
import project.XlsxConverter.XlsxConverter;

public class Config {

    private String filePath;
    private int partNumberColumn;
    private int descColumn;
    private int specColumn;
    private int partNumberColumnOffset;
    private int sheetIndex;

    public Config(String filePath, int partNumberColumn, int descColumn, int specColumn, int partNumberColumnOffset) throws IllegalColumnNumException {
        this.filePath = filePath;
        setPartNumberColumn(partNumberColumn);
        setDescColumn(descColumn);
        setSpecColumn(specColumn);
        setPartNumberColumnOffset(partNumberColumnOffset);
    }

    public Config(String filePath, int partNumberColumn, int descColumn, int specColumn, int partNumberColumnOffset, int sheetIndex) throws IllegalColumnNumException {
        this.filePath = filePath;
        setPartNumberColumn(partNumberColumn);
        setDescColumn(descColumn);
        setSpecColumn(specColumn);
        setPartNumberColumnOffset(partNumberColumnOffset);
        this.sheetIndex = sheetIndex;
    }

    private void setPartNumberColumn(int partNumberColumn) throws IllegalColumnNumException {
        if (!isPartNumColumnValid(partNumberColumn))
            throw new IllegalColumnNumException();
        this.partNumberColumn = partNumberColumn;
    }

    private void setDescColumn(int descColumn) throws IllegalColumnNumException {
        if (!isDescColumnNumValid(descColumn))
            throw new IllegalColumnNumException();
        this.descColumn = descColumn;
    }

    private void setSpecColumn(int specColumn) throws IllegalColumnNumException {
        if (!isSpecColumnNumValid(specColumn))
            throw new IllegalColumnNumException();
        this.specColumn = specColumn;
    }

    private void setPartNumberColumnOffset(int partNumberColumnOffset) throws IllegalColumnNumException {
        if (!isPartNumberColumnOffsetNumValid(partNumberColumnOffset))
            throw new IllegalColumnNumException();
        this.partNumberColumnOffset = partNumberColumnOffset;
    }

    private boolean isPartNumColumnValid(int partNumberColumn) {
        if (partNumberColumn < 1 || partNumberColumn > XlsxConverter.maxColumnNum)
            return false;
        else
            return true;
    }

    private boolean isSpecColumnNumValid(int specColumn) {
        if (specColumn == partNumberColumn || specColumn == descColumn || specColumn < 1 || specColumn > XlsxConverter.maxColumnNum)
            return false;
        else
            return true;
    }

    private boolean isDescColumnNumValid(int descColumn) {
        if (descColumn == partNumberColumn || descColumn < 1 || descColumn > XlsxConverter.maxColumnNum)
            return false;
        else
            return true;
    }

    private boolean isPartNumberColumnOffsetNumValid(int partNumberColumnOffset) {
        int result = partNumberColumnOffset + partNumberColumn;
        if (result == descColumn || result == specColumn)
            return false;
        else
            return true;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getPartNumberColumn() {
        return partNumberColumn;
    }

    public int getDescColumn() {
        return descColumn;
    }

    public int getSpecColumn() {
        return specColumn;
    }

    public int getPartNumberColumnOffset() {
        return partNumberColumnOffset;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }
}
