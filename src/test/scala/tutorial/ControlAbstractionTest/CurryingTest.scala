package tutorial.ControlAbstractionTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

class CurryingTest extends JUnit3Suite {

  @Test
  def testBasicCurrying(): Unit ={
    def plainOldSum(x: Int, y: Int) = x + y
    assert(plainOldSum(1, 2) == 3)
  }

  @Test
  def testTwoListsCurrying(): Unit ={
    def curriedSum(x: Int)(y: Int) = x + y
    assert(curriedSum(1)(2) == 3)

    // The underscore in curriedSum(1)_ is a placeholder
    // for the second parameter list.
    val onePlus = curriedSum(1)_
    assert(onePlus(2) == 3)

  }

  @Test
  def testDefiningAndInvokingCurriedFnc(): Unit = {
    def first(x: Int) = (y: Int) => x + y
    val second = first(1)
    assert(second(2) == 3)
  }

}
