import java.util.Scanner;
import java.util.Random;

public class Player implements Seat {
  int botOrHuman; //人間かbotか。
  Field f; //CampGameクラスで生成したFieldクラスのインスタンスを参照する。
  int x, y; // x...列, y...行

  //botOrHuman = 0...bot, 1...human
  public Player(Field f, int botOrHuman) {
    this.f = f;
    this.botOrHuman = botOrHuman;
  }

  //botかhumanかを返す。 0...bot, 1...human
  public int getType() {
    return botOrHuman;
  }

  public void putStone() {
    Scanner stdIn = new Scanner(System.in);
    Random rand = new Random();

    //人間の場合
    if (botOrHuman == 1) {
      System.out.println("どこに置きますか。");
      while (true) {
        while (true) {
          System.out.print("行:");
          x = stdIn.nextInt();
          if (x < 0 || x > f.getside()) {
            System.out.println("適当な座標を入力してください。");
          } else {
            break;
          }
        }
        while (true) {
          System.out.print("列:");
          y = stdIn.nextInt();
          if (y < 0 || y > f.getside()) {
            System.out.println("適当な座標を入力してください。");
          } else {
            break;
          }
        }

        //重複の判定
        if (f.checkDuplicate(x - 1, y - 1)) {
          System.out.println("重複しています。");
        } else {
          break;
        }
      }
    } else {

      //Botの場合
      while (true) {
        x = rand.nextInt(f.getside()) + 1;
        y = rand.nextInt(f.getside()) + 1;
        if (!f.checkDuplicate(x - 1, y - 1)) break; //重複の判定
      }
    }
  }

  //先ほど置いた石の座標を取得。
  public int[] getXY() {
    int[] ans = {x, y};
    return ans;
  }
}
