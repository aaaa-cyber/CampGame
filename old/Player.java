import java.util.Scanner;

public class Player extends GameFrame {
  Scanner stdIn = new Scanner(System.in);

  //playerが置いた場所の記録
  int[][] player = new int[h][v];

  //座標を引数とし、置けたらfalse,そうでなければtrueを返却
  public boolean putStone(int putH, int putV) {
    boolean evaluate = false;

    evaluate = judgeDuplicate(putH, putV);
    if (evaluate) {
      return evaluate;
    } else {
      placement[putH][putV] = 1;
      player[putH][putV] = 1;
      countStone += 1;
      return evaluate;
    }
  }

  //位置取得の実装
  public int[][] getWhere() {
      return player;
  }


}
