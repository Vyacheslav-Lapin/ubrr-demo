package ru.ubrr.it.credit;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Point {

  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj)
               || obj instanceof Point that
                      && canEquals(that)
                      && x == that.x
                      && y == that.y;
  }

  protected boolean canEquals(Point __) {
    return true;
  }

  public static void main(String... __) {
    System.out.println("new Point(1,2).toString() = " + new Point(1, 2));

    main();
  }

  @Override
  public int hashCode() {
    return Objects.hash(1, 2);
  }

  @Override
  public String toString() {
    return "Point{" +
               "x=" + x +
               ", y=" + y +
               '}';
  }
}

class ColoredPoint extends Point {

  private int color;

  public ColoredPoint(int x, int y, int color) {
    super(x, y);
    this.color = color;
  }

  public ColoredPoint(int x, int y) {
    this(x + new Random().nextInt(), y - new Random().nextInt(), 0);
    System.out.println(this);
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj)
               || obj instanceof ColoredPoint coloredPoint
                      && color == coloredPoint.color;
  }

  @Override
  protected boolean canEquals(Point point) {
    return point instanceof ColoredPoint || color == 0;
  }

}
