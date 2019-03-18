import java.util.*;
import java.io.*;

public class USACO {
  public static void main(String[] args) {
    try {
      if (args.length == 0) {
        // automatic file usage
        System.out.println(bronze("makelake.1.in"));
        System.out.println(silver("ctravel.1.in"));
      }
      else {
        // manual file testing
        System.out.println(bronze(args[0]));
        System.out.println(silver(args[1]));
      }
    } catch (FileNotFoundException e) {
      System.out.println("rest in pieces no file found");
    }
  }
  public static int bronze(String filename) throws FileNotFoundException {
    File file = new File(filename); // not entirely sure why but when i don't put it as a file it doesn't run
    Scanner s = new Scanner(file);  // scanner reads the file
    int[] firstRow = new int[4];    // the 4 ints of the first line of the file
    for (int i = 0; i < 4; i++) {
      firstRow[i] = Integer.parseInt(s.next());
    }
    int numRows = firstRow[0];
    int numCols = firstRow[1];
    int[][] field = new int[numRows][numCols];
    String line;

    //going thtough the file to make field
    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
      	field[r][c] = Integer.parseInt(s.next());
      }
    }

    // stomping
    for (int a = 0; a < firstRow[3]; a++) {
    	int stompingR = Integer.parseInt(s.next()) - 1;
    	int stompingC = Integer.parseInt(s.next()) - 1;
    	int stompingD = Integer.parseInt(s.next());
    	int max = 0;
      // finding max elevation
    	for (int m = stompingR; m < stompingR + 3; m++) {
        for (int n = stompingC; n < stompingC + 3; n++) {
          if (field[m][n] > max) max = field[m][n];
        }
      }
    	for (int p = stompingR; p < stompingR + 3; p++) {
    		for (int q = stompingC; q < stompingC + 3; q++) {
    			if (max - field[p][q] < stompingD) {
            int diff = stompingD - max;
            diff += field[p][q];
    				field[p][q] -= diff;
    			}
    		}
    	}
    }

    // getting the area ans
    int ans = 0;
    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        if (field[r][c] < firstRow[2]) ans += firstRow[2] - field[r][c];
      }
    }
    return ans * 72 * 72;
  }

  public static String toString(int[][] a) {
    String output = "";
    for (int r = 0; r < a.length; r++) {
      for (int c = 0; c < a[0].length; c++) {
        output += a[r][c] + " ";
      }
      output += "\n";
    }
    return output;
  }

  public static int silver(String filename) throws FileNotFoundException {
    File file = new File(filename);
    Scanner s = new Scanner(file);
    String[] firstRow = s.nextLine().split(" ");
    int numRows = Integer.parseInt(firstRow[0]);
    int numCols = Integer.parseInt(firstRow[1]);
    int time = Integer.parseInt(firstRow[2]);
    if (time < 0 || time > 15) {
      throw new IllegalArgumentException("");
    }
    int[][] field = new int[numRows][numCols];
    for (int r = 0; r < numRows; r++) {
      String line = s.nextLine();
      for (int c = 0; c < numCols; c++) {
        if (line.charAt(c) == '*') field[r][c] = -1;
        else {
          field[r][c] = 0;
        }
      }
    }
    String[] lastRow = s.nextLine().split(" ");
    int startingRow = Integer.parseInt(lastRow[0]) - 1;
    int startingCol = Integer.parseInt(lastRow[1]) - 1;
    int finalRow = Integer.parseInt(lastRow[2]) - 1;
    int finalCol = Integer.parseInt(lastRow[3]) - 1;
    field[startingRow][startingCol] = 1;
    for (int i = 0; i < time; i++) {
      field = refresh(field); 
    }
    return field[finalRow][finalCol];
  }

  public static int[][] refresh(int[][] field) {
    int[][] moves = { {0,1}, {0,-1}, {1,0}, {-1,0} }; // four directions
    int[][] newField = new int[field.length][field[0].length];
    int tempR;
    int tempC;
    for (int r = 0; r < field.length; r++) {
      for (int c = 0; c < field[0].length; c++) {
        if (field[r][c] == -1) newField[r][c] = -1;
        else {
          int temp = 0;
          for (int x = 0; x < moves.length; x++) {
            tempR = r + moves[x][0];
            tempC = c + moves[x][1];
            if (tempR > -1 && tempR < field.length && tempC > -1 && tempC < field[0].length && field[tempR][tempC] != -1) {
              temp += field[tempR][tempC]; // in bounds
            }
          }
          newField[r][c] = temp;
        }
      }
    }
    return newField;
  }
}
