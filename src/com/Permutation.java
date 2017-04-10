package com;

import java.util.Comparator;

/**
 * Created by Anna on 07.04.2017.
 */
public class Permutation {
    private String permutation;
    private double possibility;

    public Permutation(String newPermutation, double newPossibility) {
        this.permutation = new String(newPermutation);
        this.possibility = newPossibility;
    }

    public Permutation() {
        this.permutation = new String();
        this.possibility = 0.0;
    }

    public String GetPermutation() {
        return permutation;
    }

    public void SetPermutation(String newPermutation) {
        permutation = new String(newPermutation);
    }

    public double GetPossibility() {
        return possibility;
    }

    public void SetPossibility(double newPossibility) {
        possibility = newPossibility;
    }

    public int compareTo(Permutation r) {
        return Comparators.Possibility.compare(this, r);
    }

    public static class Comparators {
        public static final Comparator<Permutation> Possibility =
                (Permutation o1, Permutation o2) -> (int) ((o2.possibility - o1.possibility) * 100);
    }

    public void PrintPermutations() {
        System.out.println(permutation + " " + possibility);
    }
}
