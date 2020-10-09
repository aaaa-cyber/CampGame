public class Field {
  private int side; //盤の１辺
  private int area; //面積
  private int[][] firstStn;  //先攻の石の座標
  private int[][] secondStn; // 後攻の石の座標
  private int countStone = 0; //置いた石の総数をカウント
  public static int indent = 15;    //ロゴのインデント
  private int[] endXY = new int[2];  //マスがのこり一つになった時、自動で埋めた座標を格納
  private int endTrig = 0; //最後のマスを自動で埋めたかどうかを記録

  //コンストラクタ
  public Field(int side) {
    this.side = side;
    this.area = side * side;
    this.firstStn = new int[side][side];
    this.secondStn = new int[side][side];
  }

  //最後のマスを自動で埋めたら1を、そうでなければ0を返す。
  public int getTrig() {
    return endTrig;
  }

  //1辺の長さを返す。
  public int getside() {
    return side;
  }

  //面積を返す。
  public int getArea() {
    return area;
  }

  //置いた石の総数を返す。
  public int getCount() {
    return countStone;
  }

  //同じ文字をn個String型で出力
  String putString(String s, int n) {
    String putString = "";
    while (n-- > 0) {
      putString += s;
    }
    return putString;
  }

  //盤面出力
  public String print() {
    String hLine = " ---";
    String print = "";

    print += putString("\n", 15);
    print += "Rows\\Columns";

    print += putString(" ", indent - 12);
    for (int i = 0; i < side; i++) {
      print += String.format(" %2d ", i + 1);
    }
    print += "\n";

    for (int i = 0; i < side; i++) {
      print += putString(" ", indent);
      for(int j = 0; j < side; j++) { print += hLine; }
      print += "\n";

      print += String.format(putString(" ", indent - 2) + "%2d", i + 1);
      for(int j = 0; j < side; j++) {
        if (firstStn[i][j] == 1)       { print += "| ○ "; }
        else if (secondStn[i][j] == 1) { print += "| × "; }
        else                           { print += "|   "; }
      }
      print += "|\n";
    }
    print += putString(" ", indent);
    for(int j = 0; j < side; j++) { print += hLine; }
    print += "\n";

    print += putString("-", indent * 2 + 1) + putString("----", side);

    return print;
  }

  //重複判定
  public boolean checkDuplicate(int x, int y) {
    boolean evaluate = false;
    if (firstStn[x][y] == 1 || secondStn[x][y] == 1) {
      evaluate = true;
    }
    if (!evaluate) {
      if (countStone % 2 == 0) {
        firstStn[x][y] = 1;
      } else {
        secondStn[x][y] = 1;
      }
      countStone++;
    }
    return evaluate;
  }

  //勝利判定
  public boolean line(int[][] a) {
    //横判定
    int countLine = 0;
    for (int i = 0; i < side; i++) {
      for (int j = 0; j < side; j++) {
        if (a[i][j] == 0) {
          countLine = 0;
          break;
        } else {
          countLine += 1;
        }
      }
      if (countLine > 0) {
        return true;
      }
    }

    //縦判定
    for (int i = 0; i < side; i++) {
      for (int j = 0; j < side; j++) {
        if (a[j][i] == 0) {
          countLine = 0;
          break;
        } else {
          countLine += 1;
        }
      }
      if (countLine > 0) {
        return true;
      }
    }

    //斜め判定
    //右肩下がりの斜め判定
    for (int i = 0; i < side; i++) {
      if (a[i][i] == 0) {
        countLine = 0;
        break;
      } else {
        countLine += 1;
      }
    }
    if (countLine > 0) {
      return true;
    }

    //右肩上がりの斜め判定
    for (int i = 0; i < side; i++) {
      if (a[side - i - 1][i] == 0) {
        countLine = 0;
        break;
      } else {
        countLine += 1;
      }
    }
    if (countLine > 0) {
      return true;
    }
    return false;
  }

  /*勝者判定
   *先攻...0 / 後攻...1 / 勝者なし... -1
  */
  public int winner() {
    int winner;
    if (line(firstStn)) {
      winner = 0;
    } else if (line(secondStn)) {
      winner = 1;
    } else {
      winner = -1;
    }
    return winner;
  }

  /*ans[0]に終了判定、ans[1]に勝者判定
   *ans[0] = 0:続行, 1:終了
   *ans[1] = 0:先攻, 1:後攻, - 1:引き分け
  */
  public int[] judgement() {
    int[] ans = new int[2];
    if (winner() >= 0 || countStone == area - 1) {
      if (countStone == area - 1) {
        for (int i = 0; i < side; i++) {
          for (int j = 0; j < side; j++) {
            if (countStone % 2 == 0) {
              if (firstStn[i][j] == 0 && secondStn[i][j] == 0) {
                firstStn[i][j] = 1;
                endXY[0] = i + 1;
                endXY[1] = j + 1;
                endTrig = 1;
                break;
              }
            } else {
              if (firstStn[i][j] == 0 && secondStn[i][j] == 0) {
                secondStn[i][j] = 1;
                endXY[0] = i + 1;
                endXY[1] = j + 1;
                endTrig = 1;
                break;
              }
            }
          }
        }
      }
      ans[0] = 1;
      ans[1] = winner();
    }
    return ans;
  }

  //最後の座標を取得
  public int[] endXY() {
    return endXY;
  }

}
