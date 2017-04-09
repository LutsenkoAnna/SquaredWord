package com;

import java.util.*;

/**
 * Created by ALutsenko on 05.04.2017.
 */
public class Conflicts {
    private int totalConflicts;
    private int tempConflicts;
    private Map<Character, Integer> countOfRepetitions = new HashMap<>();

    public Conflicts(String dict) {
        totalConflicts = 1000;
        tempConflicts = 0;
        for (int i = 0; i < dict.length(); ++i)
            countOfRepetitions.put(dict.charAt(i), 0);
    }

    public int GetTotalConflicts() {
        return totalConflicts;
    }

    public void SetRepetition(char c) {
        countOfRepetitions.put(c, countOfRepetitions.get(c) + 1);
    }

    public void CheckConf() {
        Iterator it = countOfRepetitions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character, Integer> pair = (Map.Entry) it.next();
            if (pair.getValue() > 1) {
                tempConflicts += pair.getValue() - 1;
            }
            countOfRepetitions.put(pair.getKey(), 0);
        }
    }

    public void SetTotalConflicts(int total) {
        totalConflicts = total;
    }

    public void SetTempConflicts(int temp) {
        tempConflicts = temp;
    }

    public int GetTempConflicts() {
        return tempConflicts;
    }
}
