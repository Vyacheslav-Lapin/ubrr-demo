package ru.ubrr.it.credit.grasp;

import lombok.AllArgsConstructor;
import lombok.Getter;

public abstract sealed class TableRow permits CircleTableRow, SquareTableRow {

  public abstract void toTableString();

  public static void main(String[] args) {
    toTableString(new TableRow[] {
        new CircleTableRow(5),
        new SquareTableRow(2),
        new SquareTableRow(8)
    });
  }

  public static void toTableString(TableRow[] tables) {
    System.out.println("+----------+-----------+---------+\n|  Type    | parameter |  value  |\n+----------+-----------+---------+");
    for (TableRow table : tables) {
      table.toTableString();
      System.out.println("+----------+-----------+---------+");
    }

  }

//  public static void toTableString(TableRow[] tables) {
//    System.toTableString.println("+----------+-----------+---------+\n|  Type    | parameter |  value  |\n+----------+-----------+---------+");
//
//    for (TableRow tableRow : tables) {
//      if (tableRow instanceof CircleTableRow table)
//        System.out.printf("|  Circle  |  radius   |  %5.2f  |\n", table.getRadius());
//      else if (tableRow instanceof SquareTableRow table)
//        System.out.printf("|  Square  |  side     |  %5.2f  |\n", table.getSide());
//
//      System.toTableString.println("+----------+-----------+---------+");
//    }
//  }
}

@Getter
@AllArgsConstructor
final class CircleTableRow extends TableRow {
  double radius;

  @Override
  public void toTableString() {
    System.out.printf("|  Circle  |  radius   |  %5.2f  |\n", radius);
  }
}


@Getter
@AllArgsConstructor
final class SquareTableRow extends TableRow {
  double side;

  @Override
  public void toTableString() {
    System.out.printf("|  Square  |  side     |  %5.2f  |\n", side);
  }
}
