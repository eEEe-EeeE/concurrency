package file.main;

import comm.file.Result;
import file.parallel.ParallelGroupFileSearch;

import java.io.File;

public class ParallelGroupMain {
    public static void main(String[] args) {
        File file = new File("D:/WorkSpace");
        Result result = new Result();
        ParallelGroupFileSearch.searchFiles(file, "SerialMain.java", result);
    }
}
