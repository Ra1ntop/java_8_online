package ua.com.alevel;

public enum FilePath {
    INPUT_FILE_NAME("input.txt"),
    OUTPUT_FILE_NAME("output.txt");

    private final String filePath;

    FilePath(String filePath){
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
