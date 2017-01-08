import java.util.*;
import java.io.*;

/**
 * Created by Devang on 30-Dec-16.
 */
public class SearchIliad {
    public static void main(String[] args) throws IOException {
        Map<String, BitSet> words;
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
        System.out.println("Enter two words for a boolean search (e.g.: \"Achilles AND Helen\"): ");
        String s1 = scan.next();
        String s2 = scan.next();
    
        BitSet bs1 = (BitSet) words.get(s1).clone();
        BitSet bs2 = (BitSet) words.get(s2).clone();
        bs1.and(bs2);
        
        System.out.println(bs1);
    }
}