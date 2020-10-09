public abstract class GameFrame {
  //マス目の定義
  public static final int v = 3;
  public static final int h = 3;
  //マス目の数
  public static int space = v * h;
  //置かれた石の数
  public static int countStone = 0;

  //置かれた場所
  public static int[][] placement = new int[h][v];

  //勝利判定
  public static boolean line(int[][] a) {
    //横判定
    int countLine = 0;
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < v; j++) {
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
    for (int i = 0; i < v; i++) {
      for (int j = 0; j < h; j++) {
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
    if (v == h) {
      //右肩下がりの斜め判定
      for (int i = 0; i < h; i++) {
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
      for (int i = 0; i < h; i++) {
        if (a[h - i][i] == 0) {
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

    return false;
  }

  /*勝者判定
   *前者...0 / 後者...1 / 勝者なし... -1
  */
  public static int winner(int[][] a, int[][] b) {
    int winner;
    if (line(a)) {
      winner = 0;
    } else if (line(b)) {
      winner = 1;
    } else {
      winner = -1;
    }
    return winner;
  }

  /*ans[0]に終了判定、ans[1]に勝者判定
   *ans[0] = 0:続行, 1:終了
   *ans[1] = 0:前者, 1:後者, - 1:引き分け
  */
  public static int[] judgement(int[][] a, int[][] b) {
    int[] ans = new int[2];
    if (winner(a, b) >= 0) {
      ans[0] = 1;
      ans[1] = winner(a, b);
    } else if (countStone == space) {
      ans[0] = 1;
      ans[1] = - 1;
    }
    return ans;
  }

  //placement[putH, putV]が既に置かれていないかの判定。重複したらtrueを返す。
  public boolean judgeDuplicate(int putH, int putV) {
    boolean evaluate = false;
    if (placement[putH][putV] == 1) evaluate = true;
    return evaluate;
  }

  //位置取得
  public abstract int[][] getWhere();

}
