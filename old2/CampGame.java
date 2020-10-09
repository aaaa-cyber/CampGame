import java.util.Scanner;
import java.util.Random;

class CampGame {

  static String rtnName(Player a) {
    if (a.getType() == 0) {
      return "Bot";
    } else if (a.getType() == 1) {
      return "Human";
    } else {
      return "?";
    }
  }

  public static void main(String[] args) {
    Scanner stdIn = new Scanner(System.in);
    Random rand = new Random();

    Field f; //盤面。１辺が決まったら定義する。
    int side = 3;  //1辺の大きさ
    String fName = ""; //先攻の名前
    String dName = ""; //後攻の名前
    String currentPlayer = ""; //現在石を置くプレイヤー
    String breforePlayer = ""; //先ほど石を置いたプレイヤー
    int playMode = 0;
    Player forward, defense; //先攻、後攻の挙動をplayModeの値によって初期化

    //モード選択
    while (true) {
      System.out.println(Logo.out(Field.indent));
      while (playMode < 1 || playMode > 4) {
        //盤面が変更されていれば、現在の1辺の大きさを表示する。
        if (playMode == - 1) {
          System.out.println("現在の１辺は" + side + "です。");
        }
        System.out.print("モードを選んでください。:");
        playMode = stdIn.nextInt();
      }

      //１辺の大きさがsideの盤面を生成。デフォルトは3×3。
      f = new Field(side);

      //先攻,後攻の初期化
      if (playMode == 1) {
        forward = new Player(f, 1);
        defense = new Player(f, 1);
        break;
      } else if (playMode == 2) {
        forward = new Player(f, 0);
        defense = new Player(f, 0);
        break;
      } else if (playMode == 2) {
        int randomNum = rand.nextInt(2);
        if (randomNum == 0) {
          forward = new Player(f, 1);
          defense = new Player(f, 0);
        } else {
          forward = new Player(f, 0);
          defense = new Player(f, 1);
        }
        break;
      } else {
        //盤面の１辺の大きさの変更。
        System.out.println("現在の１辺は" + side + "です。");
        while (true) {
          System.out.print("一辺:");
          side = stdIn.nextInt();
          if (side >= 3) {
            break;
          } else {
            System.out.println("3以上の整数を入力してください。");
          }
        }
        //盤面を変更したことを記録。
        playMode = - 1;
      }
    }

    int x = 0;
    int y = 0;

    //同じプレイヤー同士（Bot vs Bot,Human vs Human）だと見分けがつかないため
    //末尾に数字を振る。
    if (playMode == 1 || playMode == 2) {
      fName = rtnName(forward) + 1;
      dName = rtnName(defense) + 2;
    } else {
      fName = rtnName(forward);
      dName = rtnName(defense);
    }

    while (true) {

      //今石を置くプレイヤー
      currentPlayer = (f.getCount() % 2 == 0) ? fName : dName;

      //盤面表示
      System.out.println(f.print());

      //前のプレイヤーが石を置いた座標を表示。
      if (f.getCount() > 0) {
        System.out.printf(breforePlayer + " < (%2d,%2d)\n", x, y);
      }

      //次のプレイヤーの番にまわる前に、勝負がついたか確認。
      if (f.judgement()[0] == 1) break;

      //現在のプレイヤーを表示。
      System.out.println(currentPlayer + "さんの番です。");

      //現在が先攻の番か後攻の番かを石の数から確認し、石を置く。
      if (f.getCount() % 2 == 0) {
        forward.putStone();
      } else {
        defense.putStone();
      }

      //プレイヤー交代
      breforePlayer = currentPlayer;

      //前のプレイヤーが置いた石の座標
      x = (f.getCount() % 2 == 1) ? forward.getXY()[0] : defense.getXY()[0];
      y = (f.getCount() % 2 == 1) ? forward.getXY()[1] : defense.getXY()[1];

    }

    //勝者がいる場合
    if (f.judgement()[1] >= 0) {
      //勝者の名前は？
      String winner = (f.judgement()[1] == 0) ? fName : dName;
      /*自動で最後のマスを埋めた場合、最後の盤面の表示がスキップされるため
        ここで表示*/
      if (f.getTrig() == 1) {
        System.out.println(f.print());
        System.out.printf(breforePlayer + " < (%2d,%2d)\n", f.endXY()[0], f.endXY()[1]);
      }
      System.out.println(winner + "さんの勝利です。");
    } else {
      //勝者がいない場合
      if (f.getTrig() == 1) {
        System.out.println(f.print());
        System.out.printf(breforePlayer + " < (%2d,%2d)\n", f.endXY()[0], f.endXY()[1]);
      }
      System.out.println("引き分けです。");
    }
  }
}
