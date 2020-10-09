public class Printer {
/*
  int[][] oPlacement = new int[GameFrame.h][GameFrame.v];
  int[][] xPlacement = new int[GameFrame.h][GameFrame.v];
*/

  public void print(String s) {
    System.out.print(s);
  }

  public void println(String s) {
    System.out.println(s);
  }

  public String printer(int[][] a, int[][] b) {
    String hLine = " --- --- ---";
    String first = "| ○ ";
    String second = "| × ";
    String none = "|   ";
    String returnStr = "";

    for (int i = 0; i < GameFrame.h; i++) {
      returnStr += hLine + "\n";
      for(int j = 0; j < GameFrame.v; j++) {
        if (a[i][j] == 1) {
          returnStr += first;
        } else if (b[i][j] == 1) {
          returnStr += second;
        } else {
          returnStr += none;
        }
      }
      returnStr += "|\n";
    }
    returnStr += hLine;
    return returnStr;
  }
}

/*
 --- --- ---
|   |   |   |
 --- --- ---
|   | × | ○ |
 --- --- ---
|   |   |   |
 --- --- ---
*/
