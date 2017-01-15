package tutorial.FunctionsAndClosuresTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite


class FirstClassFunctionTest extends JUnit3Suite {

  val someNumbers = List(-11, -10, -5, 0, 5, 10)

  // The => designates that this function converts
  // the thing on the left (any integer x) to the thing
  // on the right (x + 1). So this is a function mapping
  // any integer x to x + 1.
  @Test
  def testFunctionLiteral(): Unit = {
    var increase = (x: Int) => x + 1
    assert(increase(10) == 11)
  }

  // If you want to have more than one statement
  // in the functional literal, surround its body by curly braces
  // and put one statement per line, thus forming a block.
  @Test
  def testFunctionalLiteralMoreThanOneStatement() {
    var increase = (x: Int) => {
      println("Hello!")
      println("My name is Jeff")
      println(x + 1)
    }
    increase(10)
  }

  @Test
  def testPrintOutAllElementsOfList() {
    someNumbers.foreach((x: Int) => println(x))
  }

  @Test
  def testDistinguishPositiveInteger() {
    val positiveInt = someNumbers.filter((x: Int) => x > 0)
    assert(positiveInt == List(5, 10))
  }

  // Short forms of function literals
  @Test
  def testDistinguishPositiveIntegerShortForm() {
    val positiveInt = someNumbers.filter((x) => x > 0)
    assert(positiveInt == List(5, 10))
  }

  // Remove useless characters is to leave out parentheses
  // around a parameter whose type is inferred.
  @Test
  def testDistinguishPositiveIntegerExcludeUselessChar() {
    val positiveInt = someNumbers.filter(x => x > 0)
    assert(positiveInt == List(5, 10))
  }

  // Placeholder syntax
  // To make a function literal even more concise,
  // you can use underscores as placeholders for one or
  // more parameters, so long as each parameter appears only
  // one time within the function literal.
  // ex) _ > 0 is very short notation for a func that checks whether
  // a value is greater than zero:
  @Test
  def testIsElementGreaterThanZeroUsingPlaceHolder() = {
    val positiveInt = someNumbers.filter(_ > 0)
    assert(positiveInt == List(5, 10))
  }

  // Sometimes when you use underscores as placeholders for parameters,
  // the compiler might not have enough info to infer missing parameter types.
  // In such cases, you can specify the types using a colon, like this:
  @Test
  def testGiveEnoughPlaceholderInfo2Compiler(): Unit ={
    val f = (_: Int) + (_: Int)
    assert(f(5, 10) == 15)
  }

  // Partially applied functions
  // Substitute underscores in place of individual parameters,
  // you can replace an entire parameter list with an underscore.
  // Rather than writing println(_), you could write println _.
  // * Exactly the same short form if you had written the following:
  // someNumbers.foreach(x => println(x))
  @Test
  def testReplaceEntireParamterList(): Unit = {
    someNumbers.foreach(println _)
  }

  @Test
  def testPartiallyAppliedFunc(): Unit = {
    def sum(a: Int, b: Int, c: Int) = a + b + c
    assert(sum(1, 2, 3) == 6)
    val a = sum _
    assert(a(1,2,3) == 6)
    // a(1,2,3) is a short form for:
    assert(a.apply(1,2,3) == 6)

    val b = sum(1, _: Int, 3)
    assert(b(2) == 6)
  }

  // Closure
  // The func value (the Object) that's created at runtime
  // from this function literal is called a closure.
  // The name arises from the act of "closing" the function literal by
  // "capturing" the bindings of its free variables.
  @Test
  def testClosure(): Unit = {
    var more = 1

    // A function literal with no free variables, such as (x: Int) +> x + 1,
    // is called a closed term, where a term is a bit of source code.
    val addMore = (x: Int) => x + more
    assert(addMore(10) == 11)

    // If more changes after the closure is created.
    // In Scala, the answer is that the closure sees the change.
    // Intuitively, Scala's closures capture variables themselves,
    // not the value to which variables refer.
    more = 9999
    assert(addMore(10) == 10009)
  }

  // A roundabout way to sum the numbers in a List.
  // Variable sum is in a surrounding scope from the function literal
  // sum += _, which adds numbers to sum.
  // Even though it is the closure modifying sum at runtime,
  // the resulting total, -11, is still visible outside the closure.
  @Test
  def testRoundaboutWayToSum(): Unit = {
    var sum = 0
    someNumbers.foreach(sum += _)
    assert(sum == -11)
  }

  // What if a closure accesses some variable that has
  // several different copies as the program runs?
  // What if a closure uses a local variable of some func,
  // and the func is invoked many times?
  // Which instance of that variable gets used at each access?
  // Only one answer is consistent with the rest of the
  // language: the instance used is the one that was active at the time
  // the closure was created.
  // ex) func that creates and returns "increase" closure:
  @Test
  def testClosureWellReturned(): Unit = {
    // A func that creates and returns "increase" closures.
    def makeIncreaser(more: Int) = (x: Int) => x + more

    // Each time this func is called it will create a new closure.
    // Each closure will access the more variable that was active
    // when the closure was created.
    val inc1 = makeIncreaser(1)
    val inc9999 = makeIncreaser(9999)
    // When you call makeIncreaser(1), a closure is created and returned
    // that captures the value 1 as teh binding for more.
    // When you call makeIncreaser(9999) a closure that captures
    // the value 9999 for more is returned.
    assert(inc1(10) == 11)
    assert(inc9999(10) == 10009)

    // * The Scala compiler rearranges things in case like these
    // so that the captured parameter lives out on the heap,
    // instead of the stack, and thus can outlive the method
    // call that created it.
    // This rearrangement is all taken care of automatically.
    // Capture any variable you like: val, var, or parameter.
  }

  // Special function call forms

  // Repeated parameters
  // Scala allows you to indicate that
  // the last paramter to a func may be repeated.
  // This allows clients to pass variable length argument lists to the fuc.

  // The type of the repeated parameter is
  // an Array of the declared type of the parameter.
  // Thus, the type of args inside the echo func, which is declared
  // as type "String*" is actually Array[String].

  // IMPORTANT ! !
  // * Nevertheless, if you have an array of the appropriate type,
  // and you attempt to pass it as a repeated parameter,
  // you will get a compiler error!.
  @Test
  def testRepeatedParameters(): Unit = {
    def echo(args: String*) = for(arg <- args) println(arg)
    echo("one param")
    echo("Hello", "World!", "Welcome", "Jeff Kim")

    val arr = Array("What's", "up", "doc??")
    // echo(arr) compiler error!
    // Solution:
    // This notation tells the compiler
    // to pass each element of arr as its own argument
    // to echo, rather than all of it as a single argument.
    echo(arr: _*)
  }

  // Named arguments
  // In a normal func call, the arguments in the call are
  // matched one by one in the order of the parameters of the called func.

  // Named arguments allow you to pass arguments to a func in a different order.
  // The syntax is simply that each argument is preceded by a parameter
  // name and an equals sign.

  // speed(distance = 100, time = 10) cna be reversed without changing the meaning.
  @Test
  def testNamedArguments(): Unit = {
    def speed(distance: Float, time: Float): Float = distance / time
    assert(speed(100, 10) == 10.0)
    assert(speed(distance = 100, time = 10) == 10.0)
    assert(speed(time = 10, distance = 100) == 10.0)

    // It is also possible to mix positional and named arguments.
    // In that case, the positional arguments come first.
    // Named arguments are most frequently used in combination with default param value.
  }

  // Default parameter value
  // Scala lets you specify default value for func parameters.
  @Test
  def testDefaultParameter(): Unit = {
    def printTime(out: java.io.PrintStream = Console.out) = out.println("time =" + System.currentTimeMillis())
    printTime()
  }

  // Tail recursion
  // To transform a while loop that updates varS into a more functional style
  // that uses only valS, you may sometimes need to use recursion.

  // A recursive func that approximates a value by repeatedly
  // improving a guess until it is good enough.
  @Test
  def testRecursiveGuessing(): Unit = {
    // IMPORTANT ! ! Recursive method needs Result Type.
    // def approximate(guess: Double): Double = if (isGoodEnough(guess)) guess else approximate(improve(guess))

    // A func like this is often used in search problems, with
    // appropriate implementations for isGoodEnough and improve.

    /*def approximateLoop(initialGuess: Double) = {
      var guess = initialGuess
      while (!isGoodEnough(guess))
        guess = improve(guess)
      guess
    }*/

    // Which of the two versions of approximate is preferable?
    // In terms of brevity and var avoidance, the first functional one wins.
    // But is the imperative approach perhaps more efficient?
    // In fact, if we measure execution times, it turns out that they are almost exactly the same!
    // This might seem surprising because a recursive call looks much more "expansive" than a simple jump
    // fro the end of a loop to its beginning.

    // Func: approximate : The Scala compiler is able to apply an important optimization.
    // Note that the recursive call is the last thing that happens
    // in the evaluation of function approximate's body.
    // approximate(guess: Double), which call themselves as their last action, are called tail recursive.
    // The Scala compiler detects tail recursion and replaces it with a jump back to the beginning of the function,
    // after updating the function parameters with the new values.

    // IMPORTANT ! !
    // The moral is that you should not shy away from
    // using recursive algorithms to solve your problem.
    // Often, a recursive solution is more elegant and concise than a loop-based one.
    // If the solution is tail recursive, there won't be any runtime overhead to paid.
  }

  // Tracing tail-recursive functions
  // A tail-recursive function will not build a new stack frame for each call;
  // all calls will execute in a single frame.
  @Test
  def testWrongTailRecursiveCall(): Unit = {
    // IMPORTANT ! ! Recursive method needs Result Type.
    // This func is not tail recursive,
    // because it performs an increment operation after the recursive call.
    def boom(x: Int): Int = {
      if(x == 0) throw new Exception("Boom!!")
      else boom(x - 1) + 1
    }
    boom(3)
  }
}
