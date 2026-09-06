/*
 * Given a line of text, find the frequency of each word in the text.
 *
 * Example:
 * Input:
 * "hello this is india this is a beautiful country"
 *
 * Output:
 * hello       1
 * this        2
 * is          2
 * india       1
 * a           1
 * beautiful   1
 * country     1
 */


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class FrequencyTrackerOfWords {
    
    public static Map<String, Integer> findFrequency (String text) {

        if (text.trim().isEmpty()) {
            return new HashMap<>();
        }
    
        String[] words = text.split("\\s+");
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {

            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        return map;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter line of text: ");
        String text = sc.nextLine();

        Map<String, Integer> map = findFrequency(text);

        System.out.println("Word    :     Frequency");

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "        " + entry.getValue());
        }
    }
}
