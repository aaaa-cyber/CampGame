class Scene extends Player {

  //任意の文字列をn個表示
  static void putStr(String s, int n) {
    while (n-- > 0) {
      System.out.print(s);
    }
  }

  //任意の文字列をn個出力
  static String outStr(String s, int n) {
    String out = "";
    while (n-- > 0) {
      out += s;
    }
    return out;
  }

  //画面のリフレッシュ
  static void clearScene() {
    putStr("\n", 15);
  }

  //スタート画面
  static void startScene() {
    clearScene();
    //Logo.outの引数はインデント
    putStr(Logo.out(5), 1);
  }

  //ゲーム画面
  static void gameScene(int[][] field) {
    int indent = 10;
    String print = "";

    print += "行\\列\n";
    print += outStr(" ", indent) + "  1   2   3 \n";
    print += outStr(" ", indent) + " --- --- ---\n";

    for (int i = 0; i < 3; i++)  {
      print += outStr(" ", indent - 1);
      print += i + 1;
      for (int j = 0; j < 3; j++) {
        if (field[i][j] == 0) {
          print += "|   ";
        } else if (field[i][j] == 1) {
          print += "| ○ ";
        } else {
          print += "| × ";
        }
      }
      print += "|\n" + outStr(" ", indent) + " --- --- ---\n";
    }

    putStr(print, 1);

  }

  //リザルト画面
  static void resultScene(int[][] field) {
    gameScene(field);
    if (win(field) > 0) {
      System.out.println("\nPlayer" + win(field) + "さんの勝ちです。");
    } else {
      System.out.println("引き分けです。");
    }
  }
}
