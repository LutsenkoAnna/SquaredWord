package com;

import java.util.*;

/**
 * Created by ALutsenko on 05.04.2017.
 */
public class SquaredWord {
    Dictionary dict;
    List<Row> square;
    private List<String> permutations;
    Conflicts conf;
    boolean isRandom;

    SquaredWord(String dictionary, String rows, boolean isRandom) {
        dict = new Dictionary(dictionary);
        permutations = new ArrayList<>();
        conf = new Conflicts(dictionary);
        this.isRandom = isRandom;
        CreatePermutations("", dict.GetDictionary());
        ParseRows(rows);
        for (Row r: square) {
            r.GenerateRandom(isRandom);
        }
    }

    private void ParseRows(String rows) {
        String[] row = rows.split("\n");
        square = new ArrayList<>();
        String tmp = new String("");
        for (int i = 0; i < row.length; ++i) {
            for (int j = 0; j < row[i].length(); ++j) {
                if (row[i].charAt(j) == '.')
                    tmp += ".";
                else
                    tmp += row[i].charAt(j);
            }
            square.add(new Row(tmp, i, permutations));
            tmp = "";
        }
    }

    public void Print() {
        System.out.println("\n--------Square-------\n");
        for (Row r: square) {
            r.Print();
        }
    }

    private void CreatePermutations(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++)
                CreatePermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    public int CheckVertical() {
        conf.SetTempConflicts(0);
        for (int i = 0; i < square.size(); ++i) {
            for (int j = 0; j < square.size(); ++j) {
                conf.SetRepetition(square.get(j).GetRow().charAt(i));
            }
            conf.CheckConf();
        }
        int conflicts = conf.GetTempConflicts();
        return conflicts;
    }

    //TODO
    private int CheckDiagonal() {
        conf.SetTempConflicts(0);
        for (int i = 0; i < square.size(); ++i) {
            conf.SetRepetition(square.get(i).GetRow().charAt(i));
        }
        conf.CheckConf();
        for (int i = square.size() - 1; i >= 0; --i) {
            conf.SetRepetition(square.get(i).GetRow().charAt(i));
        }
        conf.CheckConf();
        int conflicts = conf.GetTempConflicts();
        return conflicts;
    }


    public boolean GreedyAlgorithm() {
        for (int j = 0; j < square.size(); ++j) {
            for (int i = 0; i < square.get(j).GetPermutationsForRemoveCount(); ) {
                int diff = CheckVertical() + CheckDiagonal();
                if (diff == 0) {
                    Print();
                    return true;
                }
                if (diff < conf.GetTotalConflicts()) {
                    square.get(j).SetBestRow(square.get(j).GetRow());
                    conf.SetTotalConflicts(diff);
                }
                square.get(j).GenerateRandom(isRandom);
            }
        }
        return false;
    }

    public boolean GreedyAlgorithm2() {
        Random rand = new Random(System.nanoTime());
        for (Row r: square) {
            int j = 0;
            /*for (int i = 0; i < r.GetPermutationsForRemoveCount();) {
                ++j;
                r.GetPermutationById(j).SetPossibility(CheckVertical() + CheckDiagonal());
                r.GenerateRandom(isRandom);
            }*/
           while(r.GetPermutationsForRemoveCount() > 0) {
                Random rando = new Random(System.nanoTime());
                int size = rando.nextInt(r.GetPermutationsForRemoveCount() + 1);
                if (size == r.GetPermutationsForRemoveCount())
                    size = r.GetPermutationsForRemoveCount() - 1;
                r.GetPermutationByString(r.GetPermutationForRemoveById(size).GetPermutation()).SetPossibility(CheckVertical() + CheckDiagonal());
                r.GenerateRandom(isRandom);
            }
            Collections.sort(r.GetPermutations(), Permutation::compareTo);
            r.MakeResultPermitation();
        }
        for (Row row : square) {
            Random r = new Random(System.nanoTime());
            int index = r.nextInt((int) (Math.pow(2, row.resultPermutations.size() - 1))) + 1;
            int log = (int) (Math.log(index) / Math.log(2));
            row.SetRow(row.resultPermutations.get(log).GetPermutation());
        }
        if (CheckVertical() + CheckDiagonal() == 0)
            return true;
        return false;
    }

}
