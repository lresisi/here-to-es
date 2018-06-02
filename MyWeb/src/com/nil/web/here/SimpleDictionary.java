package com.nil.web.here;

/**
 * Created by liorr on 5/27/18.
 */

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * A simple Dictionary implementation, that lists the words of a language in alphabetical order and gives their meaning.
 */
public class SimpleDictionary {
    private static final String NOT_FOUND = "The dictionary does not contain an entry for ";
    private final Map<String, String> mDictionary;

    public SimpleDictionary() {
        mDictionary = new TreeMap<String, String>();
    }

    /**
     * @param word {@link String}
     * @return the meaning of then given word.
     */
    public String lookup(final String word) {
        if (mDictionary.containsKey(word)) {
            return mDictionary.get(word);
        } else {
            return NOT_FOUND + word;
        }
    }

    /**
     * Enters a new entry in the dictionary.
     *
     * @param word    {@link String}
     * @param meaning {@link String}
     */
    public void enter(final String word, final String meaning) {
        mDictionary.put(word, meaning);
    }

    /**
     * Deletes the entry for the given word.
     *
     * @param word {@link String}
     */
    public void delete(final String word) {
        mDictionary.remove(word);
    }

    public String content() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : mDictionary.entrySet()) {
            sb.append((entry.getKey() + " : " + entry.getValue() + "\n").getBytes());
        }
        return "Content: " + sb.toString();
    }

    @Override
    public String toString() {
        try {
            return "toString: " + content();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}