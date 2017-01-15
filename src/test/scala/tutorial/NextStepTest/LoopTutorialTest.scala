package tutorial.NextStepTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

class LoopTutorialTest extends JUnit3Suite {

  // val, the variable can not be reassigned, but the object to which it refers could potentially still be changed.
  val aList = List(10, 20, 30, 40, 50)

  // greetStrings can not be reassigned to a different array.
  // greetStrings will always points to the same Array[String] instance with which it was initialized.
  val greetStrings = new Array[String](3)

  /*
  * Initialize each element of the greetStrings array.
  * Not square brackets as in Java.
  * */
  greetStrings(0) = "This is"
  //
  // greetStrings(0) = "This is" will be transfored into:
  // greetStrings.udpate(0, "This is")
  greetStrings(1) = "my first"
  greetStrings(2) = "Scala tutorial"


  @Test
  def testCanIterateLoop: Unit = {
    // The code 0 to 2 is transformed into the method call (0).to(2)
    // Apply parentheses surrounding one or more values to a variable, Scala will transform
    // the code into an invocation of a method named apply on that variable.
    // So greetString(i) gets transformed into greeStrings.apply(i).
    // Any application of an object to some arguments in parentheses will be transformed to an apply method call.
    // This will compile only if that type of object actually defined an apply method.
    // So it's not a special case; it's a general rule.
    for(i <- 0 to 2)
      println(greetStrings(i))

    for(i <- 0 to greetStrings.length - 1)
      println(greetStrings(i))
  }

  @Test
  def testCanLoopList: Unit = {
    aList.foreach(e => assert(e < 51))
  }
}
