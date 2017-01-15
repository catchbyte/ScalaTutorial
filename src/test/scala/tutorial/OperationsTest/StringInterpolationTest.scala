package tutorial.OperationsTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

class StringInterpolationTest extends JUnit3Suite {


  // Scala provides two other string interpolators by default: raw and f.
  // The raw string interpolator behaves like s, except it does not recognize
  // character literal escape sequences.
  // For example, the following statement prints four backslashes, not two.
  // println(raw"No\\\\escape!") prints: No\\\\escape!
  // In Scala, string interpolation is implemented by rewriting code at compile time.

  @Test
  def testStringInterpolation(): Unit = {
    val name = "reader"
    assert(s"Hello!, $name" == "Hello!, reader")
  }

  @Test
  def testSumStringInterpolation(): Unit = {
    assert(s"${6 * 7}".toInt == 42)
  }

  // The f string interpolator allows you to attach printf-style formatting
  // instructions to embedded expressions.
  @Test
  def testFInterpolator(): Unit = {
    val pi = "Pi"
    print(f"$pi is approximately ${math.Pi}%.8f")
  }
}
