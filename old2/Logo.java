public class Logo {

  static String putString(String s, int n) {
    String putString = "";
    while (n-- > 0) {
      putString += s;
    }
    return putString;
  }

  public static String out(int n) {
    String out = "\n\n\n\n\n\n\n\n";
    String[] s = {"+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+",
                  "*                                       *",
                  "+     ***      *      *   *    ****     +",
                  "*    *   *    * *    * * * *   *   *    *",
                  "+    *        ***    *  *  *   ****     +",
                  "*    *   *   *   *   *  *  *   *        *",
                  "+     ***    *   *   *  *  *   *        +",
                  "*                                       *",
                  "+     ***      *      *   *    ****     +",
                  "*    *   *    * *    * * * *   *        *",
                  "+    *        ***    *  *  *   ****     +",
                  "*    * ***   *   *   *  *  *   *        *",
                  "+     ** *   *   *   *  *  *   ****     +",
                  "*                                       *",
                  "+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+",
                  "",
                  "          Human vs Human ... 1",
                  "            Bot vs   Bot ... 2",
                  "          Human vs   Bot ... 3",
                  "             Change side ... 4",};
    for (int i = 0; i < s.length; i++) {
      out += putString(" ", n) + s[i] + "\n";
    }
    return out;
  }
}
