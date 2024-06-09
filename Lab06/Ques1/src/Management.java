import java.io.Serializable;
import java.util.*;

public class Management implements Serializable {
    ArrayList<CD> list;
    private static Management instance;
    public Management() {
        list = new ArrayList();
        if(FileUtils.fileExists("C:\\Documents","CD.eiu")){
            list.addAll(FileUtils.read("C:\\Documents","CD.eiu"));
        }
    }
    public Management(ArrayList<CD> list) {
        this.list = list;
    }
    public void setList(ArrayList<CD> list) {
        this.list = list;
    }
    public ArrayList<CD> getCD() {
        return list;
    }
    public static Management getInstance() {
        if(instance == null) {
            instance = new Management();
        }
        return instance;
    }
    public void addCD(CD cd) {
        list.add(cd);
        FileUtils.write("C:\\Documents","CD.eiu",list);
    }
    public ArrayList<CD> searchByTitle(String title) {
        String titleUppercase = title.toUpperCase();
        ArrayList<CD> similarTitle = new ArrayList<>();
        for (CD cd : list) {
            if (cd.getTitle().toUpperCase().contains(titleUppercase)) {
                similarTitle.add(cd);
            }
        }
        return similarTitle;
    }

    public ArrayList<CD> searchByCollection(String collection) {
        String collectionUppercase = collection.toUpperCase();
        ArrayList<CD> similarCollection = new ArrayList<>();
        for (CD cd : list) {
            if (cd.getCollection().toUpperCase().contains(collectionUppercase)) {
                similarCollection.add(cd);
            }
        }
        return similarCollection;
    }

    public ArrayList<CD> searchByType(String type) {
       ArrayList<CD> similarType = new ArrayList<>();
       for (CD cd : list) {
           if (cd.getType().toUpperCase().equals(type)) {
               similarType.add(cd);
           }
       }
       return similarType;
    }

    public ArrayList<CD> searchByPrice(Double Price) {
        ArrayList<CD> similarPrice = new ArrayList<>();
        for (CD cd : list) {
            if(cd.getPrice() <= Price){
                similarPrice.add(cd);
            }
        }
        return similarPrice;
    }

    public void deleteByTitle(String title) {
        list.remove(searchByTitle(title));
        FileUtils.write("C:\\Documents","CD.eiu",list);
    }
}