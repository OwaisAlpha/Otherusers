/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt1.uf2.m3;

import java.util.Random;

/**
 *
 * @author mowais.daw1r
 */
public class Pt1Uf2M3 {
    

    /**
     * @param args the command line arguments
     */
    
public static final String TEXT_RESET = "\u001B[0m";
public static final String TEXT_BLACK = "\u001B[30m";
public static final String TEXT_RED = "\u001B[31m";
public static final String TEXT_GREEN = "\u001B[32m";
public static final String TEXT_YELLOW = "\u001B[33m";
public static final String TEXT_BLUE = "\u001B[34m";
public static final String TEXT_PURPLE = "\u001B[35m";
public static final String TEXT_CYAN = "\u001B[36m";
public static final String TEXT_WHITE = "\u001B[37m";
    public static void main(String[] args) {
        
        int dim = 20;
        /* A variable to store the Dimensions.*/
        int InitialGen[][] = new int[dim][dim];
        int newGeneration[][] = new int[dim][dim];
        final int limit = 500;
        int counter = 0;
        boolean same = false;
        /* starting processing the programe
        @InitialGeneration is name of function to create the Initial Generation,
        @InitialGen is the table being used asparameters and gets the return 
        value from function.*/
        InitialGeneration(InitialGen);
        /* @ DisplayGeneration Display the initial generation. */
        DisplayGeneration(InitialGen);

//        System.out.println("The New Generation For" + counter + " As Following.");
//        DisplayGeneration(newGeneration);
        while (counter < limit && !same) {
            NewGeneration(InitialGen, newGeneration);
            System.out.println("The New Generation For" + counter );
            
            same = CompareGenrations(InitialGen, newGeneration);
            if (!same) {
                InitialGen = assignValues(InitialGen, newGeneration);
               
                     }
             counter++;

        }
        ShowResult(counter,limit,InitialGen);

    }
// in this function we are going to creat a table of random numbers <2.



    public static int[][] InitialGeneration(int t[][]) {
        Random r = new Random();
        for (int i = 0; i < t.length; i++) {

            for (int j = 0; j < t[i].length; j++) {
                int random = r.nextInt(2);
                t[i][j] = random;
            }
        }
        return t;
    }

    /*THis function will display the  Created Generation(may be any of the generation, inicial or last one.).*/
    public static void DisplayGeneration(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if(table[i][j]==1){
                    System.out.print(TEXT_GREEN+table[i][j] + " ");
                }else{
                    System.out.print(TEXT_RED+table[i][j]+" ");
                }
            }
            System.out.println("");
        }
    }

    /*this Function will check the Neighbours in the surrounding of 
    a Position int[line][column].
     */
    public static int neighbour(int t[][], int line, int column) {
        int neighbour = 0;
        for (int i = (line - 1); i <= (line + 1); i++) {
            for (int j = (column - 1); j <= (column + 1); j++) {
                if (i >= 0 && i < t.length) {
                    if (j >= 0 && j < t[i].length) {
                        if (t[i][j] == 1) {
                            neighbour++;
                        }
                    }
                }
            }
        }
        /*the Following code "neighbour=neighbour- t[line][column];" will rest 1
        if at the position it has one, with this we will not get the exact
        position counted as neighbour.*/
        neighbour = neighbour - t[line][column];
        return neighbour;
    }

    public static int[][] NewGeneration(int[][] t1, int[][] t2) {
        int neighbours;
        for (int i = 0; i < t1.length; i++) {
            for (int j = 0; j < t1[i].length; j++) {
                /*calling the function to check the neighbouyrs of every position.
                with respect of neighbours of a position we will put the conditions
                for creation of new generation.
                @neighbour is the function to check the neighbour.
                the Vriable neighbours is get value as return valueof function neighbour*/
                neighbours = neighbour(t1, i, j);
                if (t1[i][j] == 0 && neighbours == 3) {
                    /*will be Born in next generation because of having 3 neighbours*/
                    t2[i][j] = 1;
                } else if (t1[i][j] == 1 && (neighbours == 0 || neighbours == 1)) {
                    /*Going to be Dead because of Lonliness*/
                    t2[i][j] = 0;
                } else if (t1[i][j] == 1 && (neighbours == 2 || neighbours == 3)) {
                    /*will survive in the new generation. */
                    t2[i][j] = 1;
                } else if (t1[i][j] == 1 && neighbours > 3) {
                    /*will be dead because of suffocation.*/
                    t2[i][j] = 0;
                } else {
                    t2[i][j] = t1[i][j];
                }

            }
        }
        return t2;
    }

    public static boolean CompareGenrations(int[][] table1, int[][] table2) {
        boolean same = true;
        int i, j;
        i = 0;
        while (same && i < table1.length) {
            j = 0;
            while (same && j < table1[i].length) {

                if (table1[i][j] != table2[i][j]) {
                    same = false;
                }
                j++;
            }
            i++;
        }
        return same;
    }

    public static int[][] assignValues(int[][] initialGen, int[][] newGeneration) {
        for (int i = 0; i < initialGen.length; i++) {
            for (int j = 0; j < initialGen[i].length; j++) {
                initialGen[i][j] = newGeneration[i][j];
            }
        }
        return initialGen;
    }

    public static void ShowResult(int count,int looplimit,int[][]currentGen){
        if(count==looplimit){
            System.out.println("The Loop has Reached its Limits.");
        }
        else{ 
            System.out.println("==================================");
            System.out.println("==================================");
            System.out.println("it took " + count + " generations");
            System.out.println("Final Generation:");
            DisplayGeneration(currentGen);
            
        }
        
    }

    }
   