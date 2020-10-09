class Test extends Scene {
  static int[][] field = { {1, 2, 0},
                       {0, 0, 2},
                       {1, 2, 1} };
  static int player = 1;

  public static void printAry(int[][] ary) {
    for (int i = 0; i < ary.length; i++) {
      System.out.print("ary[" + i + "]");
      for (int j = 0; j < ary[i].length; j++) {
        System.out.print(ary[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * int[]型の配列のインスタンスをコピーするメソッド。
   *
   * @param ary　コピー元のインスタンスへのポインタ
   * @return コピーしたインスタンス
   */
  static int[] cloneAry(int[] ary) {
    int[] ans = new int[ary.length];
    for (int i = 0; i < ary.length; i++) {
      ans[i] = ary[i];
    }
    return ans;
  }

  /**
   * int[][]型の配列のインスタンスをコピーするメソッド。
   * メソッドcloneAryの多重定義とする。
   *
   * @param ary　コピー元のインスタンスへのポインタ
   * @return コピーしたインスタンス
   */
  static int[][] cloneAry(int[][] ary) {
    int[][] ans = new int[ary.length][];
    for (int i = 0; i < ary.length; i++) {
      ans[i] = cloneAry(ary[i]);
    }
    return ans;
  }

  public static void main(String[] args) {
    int[][] ary = {{1,2,3}, {4,5,6,7}};
    System.out.println("ary:");
    for (int i = 0; i < ary.length; i++) {
      for (int j = 0; j < ary[i].length; j++) {
        System.out.print(ary[i][j]);
      }
      System.out.println();
    }

    int[][] aryCopy = cloneAry(ary);
    System.out.println("aryCopy:");
    for (int i = 0; i < aryCopy.length; i++) {
      for (int j = 0; j < aryCopy[i].length; j++) {
        System.out.print(aryCopy[i][j]);
      }
      System.out.println();
    }

    /*
    int[][] a = {{1,2,3}, {4,5,6}};
    int[][] b = a.clone();

    System.out.println("配列a");
    printAry(a);
    System.out.println("配列b");
    printAry(b);

    b[1][0] = 100;

    System.out.println("配列a");
    printAry(a);
    System.out.println("配列b");
    printAry(b);

     */
  }

}
