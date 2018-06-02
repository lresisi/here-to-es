package com.nil.web.example;

/**
 * Created by liorr on 5/27/18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * A simple Dictionary implementation, that lists the words of a language in alphabetical order and gives their meaning.
 */
public class Dictionary {
    private static final String NOT_FOUND = "The dictionary does not contain an entry for ";
    private final Map<String, String> mDictionary;

    public Dictionary() {
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

    /**
     * @param os {@link OutputStream}
     * @return the complete alphabetically sorted dictionary
     * @throws IOException
     */
    public OutputStream content(final OutputStream os) throws IOException {
        for (Map.Entry<String, String> entry : mDictionary.entrySet()) {
            os.write((entry.getKey() + " : " + entry.getValue() + "\n").getBytes());
        }
        return os;
    }

    /**
     * Writes the complete alphabetically sorted dictionary as a JSON String into the output stream.
     *
     * @param os {@link OutputStream}
     * @return the given OutputStream.
     * @throws IOException
     */
    public OutputStream serialize(final OutputStream os) throws IOException {
//        ObjectMapper mapper = JsonFactory.create();
//        mapper.writeValue(os, mDictionary);
        os.flush();
        return os;
    }

    public static void main(String[] args) {

        Dictionary dict = new Dictionary();
        System.out.printf("Usage:\n" +
                " lookup <word> - returns its meaning\n" +
                " enter <word> <meaning> - enters a new entry into the dictionary\n" +
                " delete <word> - removes an entry from the dictionary\n" +
                " content - returns the complete dictionary\n" +
                " serialize - returns the complete dictionary, json-encoded");

        while (true) {
            System.out.print("\n> ");
            BufferedReader lineOfText = new BufferedReader(new InputStreamReader(System.in));
            try {
                final String textLine = lineOfText.readLine();
                int m = textLine.indexOf(" ");
                final String cmd = textLine.substring(0, m > 0 ? m++ : textLine.length());
                if ("content".equals(cmd)) {
                    dict.content(System.out);
                } else if ("serialize".equals(cmd)) {
                    dict.serialize(System.out);
                } else {
                    int n = textLine.indexOf(" ", m);
                    final String param = textLine.substring(m, n > 0 ? n++ : textLine.length());
                    if ("delete".equals(cmd)) {
                        dict.delete(param);
                    } else if ("enter".equals(cmd)) {
                        dict.enter(param, textLine.substring(n));
                    }
                    System.out.println(param + " : " + dict.lookup(param));
                }
            } catch (IOException e) {
                System.out.println(e.toString());
                break;
            }
        }
    }
}