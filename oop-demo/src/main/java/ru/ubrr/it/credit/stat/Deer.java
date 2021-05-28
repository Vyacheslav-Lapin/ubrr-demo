package ru.ubrr.it.credit.stat;

public class Deer {
  public Deer() { System.out.print("Deer"); }
  public Deer(int age) { System.out.print("DeerAge"); }
  private boolean hasHorns() { return false; }

  public static void main(String[] args) {
    Deer deer = new RainDeer(5);
    System.out.println(", " + deer.hasHorns());
  }
}

class RainDeer extends Deer {
  public RainDeer(int age) { System.out.print("ReinDeer"); }
  public boolean hasHorns() { return true; }
}
