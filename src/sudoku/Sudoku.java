package sudoku;
import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;
import java.util.HashSet;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Sudoku{                        
	public static int[][] grid = { 
			{ 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 8, 0, 0, 0, 0, 0, 3, 1 }, 
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
            { 9, 0, 0, 0, 6, 3, 0, 0, 5 }, 
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, 
            { 1, 3, 0, 0, 0, 0, 2, 0, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 }  }; 
	
	static void printSudoku(int[][] sudoku) {
		for(int i = 0; i != 9; i++) {
			for(int j = 0; j != 9; j++) {
				System.out.println(sudoku[i][j]);
			}
			System.out.println("\n");
		}
	}
	
	static boolean isValid(int[][] sudoku, int row, int col, int num) {
		//check row
		for(int i = 0; i < sudoku.length; ++i) {
			if(sudoku[row][i] == num) {
				return false;
			}
		}
		//check col
		for(int j = 0; j < sudoku.length; j++) {
			if(sudoku[j][col] == num) {
				return false;
			}
		}
		//check 3x3
		int sqrt = (int)Math.sqrt(sudoku.length); 
        int boxRowStart = row - row % sqrt; 
        int boxColStart = col - col % sqrt; 
  
        for (int r = boxRowStart; 
             r < boxRowStart + sqrt; r++) { 
            for (int d = boxColStart; 
                 d < boxColStart + sqrt; d++) { 
                if (sudoku[r][d] == num) { 
                    return false; 
                } 
            } 
        } 
		return true;
	}

	static boolean backtrack(int[][] sudoku, int n) {
		boolean empty = true;
		int i = 0,j = 0;
		for(int row = 0; row != 9; row++) {
			for(int col = 0; col != 9; col++) {
				if(sudoku[row][col] == 0) {
					i= row;
					j = col;
					empty = false;
					break;
				}
			}
			if(empty == false) {
				break;
			}
		}
		
		if(empty == true) {
			return true;
		}
		
		for(int ans = 1; ans <= n; ans++){
			if(isValid(sudoku, i, j, ans)) {
				sudoku[i][j] = ans;
				if(backtrack(sudoku, n)) {
					return true;
				}
				else {
					sudoku[i][j] = 0;
				}
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
	}
}
