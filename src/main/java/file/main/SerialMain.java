package file.main;

import comm.file.Result;
import file.serial.SerialFileSearch;

import java.io.File;

public class SerialMain {
    public static void main(String[] args) {
        File file = new File("D:/WorkSpace");
        Result result = new Result();
        SerialFileSearch.searchFiles(file, "SerialMain.java", result);
        System.out.println(result.getPath());
    }
}
