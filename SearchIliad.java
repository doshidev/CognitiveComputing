import java.util.*;
import java.io.*;

/**
 * Created by Devang on 30-Dec-16.
 */
public class SearchIliad {
    public static void main(String[] args) throws IOException {
        Map<String, ArrayList<Integer>> words;
        try {
            FileInputStream fis = new FileInputStream("words.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            words = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter two words for a boolean search (e.g.: \"Achilles AND Helen\"): ");
        String s1 = scan.next();
        String s2 = scan.next();

        ArrayList<Integer> lst1 = words.get(s1);
        ArrayList<Integer> lst2 = words.get(s2);
        ArrayList<Integer> result = intersect(lst1, lst2);
        System.out.println(result);
    }

    private static ArrayList<Integer> intersect(ArrayList<Integer> lst1, ArrayList<Integer> lst2){
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < lst1.size() && j < lst2.size()){
            if(lst1.get(i) == lst2.get(j)){
                result.add(lst1.get(i));
                i++;
                j++;
            } else if(lst1.get(i) < lst2.get(j)){
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}