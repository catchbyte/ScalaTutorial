package tutorial.FunctionalObjectsTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

class RationalTest extends JUnit3Suite {
  val aa = 10
  val bb = 20

  override def toString = aa + "/" + bb

  case class Rational(n: Int, d: Int){
    val a = n
    val b = d

    // Won't work.
    // def this(n: Int) = this(n, 1)

    def - (a: Int, b: Int)= {
      val result = a - b
      result
    }

    def - (a: Int) = {
      val temp = 1
      val result = a - temp
      result
    }

    override def toString = n + "/" + d
  }

  class HelloRational(n: Int, d: Int){
    // An auxiliary constructor
    // Can not use auxiliary constructor in case class.
    def this(n: Int) = this(n, 1)

    def * (r: Rational) = new Rational(r.a * aa, r.b * bb)

    override def toString = if(d < 2 && d > 0) n.toString else if(d < 0) d +" "+"must be higher than zero." else n + "/" + d

  }

  @Test
  def testR(): Unit = {
    val x = Rational(1, 3)
    println(x.isInstanceOf[Rational])
    assert(x.toString == "1/3")
  }

  @Test
  def testI(): Unit ={
    val r = new Rational(1, 2)
    assert(r.a == 1)
    assert(r.b == 2)
  }

  @Test
  def testFindMax(): Unit = {
    val rt = Rational(2, 3)
    def lessThan(r: Rational) = this.aa * r.b < r.a * this.bb
    def max(r: Rational) = if (lessThan(r)) r else this
    assert(max(rt).toString == "2/3")
  }

  @Test
  def testAuxiliary(): Unit = {
    val x = new HelloRational(5)
    val y = new HelloRational(5, 2)
    val z = new HelloRational(5, -1)
    assert(x.toString == "5")
    assert(y.toString == "5/2")
    assert(z.toString == "-1 must be higher than zero.")
  }

  @Test
  def testSubtraction(): Unit = {
    val r = Rational(5,4)
    val rst = r.-(r.a)
    val rst2 = r.-(r.a, r.b)
    assert(rst == 4)
    assert(rst2 == 1)
  }

  // val x = new Rational(2, 3)
  // x: Rational = 2/3
  // x * 2
  // Rational = 4/3
  // Implicit conversions----------------
  // Can write r * 2 but 2 * r does not work yet.
  // 2 * r is quivalent to 2.*(r), so it is a method call on the number 2,
  // which is an Integer. But the Int class contains no multiplicaiton method.
  // IMPORTANT !!
  // An impicit conversion that automatically converts integers to rational numbers when needed.

  // This defines a conversion method from Int to Rational.
  // The implicit modifier in front of the method
  // tells the copiler to apply it automatically in a number of situations.
}
