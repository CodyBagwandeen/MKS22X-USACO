import java.io.*;
import java.util.*;

public class USACO{

  static int[][] lake;

  public static int makeLake(String filename){

    int R, C, N, E = 0;

    try{
    File text = new File(filename);
    // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"

    //inf stands for the input file
    Scanner inf = new Scanner(text);

    R = Integer.parseInt(inf.next());
    C = Integer.parseInt(inf.next());
    N = Integer.parseInt(inf.next());
    E = Integer.parseInt(inf.next());

    System.out.println(R); // gets the values for these from the text file
    System.out.println(C);
    System.out.println(N);
    System.out.println(E);


    for(int i = 0; i < R; i++){
      String nums = inf.nextLine();
      
    }

    System.out.println(lake.toString());



  }catch(FileNotFoundException e){
    System.out.println("File not Found");
    }

    return 0 ; // return the sum of the things left in the array * R *  C
  }

  public void stomp(int row, int col, int depth){
    while(depth > 0){
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
            lake[r][c] -= 1;
          }
        }
      }
    }
    depth =- 1;
  }

  public static void main(String[] args){
    System.out.println(makeLake(args[0]));
  }
}
