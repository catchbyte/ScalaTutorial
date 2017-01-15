package tutorial.OperationsTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

class OperationTutorialTest extends JUnit3Suite{

  @Test
  def testScalaOperatorStyle(): Unit = {
    val s = "Hello, World!"
    val result = s indexOf 'o' // Scala invokes s.indexOf('o')
    assert(result == 4)
  }

  @Test
  def testLongTypeNumber(): Unit = {
    val n = 2L * 3L
    assert(n.isInstanceOf[Long])
  }

  @Test
  def testFloatTypeNumber(): Unit = {
    val n = 11.0f * 4.0f
    assert(n.isInstanceOf[Float])
  }

  @Test
  def testDoubleTypeNumber(): Unit = {
    val n = 1.2 + 3.5
    assert(n.isInstanceOf[Double])
  }

  @Test
  def testNegativeNumber(): Unit = {
    val n = 1 + -3
    assert(n == -2)
    assert(-n == 2)
  }

  @Test
  def testPositiveNumber(): Unit = {
    val n = +3
    assert(n == 3)
  }

  @Test
  def testLogicalOperation(): Unit = {
    val untrue = !true
    val isTrue = true
    val case1 = isTrue || !isTrue
    val case2 = isTrue && !isTrue
    assert(!untrue)
    assert(case1)
    assert(!case2)
  }

  @Test
  def testLeftShiftOperation(): Unit = {
    val result = 2 + 2 << 2
    assert(result == 16)
  }
}
