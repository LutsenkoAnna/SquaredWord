package com;


/**
 * Created by ALutsenko on 05.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        //String str = "СЛЕЗА\n.....\n..ЛЕС\n.....\n.....";
        //String d = "СЛЕЗА";
        String str = "КЛИМАТ\n......\n......\n..МАТ.\nЛИК...\n......";
        String d = "КЛИМАТ";
        int countOfMakingNewSquare = 0;
        SquaredWord sw;
        /*do {
            sw = new SquaredWord(d, str, true);
            ++countOfMakingNewSquare;
            System.out.println("\nCountOfMakingNewSquare = " + countOfMakingNewSquare);
        } while (!sw.GreedyAlgorithm());
        sw.Print();*/
        do {
            sw = new SquaredWord(d, str, true);
            ++countOfMakingNewSquare;

            System.out.println("\nCountOfMakingNewSquare = " + countOfMakingNewSquare);
        } while(!sw.GreedyAlgorithm2());
        sw.Print();
        System.out.println("\nCountOfMakingNewSquare = " + countOfMakingNewSquare);
    }
}
