package ru.ubrr.it.credit;

public enum Day {
  SUNDAY,
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY;

  public boolean isWeekend() {
    return switch (this) {
      case SUNDAY, SATURDAY -> true;
      default -> false;
    };
  }

  public static void main(String[] args) {
    System.out.println(Day.MONDAY + " isWeekEnd(): " + Day.MONDAY.isWeekend());
  }
}
