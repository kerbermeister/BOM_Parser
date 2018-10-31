package project.Controllers;

public class ConfigEntity {

    private String filePath;
    private String folderToSave;
    private int partNumberColumn;
    private int descColumn;
    private int specColumn;
    private int partNumberColumnOffset;
    private int sheetIndex;

    public ConfigEntity() {}

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getPartNumberColumn() {
        return partNumberColumn;
    }

    public void setPartNumberColumn(int partNumberColumn) {
        this.partNumberColumn = partNumberColumn;
    }

    public int getDescColumn() {
        return descColumn;
    }

    public void setDescColumn(int descColumn) {
        this.descColumn = descColumn;
    }

    public int getSpecColumn() {
        return specColumn;
    }

    public void setSpecColumn(int specColumn) {
        this.specColumn = specColumn;
    }

    public int getPartNumberColumnOffset() {
        return partNumberColumnOffset;
    }

    public void setPartNumberColumnOffset(int partNumberColumnOffset) {
        this.partNumberColumnOffset = partNumberColumnOffset;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
}
