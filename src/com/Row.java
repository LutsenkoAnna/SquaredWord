package com;


import java.util.*;

/**
 * Created by ALutsenko on 05.04.2017.
 */
public class Row {
    private String row;
    private int rowNumber;
    private String bestRow;
    private List<Permutation> permutations;
    private List<Permutation> permutationsForRemove;
    public List<Permutation> resultPermutations;


    public Row(String word, int number, List<String>perm) {
        this.row = word;
        this.rowNumber = number;
        this.bestRow = word;
        this.permutations = new ArrayList<>();
        this.permutationsForRemove = new ArrayList<>();
        MakePermutations(perm);
    }

    private void MakePermutations(List<String> perm) {
        for (String p: perm) {
            if (p.matches(row)) {
                permutations.add(new Permutation(p, 0.0));
                permutationsForRemove.add(new Permutation(p, 0.0));
            }
        }
    }

    public String GetRow(){
        return row;
    }

    public void SetRow(String newRow) {
        row = newRow;
    }


    public void SetBestRow(String newBestRow) {
        bestRow = new String(newBestRow);
    }

    public int GetPermutationsForRemoveCount() {
        return permutationsForRemove.size();
    }

    public void Print(){
        if("".endsWith(row))
            System.out.print(".");
        else
            System.out.print(row);
        System.out.println();
    }

    public List<Permutation> GetPermutations() {
        return permutations;
    }

    public Permutation GetPermutationById(int index) {
        return permutations.get(index);
    }

    public Permutation GetPermutationForRemoveById(int index) {
        return permutationsForRemove.get(index);
    }

    public Permutation GetPermutationByString(String str) {
        for (Permutation perm : permutations) {
            if (perm.GetPermutation().equals(str)) {
                return perm;
            }
        }
        return permutations.get(0);
    }

    public void GenerateRandom(boolean isRandom) {
        int index;
        if (isRandom) {
            Random r = new Random(System.nanoTime());
            index = r.nextInt(permutationsForRemove.size());
        }
        else {
            index = 0;
        }
        String newRow = permutationsForRemove.get(index).GetPermutation();
        row = newRow;
        permutationsForRemove.remove(index);
    }

    public void MakeResultPermitation() {
        resultPermutations = new ArrayList<>();
        resultPermutations.add(new Permutation(permutations.get(0).GetPermutation(), permutations.get(0).GetPossibility()));
        List<Permutation>temp = new ArrayList<>();
        for (Permutation perm : permutations) {
            if (perm.GetPossibility() != resultPermutations.get(resultPermutations.size() - 1).GetPossibility()) {
                resultPermutations.add(new Permutation(perm.GetPermutation(), perm.GetPossibility()));
            }
        }
    }

}
