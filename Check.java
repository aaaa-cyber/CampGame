class Check extends SuperBot {

  //勝利条件が満たされているか。勝負がつけば1,2を、つかなければ0を返す。
  static int win(int[][] field) {
    for (int i = 0; i < 3; i++) {
      if (field[i][0] == field[i][1] && field[i][0] == field[i][2]) {
        //横判定
        return field[i][0];
      }
      if (field[0][i] == field[1][i] && field[0][i] == field[2][i]) {
        //縦判定
        return field[0][i];
      }
    }
    if (field[0][0] == field[1][1] && field[0][0] == field[2][2]
                || field[2][0] == field[1][1] && field[2][0] == field[0][2]) {
      //斜め判定
      return field[1][1];
    }
    return 0;
  }

  //ブランクがあるか
  static int end(int[][] field) {
    int count0 = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (field[i][j] == 0) {
          count0++;
        }
      }
    }
    return count0;
  }

}
