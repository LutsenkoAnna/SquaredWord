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
        permutations = new ArrayList<String>();
        conf = new Conflicts(dictionary);
        this.isRandom = isRandom;
        CreatePermutations("", dict.GetDictionary());
        ParseRows(rows);
        for (Row r: square) {
            r.GenerateRandom(isRandom); //TODO: Надо что-то придумать с рандомом! Чтобы не менять на методах
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

    public void PrintAllPermutations() {
        for (String p: permutations)
            System.out.println(p);
    }

    public void PrintRowPermutations(int i) {
        square.get(i).PrintPermutations();
    }

    public void PrintRowResultPermutations(int i) {
        square.get(i).PrintResultPermutations();
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

    public int CheckVerticalForPermutation(String perm) {
        conf.SetTempConflicts(0);
        for (int i = 0; i < square.size(); ++i) {
            for (int j = 0; j < square.size(); ++j) {
                //conf.SetRepetition(square.get(j).GetRow().charAt(i));
                conf.SetRepetition(perm.charAt(i));
            }
            conf.CheckConf();
        }
        int conflicts = conf.GetTempConflicts();
        return conflicts;
    }

    //TODO
    private int CheckDiagonal() {
        return 0;
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
                //Print();
            }
        }
        return false;
    }

    public boolean GreedyAlgorithm2() {
        Random rand = new Random(System.nanoTime());
        for (Row r: square) {
            int j = 0;
            //TODO: не берет последний элемент массива
            //TODO: добавить рандом (по-моему пока присваивает не тому)
            for (int i = 0; i < r.GetPermutationsForRemoveCount();) {
                ++j;
                r.GetPermutationById(j).SetPossibility(1.0 - (CheckVertical() + CheckDiagonal()) * 1.0 / (square.size() * square.size()));
                r.GenerateRandom(isRandom);
            }
            Collections.sort(r.GetPermutations(), Permutation::compareTo);
            r.MakeResultPermitation();
        }
        //PrintRowPermutations(3);
        PrintRowResultPermutations(0);
        PrintRowResultPermutations(1);
        PrintRowResultPermutations(2);
        PrintRowResultPermutations(3);
        PrintRowResultPermutations(4);
        do {
            for (Row row : square) {
                Random r = new Random(System.nanoTime());
                int index = r.nextInt((int) (Math.pow(2, row.resultPermutations.size()))) + 1;
                int log = (int) (Math.log(index) / Math.log(2));
                if (log >= row.resultPermutations.size())
                    log = row.resultPermutations.size() - 1;
                row.SetRow(row.resultPermutations.get(log).GetPermutation());
            }
            //Print();
        } while (CheckVertical() + CheckDiagonal() > 0);

        return false;
    }

}
