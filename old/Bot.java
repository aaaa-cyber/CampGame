import java.util.Random;

//ランダムに置く雑魚bot
public class Bot extends GameFrame {
  Random rand = new Random();

  //botが置いた場所の記録
  int[][] bot = new int[h][v];

  public void putStone() {
    int[] botPlace = new int[2];
    boolean evaluate = false;

    do {
      //botplace[0]=横位置、botplace[1]=縦位置
      botPlace[0] = rand.nextInt(h);
      botPlace[1] = rand.nextInt(v);

      evaluate = judgeDuplicate(botPlace[0], botPlace[1]);
      if (evaluate) {
        System.out.println("bot:置き場所を間違えました。");
      } else {
        placement[botPlace[0]][botPlace[1]] = 1;
        bot[botPlace[0]][botPlace[1]] = 1;
        countStone += 1;
      }
    } while (evaluate);
  }

  //位置取得の実装
  public int[][] getWhere() {
    return bot;
  }


}
