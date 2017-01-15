package tutorial.BuiltInTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite
import java.io._
import java.net.{MalformedURLException, URL}
import scala.util.control.Breaks._

class BuiltInTest extends JUnit3Suite {

  val aList = List("hello.txt", "world.jsp", "testing-process.php", "apache.imi", "Iam.scala")

  // while loops don't result in a value, to make any kind of difference
  // to your program, a while loop will usually either need to update varS
  // or perform I/O.
  // Be a bit suspicious of while loops in your code.
  // If there isn't a good justification for a particular while of do-while loop,
  // try to find a way to do the same thing without it.
  @Test
  def testWhileLoop(): Unit ={
    def gcdLoop(x: Long, y: Long) = {
      var a = x
      var b = y
      while(a != 0){
        val temp = a
        a = b % a
        b = temp
      }
      b
    }
    val i = gcdLoop(5, 10)
    assert(i == 5)
  }

  // Result : 1, 2, 3, 4
  @Test
  def testBasicForLoop(): Unit = {
    val aList = List(1,2,3,4)
    def basicForLoop(): Unit = {
      for (i <- 1 to 4)
        assert(i == aList(i))
    }
  }

  // Don't want to include the upper bound of the range
  // in the values that are iterated over, use until instead of to.
  // Result : 1, 2, 3
  @Test
  def testBasicForLoop2(): Unit = {
    val aList = List(1,2,3,4)
    def basicForLoop(): Unit = {
      for (i <- 1 until 4)
        assert(i == aList(i))
    }
  }

  // Result : 1, 2, 3, 4, 5, 6
  @Test
  def testBasicForLoop3(): Unit = {
    val aList = List(1,2,3,4,5,6,7)
    def basicForLoop(): Unit = {
      for (i <- 1 to aList.length - 1) assert(i == aList(i))
    }
  }

  // Instead of returning a value in the normal way,
  // a method can terminate by throwing an exception.
  // The method's caller can either catch and handle that exception,
  // or it can itself simply termnate, in which case the exception
  // propagates to the caller's caller.
  // The exception propagates in this way, unwinding the call stack,
  // until a method handles it or there are no more methods left.
  // If n is not even, an exception will be thrown before half can be initialized
  // to anything at all. Because of this, it is safe to treat a thrown exception
  // as any kind of value whatsoever.
  // * Technically, an exception throw has type Nothing.
  @Test
  def testRuntimeException(): Unit ={
    def canDividedByZero(n: Int) = {
      val half =
        if (n % 2 == 0)
          n / 2
        else
          throw new RuntimeException("n must be even")
      half
    }
    canDividedByZero(3)
  }

  // One difference in Scala is that, unlike Java,
  // Scala does not require you to catch checked exception or
  // declare them in a throws caluse.
  // May declare a throws clause with the @throws annotation, but it is not required.
  @Test
  def testTryCatch(): Unit = {
    try{
      val f = new FileReader("input.txt")
    }catch{
      case ex: FileNotFoundException =>
      case ex: IOException =>
    }
  }

  // If an exception is thrown but not caught, the expression has
  // has no result at all. The value computed in the finally clause.
  // Usually finally clauses do some kind of clean up such as closing a file.
  // Normally, they should not change the value computed in the main body
  // or a catch clause of the try.
  @Test
  def testCorrectURL(): Unit ={
    val p = "a"
    val correctP = "http://www.scala-lang.org"
    def urlFor(path: String) = {
      try{
        new URL(path)
      }catch {
        case e: MalformedURLException => new URL("http://www.scala-lang.org")
      }
    }
    assert(urlFor(p).toString == correctP)
  }

  // Scala's behavior differes from Java only because Java's try-finally
  // does not result in a value. As in Java, if a finally clause includes
  // an explicit return statement, or throws an exception, that return value
  // or exception will "overrule" any previous one that originated in the try block
  // or one of its catch clauses.
  // * IMPORTANT !!
  // It is usually best to avoid returning values from finally clauses.
  // The best way to think of finally clauses is as a way to ensure some side effect happens.
  // ex) closing an open file.
  @Test
  def testScalaTryFinallyBehavior(): Unit = {
    def f(): Int = try return 1 finally return 2
    def g(): Int = try 1 finally 2
    assert(f == 2)
    assert(g == 1)
  }

  // Scala's match expression lets you select from a number of alternatives,
  // just like switch statements in other languages. In general a match
  // expression lets you select using arbitrary patterns.
  // The default case is specified with an underscore, a wildcard symbol
  // frequently used in Scala as a placeholder for a completely unknown value.
  // * A few important differences from Java's switch statement.
  // 1. Any kind of constant, as well as other things, can be used in cases in Scala.
  // not just the integer-type, enum, and string constants of Java's case statements.
  // 2. There are no breakS at the end of each alternative.
  // Instead the break is implicit, and there is no fall through from one alternative to the next.
  // The most significant difference from Java's switch, however, may be that match expressions result in a value.
  @Test
  def testMatchExpression(): Unit = {
    def addAandB(a: Int, b: Int) = {
      val r = a + b
      r
    }
    val aList = List("add", "Jeff", "Programmer", 19900404)
    val n = if(aList.length > 0) aList(0) else ""

    // The value that results from this match expression is stored in the rst variable.
    // It would work just as well to yield the value rather than print it.
    val rst = n match {
      case "add" => addAandB(4, 10)
      case "chips" => println("salsa")
      case 19900404 => println(27)
      case "Jeff" => println("badass")
      case _ => println("huh????")
    }
    assert(rst == 14)
  }

  // To get rid of the break, you would normally add a boolean variable
  // indicating whether to keep going, but in this case you can reuse foundIt.
  @Test
  def testLookingforScalaFile(): Unit ={
    def findScalaFile(): Unit = {
      var i = 0
      var foundIt = false
      while (i < aList.length && !foundIt){
        if(!aList(i).startsWith("-")){
          if(aList(i).endsWith(".scala"))
            foundIt = true
        }
        i += 1
      }
    }
    findScalaFile()
  }

  // In order to get rid of the varS from the previous code, one
  // approach you could try is to rewrite the loop as a recursive func.
  // Following code uses recursion to substitute for looping.
  // Each continue is replaced by a recursive call with i + 1 as the argument,
  // effectively skipping to the next integer.
  @Test
  def testGetRidOfReassignableVar(): Unit = {
    def searchFrom(i: Int): Int = {   // WARNING - Recursive method needs result type.
      if (i >= aList.length) -1
      else if (aList(i).startsWith("-")) searchFrom(i + 1)
      else if (aList(i).endsWith(".scala")) i
      else searchFrom(i + 1)
    }
    val i = searchFrom(0)
  }

  // The Scala compiler will not actually emit a recursive func.
  // All of the recursive calls are in tail-call position,
  // the compiler will generate code similar to a while loop.

  // To use break, there's help in Scala's standard library.
  // Class Breaks in package scala.util.control offers a break method,
  // which can be used to exit an enclosing block that's marked with breakable.
  @Test
  def testBreakable(): Unit ={
    val in = new BufferedReader(new InputStreamReader(System.in))
    breakable {
      while(true){
        println("? ")
        if (in.readLine() == "") break()
      }
    }
  }

  @Test
  def testVariableScope(): Unit = {
    def printMultiTable() = {
      var i = 1 // Only i in scope here
      while (i <= 10){
        var j = 1 // Both i and j in scope here
        while (j <= 10){
          val prod = (i * j).toString // i, j and prod in scope here
          var k = prod.length // i, j, prod and k in scope here
          while (k < 4){
            print(" ")
            k += 1
          }
          print(prod)
          j += 1
        }
      }
      // i and j still in scope; prod and k out of scope.
      println()
      i += 1
    }
    // i still in scope; j, prod, and k out of scope.
    printMultiTable()
  }

  // Can not define a new variable with the same name in the same scope.
  // val a = 1
  // val a = 2 Does not compile.
  // You can, on the other hand, define a variable in an inner scope
  // that has the same name as a variable in an outer scope.
  /*
  *  val a = 1;
  *  {
  *   val a = 2 compiles just fine.
  *   println(a)
  *  }
  *  println(a)
  * */

  // Difference to note between Scala and Java
  // Java will not let you create a variable in an inner scope that has the
  // same name as a variable in an outer scope.


}
