package java1.dz.dz4;

import java.util.*;

public class UnicWords {
    private Map<String, Integer> words = new HashMap<>(32);

    public UnicWords (List<String> list) {
        for (String word : list) {
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
            } else {
                words.put(word, 1);
            }
        }
    }

    public void printUnicWords() {
        for (String word : words.keySet()) {
            System.out.println(word);
        }
        System.out.println(words.size() + " words.");
        System.out.println();
    }

    public void printHowManyWordsRepeats() {
        for (String word : words.keySet()) {
            System.out.println("Word " + word + " repeats " + words.get(word) + " times.");
        }
        System.out.println();
    }
}
