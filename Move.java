import java.util.Scanner;
//import java.util.InputMismatchException;

class  Move extends Check {
  static Scanner stdIn = new Scanner(System.in);

  static int[] getCoordinate(int[][] field) {
    int[] Coordinate = new int[2];
    int h, v;

    while (true) {
      System.out.print("置く座標を次のように入力してください。 (x,y) :");
      String s = stdIn.next();
      String[] position = s.split(",", 0);
      try {
        h = Integer.parseInt(position[0]);
        v = Integer.parseInt(position[1]);
      } catch (Exception e) {
        System.out.println(e + "\n座標を正しく入力してください。");
        continue;
      }
      if (field[h - 1][v - 1] > 0) {
        System.out.println("重複しています。");
      } else {
        Coordinate[0] = h - 1;
        Coordinate[1] = v - 1;
        break;
      }
    }

    return Coordinate;

    /*
    int h, v;
    int[] Coordinate = new int[2];
    LOOP : while (true) {
      try {
        while (true) {
          System.out.print("行:");
          h = stdIn.nextInt();
          if (h >= 1 && h <= 3) {
            break;
          } else {
            System.out.println("1~3の整数を入力してください。");
          }
        }
        while (true) {
          System.out.print("列:");
          v = stdIn.nextInt();
          if (v >= 1 && v <= 3) {
            break;
          } else {
            System.out.println("1~3の整数を入力してください。");
          }
        }
      } catch(InputMismatchException e) {
        System.out.println("1~3の整数を入力してください。");
        String s = stdIn.next();
        continue LOOP;
      }
      if (field[h - 1][v - 1] > 0) {
        System.out.println("重複しています。");
      } else {
        Coordinate[0] = h - 1;
        Coordinate[1] = v - 1;
        break;
      }
    }
    return Coordinate;
    */
  }

  //入力座標に従って置く
  static void putDirected(int[] Coordinate, int playerNum, int[][] field) {
    field[Coordinate[0]][Coordinate[1]] = playerNum;
  }
}
