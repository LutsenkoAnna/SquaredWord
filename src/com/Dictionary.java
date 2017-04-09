package com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALutsenko on 05.04.2017.
 */
public class Dictionary {
    List<Character> dictionary;

    public Dictionary(String dict) {
        dictionary = new ArrayList<>();
        ParseDicrionary(dict);
    }

    private void ParseDicrionary(String dict) {
        for (int i = 0; i < dict.length(); ++i) {
            dictionary.add(dict.charAt(i));
        }
    }

    public void Print() {
        for (Character c: dictionary) {
            System.out.println(c);
        }
    }

    private String makeString() {
        String tmp = new String("");
        for (Character c: dictionary)
            tmp += c;
        return tmp;
    }

    public String GetDictionary() {
        return makeString();
    }

}
