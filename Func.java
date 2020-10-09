import java.util.Random;

class Func extends Scene {
  static Random rand = new Random();

  //先攻を決める乱数を取得
  static int getRandPlayerNum() {
    int random = rand.nextInt(2) + 1;
    return random;
  }

  //ターンを更新し出力
  static int changeTurn(int turn) {
    return turn + 1;
  }

  //ターンの更新に合わせてプレイヤーを更新
  static int changePlayerNum(int playerNum) {
    return (playerNum == 1) ? 2 : 1;
  }

}
