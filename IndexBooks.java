import java.util.*;
import java.io.*;

/**
 * Created by Devang on 30-Dec-16.
 */
public class IndexBooks {
    public static void main(String[] args) throws IOException{

        Map<String, ArrayList<Integer>> words = new HashMap<>();
        int bookid = 0, count = 0;

        File folder = new File("books");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {

                bookid++;

                InputStream is = new FileInputStream(file);
                BufferedReader br;
                br= new BufferedReader(new InputStreamReader(is));
                StreamTokenizer st = new StreamTokenizer(br);

                System.out.println("File: " + file);
                //Read and print each line
                int token = 0, len = 0;
                while(token != -1){
                    String key;
                    token = st.nextToken();
                    if(token == -3){
                        count++;

                        len = st.sval.length();
                        if(len > 1 && st.sval.substring(len-1, len).equals(".")){
                            key = st.sval.substring(0, len-1);
                        } else {
                            key = st.sval;
                        }
                        if (!words.containsKey(key)) {
                            words.put(key, new ArrayList<Integer>());
                            words.get(key).add(bookid);
                        } else if(!words.get(key).contains(bookid)) {
                            words.get(key).add(bookid);
                        }
                    }
                }

            }
        }

        /* The "words" Map is serialized and stored in words.ser */
        try {
            FileOutputStream fos = new FileOutputStream("words.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(words);
            oos.close();
            fos.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        System.out.println(words);
        System.out.println(count + " total words");
        System.out.println(words.size() + " unique words");
        System.out.println(words.get("Achilles"));
    }
}
