class CampGame {

  public static void main(String[] args) {
    do {
      Field.print();
      System.out.println("player" + Field.getPlayer() + "さんの番です。");
      Player.move(Field.getPlayer(), Field.getField());
      Field.changePlayer();
    } while (Field.countStones() > 0);
  }
}
