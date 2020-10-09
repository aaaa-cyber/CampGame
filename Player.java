class Player extends Move {

  //プレイヤー（入力）の場合の動作
  static void human(int playerNum, int[][] field) {
    putDirected(getCoordinate(field), playerNum, field);
  }

  //エリアの更新
  static void updateArea(int switchie, int playerNum, int[][] field) {
    if (switchie == 1) {
      human(playerNum, field);
    } else {
      if (playerNum == 1) {
        human(playerNum, field);
      }  else {
        superBot(playerNum, field);
      }
    }

  }

}
