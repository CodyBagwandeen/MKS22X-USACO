import java.io.*;
import java.util.*;

public class USACO{

  int[][] lake;

  public int makeLake(String filename){

    int R, C, N, E = 0;

    try{
    File text = new File("filename");
    // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"

    //inf stands for the input file
    Scanner inf = new Scanner(text);




  }catch(FileNotFoundException e){
    System.out.println("File not Found");
    }

    return 0; // return the sum of the things left in the array * R *  C
  }

  public void stomp(int row, int col, int depth){
    int max = 0;
    for(int r = row; r < row + 3; r++){
      for(int c = col; c < col +3; c++){
        if( lake[r][c] >= max){
          max = lake[r][c];
        }
      }
    }
    for( int r = row; r < row + 3; r++){
      for(int c = col; c < col +3; c++){
        if(lake[r][c] == max){
          lake[r][c] -1;
        }
      }
    }
  }


}
