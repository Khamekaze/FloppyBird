package com.floppy.game;


import java.util.Scanner;

public class Score {
    private static int highScore;
  //  private static int a = 1;
    private static boolean passed = true;
    private static int numberOfObstaclesPassed = 0;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        while(a == 1) {
            poang();
            System.out.println(getNumberOfObstaclesPassed());
            a = sc.nextInt();
        }
        hogstaPoang();
        System.out.println("Nytt världsrekord: " + highScore);
    }
    public static void hogstaPoang () {
        if(numberOfObstaclesPassed > highScore){
            highScore = numberOfObstaclesPassed;
        }
    }
    public static int getNumberOfObstaclesPassed(){ return numberOfObstaclesPassed; }
    public static int getHighScore(){return highScore; }
    public static void poang () { numberOfObstaclesPassed ++; }
}
