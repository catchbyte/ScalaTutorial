package tutorial.NextStep

import java.time.temporal.JulianFields

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

/**
  * Created by JeffMinsungKim on 11/01/2017.
  */
class FunctionalStyleTest extends JUnit3Suite {

  /*
  * IMPORTANT!!
  * Bear in mind that neither varS nor side effects are inherently evil.
  * Scala is not a pure functional language
  * that forces you to program everything in the functional style.
  * Scala is a hybrid imperative/functional language.
  * You may find that in some situations an imperative style is a better fit
  * for the problem at hand, and in such cases you should not hesitate to use it.
  * -----------------A balanced attitude for Scala programmers
  * Prefer valS, immutable objects, and methods without side effects. Reach for them first.
  * Use varS, mutable objects, and methods with side effects when you have a specific need
  * and justification for them.
  * */

  @Test
  def testPrintNumbersInImperativeStyle(): Unit = {
    def printNumbers(): Unit = {
      var i = 0
      while (i < 10){
        println(i)
        i += 1
      }
    }

    printNumbers()
  }

  // The refactored (more functional) code is clearer, more concise,
  // and less error-prone than the original (more imperative) code.
  @Test
  def testPrintNumbersInFunctionalStyle(): Unit = {
    def printNumbers(): Unit = {
      var i = 0
      for(i <- 0 to 10)
        println(i)
    }

    // The refactored printNumbersAnotherWay method is not purely functional
    // because it has side effects-in this case, its side effect is printing to
    // the standard output stream.
    def printNumbersAnotherWay(word: Array[String]): Unit = {
      word.foreach(println)
    }

    // A more functional approach would be to define a method that formats
    // the passed word for printing, but just returns the formatted string as shown below.
    def formatWord(word: Array[String]) = word.mkString(",")
    printNumbers()
    printNumbersAnotherWay(Array("HelloWorld", "Jeff Kim"))
    assert("Hi,Jeff" == formatWord(Array("Hi", "Jeff")))
  }



}
