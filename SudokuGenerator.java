import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class SudokuGenerator
{
    
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        int[][] board = new int[9][9];
        int[] base = base();
        fillBoard(board, base);
        
        int[][] puzzle = copyBoard(board);
        removeNumbers(puzzle);
        play(board, puzzle);
    }
    
    public static void printBoard(int[][] board){
        for (int row = 0; row < 9; row++){
            if(row % 3 == 0){
                System.out.println("---------------------------");
            }
            
            System.out.print("| ");
            for (int col = 0; col < 9; col++){
                if(col % 3 == 0 && col != 0){
                    System.out.print(" | ");
                }
                
                if(board[row][col] == 0){
                    System.out.print("* ");
                }
                
                else{
                    System.out.print(board[row][col] + " ");
                }
                
                
            }
            System.out.println("|");
        }
        System.out.println("---------------------------");
    }
    
    public static void fillBoard(int[][] board, int[] base){
        int ran = (int)(Math.random() * 2);
        
        if (ran == 0){
            for (int row = 0; row < 9; row++){
                int shift = (row % 3) * 3 + (row / 3);
                for (int col = 0; col < 9; col++){
                    board[row][col] = base[(col + shift) % 9];
                }
            }
        }
        
        else{
            for (int col = 0; col < 9; col++){
                int shift = (col % 3) * 3 + (col / 3);
                for (int row = 0; row < 9; row++){
                    board[row][col] = base[(row + shift) % 9];
                }
            }
        }
        System.out.println(ran);
        
    }
    
    public static int[] base(){
        int start = (int)(Math.random() * 9) + 1;
        int[] base = new int[9];
        for (int i = 0; i < 9; i++){
            base[i] = (start + i - 1) % 9 + 1;
        }
        
        return base;
    }
    
    public static int[][] copyBoard(int[][] board){
        int[][] puzzle = new int[9][9];
        for (int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                puzzle[row][col] = board[row][col];
            }
        }
        return puzzle;
    }
    
    public static void removeNumbers(int[][] puzzle){
        int removeCount = 10;
        
        for (int i = 0; i < removeCount; i++){
            int row = (int)(Math.random()*9);
            int col = (int)(Math.random() * 9);
            
            while (puzzle[row][col] == 0){
                row = (int)(Math.random() * 9);
                col = (int)(Math.random() * 9);
            }
            
            puzzle[row][col] = 0;
            
            }
        }
    
    public static void play(int[][] board, int[][] puzzle){
        while(!isSolved(puzzle)){
            printBoard(puzzle);
            System.out.println("Enter 0 at any point to reveal answers");
            System.out.print("Enter a row number:");
            int row = sc.nextInt();
            
            if (row == 0){
                System.out.println("Answer Key:");
                printBoard(board);
                break;
                
            }
            
            System.out.print("Enter a column number:");
            int col = sc.nextInt();
            
            if (col == 0){
                System.out.println("Answer Key:");
                printBoard(board);
                break;
                
            }
            
            System.out.print("Enter your guess");
            int num = sc.nextInt();
            
            if (num == 0){
                System.out.println("Answer Key:");
                printBoard(board);
                break;
                
            }
            
            if (board[row-1][col-1] == num){
                puzzle[row-1][col-1] = num;
                System.out.println("Correct!");
                printBoard(puzzle);
                
            }
            
            else{
                System.out.println("Incorrect");
            }
            
            
            
        }
    }
    
    public static boolean isSolved(int[][] puzzle){
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                if(puzzle[row][col] == 0){
                    return false;
                }
            }
        }
        return true;
    }
                
}