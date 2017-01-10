package tutorial

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

/**
  * Created by JeffMinsungKim on 10/01/2017.
  */
class SumTest extends JUnit3Suite{

  val a = 3
  val b = 4

  def addNum(a: Int, b: Int) = {
    val c = a + b
    c
  }

  @Test
  def testAddNum : Unit = {
    val c = addNum(a, b)
    assert(c == 7)
  }

}
