import java.util.Scanner;

public class Player {
  static Scanner stdIn = new Scanner(System.in);

  public static int[] setPlace() {
    int x, y;
    int[] setPlace = new int[2];
    while (true) {
      System.out.print("x:");
      x = stdIn.nextInt();
      if(x >= 1 && x <= 3) {
        setPlace[0] = x - 1;
        break;
      }
    }
    while (true) {
      System.out.print("y:");
      y = stdIn.nextInt();
      if(x >= 1 && x <= 3) {
        setPlace[1] = y - 1;
        break;
      }
    }
    return setPlace;
  }

  public static boolean checkDuplicate(int x, int y, int player, int[][] field) {
    boolean checkDuplicate = false;
    if (field[x][y] > 0) {
      checkDuplicate = true;
      System.out.println("重複しています。");
    }
    return checkDuplicate;
  }

  public static void move(int player, int[][] field) {
    int[] p;
    do {
      p = setPlace();
    } while (checkDuplicate(p[0], p[1], player, field));
    field[p[0]][p[1]] = player;
  }

}
