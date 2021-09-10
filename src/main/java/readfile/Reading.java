package readfile;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Reading {
    private static final String FILE_NAME = "List.txt";

    public static void main(String[] args) throws IOException {
        readFile();
    }
    public static void readFile() throws IOException {
        RandomAccessFile random = new RandomAccessFile(FILE_NAME,"r");
        while (random.getFilePointer() < random.length()){
            System.out.println(random.readLine());
        }
        random.close();
    }
}
