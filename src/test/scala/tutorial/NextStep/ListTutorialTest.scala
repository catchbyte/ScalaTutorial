package tutorial.NextStep

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

/**
  * Created by JeffMinsungKim on 10/01/2017.
  */
class ListTutorialTest extends JUnit3Suite {

  // You don't need to say new List because "List.apply()" is
  // defined as a factory method on the scala.List companion object.
  val oneTwo = List(1, 2)
  val twoThree = List(2, 3)
  val threeFour = List(3, 4)

  // Nil at the end is that :: is defined on class List.
  // If you try to just say 1 :: 2 :: 3, it won't compile because 3 is an Int, which doesn't have a :: method.
  val thrill = "Will" :: "fill" :: "until" :: Nil

  /*
  * Why not append to lists?
  * :+ is an operation "append" (rarely used.)
  * The time it takes to append to a list grows linearly with the size of the list.
  * Whereas prepending with :: takes constant time.
  * To build a list efficiently by appending elements -> prepend them and when you're done call reverse.
  * A ListBuffer, a mutable list -> append operation, and when you're done call toList.
  * */

  @Test
  def testMutateList(): Unit = {
    val oneTwo = this.oneTwo
    val threeFour = this.twoThree
    // List has a method named ':::' for list concatenation.
    val oneTwoThreeFour = this.oneTwo ::: this.threeFour
    println(this.oneTwo + "and" + this.threeFour + "were not mutated.")
    println("Thus," + oneTwoThreeFour + "is a new list.")

    // '::' which is pronounced "cons." '::' is a mehotd.
    // Cons prepends a new element to the beginning of an existing list and returns the resulting list.
    // The :: method is invoked on twoThree, passing in 1, like this:
    // twoThree.::(1)
    val oneTwoThree = 1 :: this.twoThree
    // val oneTwoThree = listTuto.twoThree.::(1)
    println(oneTwoThree)
  }

  @Test
  def testUsagesofListMethod(): Unit = {
    // Nil references an object directly, whereas List() is a method call.
    assert(Nil == List())
    // "eq" - they're the same object
    //System.identityHashCode(Nil)
    //System.identityHashCode(List())
    assert(Nil eq List())
    assert(Nil equals List())
  }

  @Test
  def testCreatesNewList(): Unit = {
    println(thrill)
    println(thrill(2)) // Returns the element at (zero based) of the thrill list.
    val aList = List("a","b") ::: List("c", "d")
    println(aList)
  }

  // Counts the number of string elements in thrill that have length 4
  // Returns the number of matched elements.
  @Test
  def testCountListElements(): Unit = {
    val result = thrill.count(s => s.length == 4)
    println(result)
  }

  // Returns the thrill list without its first n elements.
  @Test
  def testDropFirstNElements(): Unit = {
    def dropElements(n: Int) = {
      val result = thrill.drop(n)
      result
    }
    val remainingElement = dropElements(2)
    println(remainingElement)
  }

  // Returns the thrill list without its rightmost n elements.
  @Test
  def testDropRightMostNElements(): Unit = {
    def dropRightMostElements(n: Int) = {
      val result = thrill.dropRight(n)
      result
    }
    val remainingElement = dropRightMostElements(2)
    println(remainingElement)
  }

  // Determines whether a string element exists in thrill that has the value "until"
  // Returns true
  @Test
  def testIsElementExists(): Unit = {
    val validWord = "until"
    val invalidWord = "apple"
    def isElementExists(word: String) = thrill.exists(s => s == word)
    assert(isElementExists(validWord))
    // assert(isElementExists(invalidWord))
  }

  // Returns a list of all elements, in order, of the thrill list that have length 4
  @Test
  def testFilterElement(): Unit = {
    def filterElementByLength(len: Int) = thrill.filter(s => s.length == len)
    val remainingElement = filterElementByLength(4)
    assert(remainingElement == List("Will", "fill"))
  }

  // Indicates whether all elements in the thrill list end with the letter "l"
  @Test
  def testCheckAllElementsInParticularLetter(): Unit = {
    def verifyAllElementsEndWithCertainLetter(letter: String) = thrill.forall(s => s.endsWith(letter))
    val letter = "l"
    val result = verifyAllElementsEndWithCertainLetter(letter)
    assert(result)
  }

  // Executes the print statement on each of the strings in the thrill list.
  @Test
  def testPrintAllElementsInList(): Unit = {
    def printElements = thrill.foreach(s => print(s))
    def printElementsMoreConcise = thrill.foreach(print)
    printElements
    println()
    printElementsMoreConcise
  }

  // Returns the first element in the thrill list
  @Test
  def testGetFirstElement(): Unit = {
    def getHeadElement = {
      val head = thrill.head
      head
    }
    val h = getHeadElement
    assert(h == "Will")
  }

  // Returns the thrill list minus first element.
  @Test
  def testRemoveFirstElementOfList(): Unit = {
    def removeFirstElementOfList = thrill.tail
    assert(removeFirstElementOfList == List("fill", "until"))
  }


  // Returns the last element in the thrill list
  @Test
  def testGetLastElement(): Unit = {
    def getLastElement = {
      val last = thrill.last
      last
    }
    val l = getLastElement
    assert(l == "until")
  }

  // Returns a list of all but the last element in the thrill list.
  @Test
  def testGetAllElementButLastElement: Unit = {
    def getAllElementButLastElement = {
      val result = thrill.init
      result
    }
    val r = getAllElementButLastElement
    assert(r == List("Will", "fill"))
  }

  // Indicates whether the thrill list is empty.
  @Test
  def testIndicatesListIsEmptyOrNot: Unit = {
    def isListEmptyOrNot = {
      val result = thrill.isEmpty
      result
    }
    val r = isListEmptyOrNot
    assert(r == false)
  }

  // Returns the number of elements in the thrill list.
  @Test
  def testSizeOfList: Unit = {
    def sizeOfList = {
      val size = thrill.length
      size
    }
    assert(sizeOfList == 3)
  }

  // Returns a list resulting from adding a "y" to each string element in the thrill list.
  @Test
  def testUsageOfMap: Unit = {
    def addString2Element = {
      val li = thrill.map(s => s + "y")
      li
    }
    assert(addString2Element == List("Willy", "filly", "untily"))
  }

  // Makes a string with the element of the list.
  @Test
  def testMakesStringElement: Unit = {
    def convertElementsIntoString = {
      val str = thrill.mkString(", ")
      str
    }
    assert(convertElementsIntoString == "Will, fill, until")
  }

  // Returns a list of all elements, order, of the thrill list except those that have length 4
  @Test
  def testFilterElement2: Unit = {
    def filterElementExceptCertainElement = thrill.filterNot(s => s.length == 4)
    assert(filterElementExceptCertainElement == List("until"))
  }

  // Returns a list containing all elements of the thrill list in reverse order.
  @Test
  def testElementReversed: Unit = {
    def replaceElementInversiveOrder = thrill.reverse
    assert(replaceElementInversiveOrder == List("until", "fill", "Will"))
  }
}
