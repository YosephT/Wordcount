import java.io.*;
import java.util.*;

/**
 * Created by Samsung on 8/6/2015.
 */
class WordCount {


    public static void main (String[] args) {
//        System.out.println("did this work?");
//        WordCount temp = new WordCount("testSTring", 2);
//        System.out.println(temp.getWord() + " " +  temp.getOccurence());
//
        Map<String, Integer> totalOcc = new HashMap<>();
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader("src/rfc2616.txt"));
            String currWord;
            while ((currWord = inputStream.readLine()) != null){
//                System.out.println(currWord);
                String[] wordsFromLine = currWord.split("\\W");
                for (String s : wordsFromLine){
                    if (!totalOcc.containsKey(s)){
                        totalOcc.put(s, 1);
                    }
                    else
                    {
                        int current = totalOcc.get(s);
                        totalOcc.replace(s, current, current+1);
                    }
                }

//                System.out.println(totalOcc.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map<String, Integer> sortedNow = valueCompare(totalOcc);
        System.out.println("I wonder if this works now?");



        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("filename.txt"), "utf-8"));
            for (Map.Entry<String, Integer> entry : sortedNow.entrySet()) {
                writer.write("key: " + entry.getKey() + " value: " + entry.getValue());
                writer.write(System.getProperty( "line.separator" ));

            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Map<String, Integer> valueCompare(Map<String, Integer> totalOcc) {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(totalOcc.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    ;
};


