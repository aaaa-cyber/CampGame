class CampGame extends Func {
  public static void main(String[] args) {
    int[][] area = new int[3][3];
    int playerNum;
    int turn = 1;

    clearScene();
    startScene();
    System.out.print("Which mode do you choose? : ");
    int switchie = stdIn.nextInt();
    clearScene();

    playerNum = getRandPlayerNum();
    while (true) {
      clearScene();
      gameScene(area);
      System.out.println("\nPlayer" + playerNum + "さんの番です。");
      if (win(area) > 0) {
        break;
      } else if (end(area) == 0) {
        break;
      }
      updateArea(switchie, playerNum, area);
      turn = changeTurn(turn);
      playerNum = changePlayerNum(playerNum);
    }
    clearScene();
    resultScene(area);
  }
}
