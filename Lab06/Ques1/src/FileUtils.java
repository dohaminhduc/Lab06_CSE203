import java.io.*;
import java.util.*;

public class FileUtils {
    public static boolean fileExists(String directoryPath, String fileName){
        File directory = new File(directoryPath);
        if(!directory.exists()){
            directory.mkdir(); // Create the directory if it does not exist
        }
        File file = new File(directoryPath, fileName);
        return file.exists() && !file.isDirectory();
    }

    public static List<CD> read(String directoryPath, String fileName){
        List<CD> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directoryPath + fileName))) {
            list = (ArrayList<CD>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void write(String directoryPath, String fileName, List<CD> list){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(directoryPath + fileName))) {
            oos.writeObject(list);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
