package pl.project13.protoscala

case class Point(x: Double, y: Double)

abstract class Shape() {
  def draw(): Unit
}

case class Circle(center: Point, radius: Double) extends Shape() {
  def draw() = println("Circle.draw: " + this)
}

case class Rectangle(lowerLeft: Point, height: Double, width: Double)
  extends Shape() {
  def draw() = println("Rectangle.draw: " + this)
}

case class Triangle(point1: Point, point2: Point, point3: Point)
  extends Shape() {
  def draw() = println("Triangle.draw: " + this)
}

object ExapleCase {
  def main(args: Array[String]) = {
    var lista = Circle(center = Point(15, 15), radius = 15) :: Circle(center = Point(15, 15), radius = 30) :: Nil

    lista.foreach(_ match{
      case Circle(center, 15) =>
        println("circle")
      case Circle(center, 30) =>
        println("circle 2")
      case _ =>
        println("hmmm")
    })
  }
}