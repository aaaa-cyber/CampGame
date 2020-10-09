public class Field {
  private static int player = 1;
  private static int[][] field = new int[3][3];

  public static int getPlayer() {
    return player;
  }

  public static int[][] getField() {
    return field;
  }

  public static void changePlayer() {
    player = (player == 1) ? 2 : 1;
  }

  public static int countStones() {
    int countStones = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (field[i][j] == 0)
        countStones++;
      }
    }
    return countStones;
  }

  public static void print() {
    System.out.println(" --- --- --- ");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (field[i][j] == 1) {
          System.out.print("| ○ ");
        } else if (field[i][j] == 2) {
          System.out.print("| × ");
        } else {
          System.out.print("|   ");
        }
      }
      System.out.println("|");
      System.out.println(" --- --- --- ");
    }
  }
}
