package tutorial.WorkingwithListsTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


class ListLiteralTest extends JUnit3Suite {

  val abcde = List('a', 'b', 'c', 'd', 'e')

  // List literals
  // List[Nothing] is a subtype of List[T] for any type T.
  @Test
  def testBasicList(): Unit = {
    val fruit = List("apple", "orange", "banana")
    val nums = List(1, 2, 3, 4)
    val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
    val empty: List[Nothing] = List()

    def isort(xs: List[Int]): List[Int] = {
      if(xs.isEmpty) Nil
      else insert(xs.head, isort(xs.tail))
    }

    def insert(x: Int, xs: List[Int]): List[Int] = {
      if(xs.isEmpty || x <= xs.head) x :: xs
      else xs.head :: insert(x, xs.tail)
    }

    println(isort(nums))
  }

  // Constructing lists
  // Nil - Represents the empty list.
  // :: - The infix operator, ::, expresses list extension at the front.
  // head - Returns the first element of a list
  // tail - Returns a list consisting of all elements except the first.
  // isEmpty - Returns true if the list is empty.
  // ::: - ::: takes two lists as operands. The result of xs ::: ys is a new list
  // that contains all the elements of xs.

  // Concatenating two lists
  @Test
  def testCreateNewList(): Unit = {
    val xs = List(1,2)
    val ys = List(3,4,5)
    val result = xs ::: ys
    assert(result == List(1,2,3,4,5))
  }

  // Accessing the end of a list: init and last
  // Unlike head and tail, which both run in constant time, init and last
  // need to traverse the whole list to compute their result.
  // As a result, they take time proportional to the length of the list.
  @Test
  def testEndOfList(): Unit = {
    assert(abcde.last == 'e')
    assert(abcde.init == List('a','b','c','d'))
  }

  // Reversing lists: reverse
  // Like all other list operations, reverse creates a new list rather than
  // changing the one it operates on.
  // The original value of abcde is unchanged after the reverse operation.
  // reverse turns init to tail and last to head, except that the elements are reversed.
  @Test
  def testReverseList(): Unit = {
    assert(abcde.reverse == List('e', 'd', 'c', 'b', 'a'))
    assert(abcde == List('a', 'b', 'c', 'd', 'e') )
  }

  // Prefixes and suffixes: drop, take, and splitAt
  @Test
  def testPrefixSuffix(): Unit = {
    def takeN(n: Int) = abcde take n
    assert(takeN(2) == List('a', 'b'))


    def dropN(n: Int) = abcde drop n
    assert(dropN(2) == List('c', 'd', 'e'))

    def splitAtN(n: Int) = abcde splitAt n
    assert(splitAtN(2) == (List('a', 'b'), List('c', 'd', 'e')))
  }

  // Element selection: apply and indices
  // It is a less common operation for lists than it is for arrays.
  @Test
  def testElementSelection(): Unit = {
    val aply = abcde apply 2 // rare in Scala
    assert(aply == 'c')
    assert(abcde(2) == 'c') // rare in Scala

    // One reason why random element selection is less popular for lists
    // than for arrays is that xs(n) takes time proportional to the index n.
    // In fact, apply is simply defined by a combination of drop and head:
    // xs apply n equals (xs drop n).head

    // The indices method returns a list consisting of all valid indices of a given list:
    println(abcde.indices)
  }

  // Flattening a list of lists: flatten
  // The flatten method takes a list of lists and flattens it out to a single list:
  @Test
  def testFlattening(): Unit = {
    val aList = List(List(1,2), List(3), List(), List(4,5)).flatten
    val fruit = List("apple", "orange", "banana")
    println(aList)
    val aList2 = fruit.map(_.toCharArray).flatten
    println(aList2)
    // It can only be applied to lists whose elements are all lists.
  }

  // Zipping lists: zip and unzip
  // The zip operation takes two lists and forms a list of pairs:
  @Test
  def testZipping(): Unit = {
    val z = abcde.indices zip abcde
    val switchZ = abcde zip abcde.indices
    val zWithIndex = abcde.zipWithIndex
    val zipped = abcde zip List(1,2,3)
    println(z)
    println(switchZ)
    assert(switchZ == zWithIndex)
    assert(zipped.unzip == (List('a','b','c'), List(1,2,3)))
  }

  // Displaying lists: toString and mkString
  // The toString operation returns the canonical string representation of a list:
  @Test
  def testString(): Unit = {
    assert(abcde.toString == "List(a, b, c, d, e)")

    // The mkString method has two overloaded variants that let you drop
    // some of all of its arguments.
    // The first variant only takes a separator string:
    val firstVariant = abcde mkString ("[", ",","]")
    assert(firstVariant == "[a,b,c,d,e]")

    // The second variant lets you omit all arguments:
    val secondVariant = abcde mkString ""
    assert(secondVariant == "abcde")

    assert(abcde.mkString == secondVariant)
    val anotherWay = abcde mkString ("List(",",",")")
    assert(anotherWay == "List(a,b,c,d,e)")
  }

  // Converting lists: iterator, toArray, copyToArray
  // To convert data between the flat world of arrays and the recursive
  // world of lists, you can use method toArray in class List
  // and toList in class Array:
  @Test
  def testConvertingList(): Unit = {
    val arr = abcde.toArray
    assert(arr sameElements Array('a','b','c','d','e'))
    val aList = arr.toList
    assert(aList sameElements List('a','b','c','d','e'))

    // There's also a method copyToArray, which copies list elements to
    // successive array positions within some destination array.
    // xs copyToArray (arr, start) - copies all elements of the list xs to the array arr,
    // beginning with position start.
    // You must ensure that the destination array arr is large enough
    // to hold the list in full.

    val arr2 = new Array[Int](10)
    assert(arr2 sameElements Array(0,0,0,0,0,0,0,0,0,0))
    List(1,2,3) copyToArray(arr2, 3)
    assert(arr2 sameElements Array(0,0,0,1,2,3,0,0,0,0))

    val it = abcde.iterator
    for(e <- 0 to abcde.length - 1)
      assert(abcde(e) == it.next)

  }

  // Both accesing and updating an array element:
  // Scala arrays are represented in the same way as Java arrays.
  @Test
  def testUpdateArrayElement(): Unit = {
    val fiveIntsBucket = new Array[Int](5)
    val fiveToOne = Array(5,4,3,2,1)
    fiveIntsBucket(0) = fiveToOne(4)
    assert(fiveIntsBucket sameElements Array(1,0,0,0,0))
  }

  // List buffers
  // Avoid the reverse operation, is to use a ListBuffer.
  // A ListBuffer is a mutable object, which can help you build lists
  // more efficiently when you need to append.
  // ListBuffer provides constant time append and prepend operations.
  // Append elements with the += operator,
  // and prepend with the +=: operator.
  // Obtain a List by invoking toList on the ListBuffer.
  @Test
  def testListBuffer(): Unit = {
    val buf = new ListBuffer[Int]
    buf += 1
    buf += 2
    assert(buf == ListBuffer(1, 2))

    3 +=: buf // ListBuffer(3, 1, 2)
    assert(buf.toList == List(3, 1, 2)) // List(3, 1, 2)

    // Another reason to use ListBuffer instead of list is
    // to prevent the potential for stack overflow.
  }

  // Array buffers
  // An ArrayBuffer is like an array, except that you can additionally
  // add and remove elements from the beginning and end of the sequence.
  // All Array operations are available, though they are a little slower
  // due to a layer of wrapping in the implementation.
  // The new addition and removal operations are constant time on average,
  // but occasionally require linear time due to the implementation needing
  // to allocate a new array to hold the buffer's contents.
  // The ArrayBuffer will adjust the allocated space automatically as needed.
  @Test
  def testArrayBuffer(): Unit = {
    val buf = new ArrayBuffer[Int]()

    // Append to an ArrayBuffer using the += method:
    buf += 12
    buf += 15
    assert(buf == ArrayBuffer(12, 15))
    assert(buf.length == 2)
    assert(buf(0) == 12)
  }

  // Strings (via StringOps)
  // One other sequence to be aware of is StringOps, which implements
  // many sequence methods. Because Predef has an implicit conversion
  // from String to StringOps, you can treat
  // any string like a sequence.
  @Test
  def testUpperCase(): Unit = {
    def hasUpperCase(s: String) = s.exists(_.isUpper)
    assert(hasUpperCase("Robert Frost"))
    assert(!hasUpperCase("e e cuasd"))
  }

  // Sets and maps
  // The Scala collections library offers
  // both mutable and immutable versions of sets and maps.
  // By default when you write "Set" or "Map" you get an immutable object.
  // If you want the mutable variant, need to do an explicit import.
  @Test
  def testSet(): Unit = {
    val text = "See Spot run. Run, Spot. Run!"
    val words = mutable.Set.empty[String]
    val wordArray = text.split("[ !,.]+")
    for (word <- wordArray)
      words  += word.toLowerCase
    assert(words == Set("see", "run", "spot"))

  }

  // Using maps
  // Maps let you associate a value with each element of a set.
  // Using a map looks similar to using an array, except instead of
  // indexing with integers counting from 0, you can use any kind of key.

  // Import the mutable package name, you can create an empty mutable map:
  @Test
  def testMap(): Unit = {
    val text = "See Spot run. Run, Spot. Run!"
    val map = mutable.Map.empty[String, Int]
    map("hello") = 1
    map("there") = 2
    assert(map == Map("hello" -> 1, "there" -> 2))
    assert(map("hello") == 1)

    def countWords(text: String) = {
      val counts = mutable.Map.empty[String, Int]
      for(rawWord <- text.split("[ !,.]+")){
        val word = rawWord.toLowerCase
        val oldCount =
          if(counts.contains(word)) counts(word)
          else 0
        counts += (word -> (oldCount + 1))
      }
      counts
    }
    assert(countWords("See Spot run! Run, Spot. Run!") == Map("spot" -> 2, "see" -> 1, "run" -> 3))
  }

  // Tuples
  // A common application of tuples is
  // returning multiple values from a method.
  @Test
  def testTuple(): Unit ={
    def longestWord(words: Array[String]) = {
      var word = words(0)
      var idx = 0
      for(i <- 1 until words.length)
        if(words(i).length > word.length){
          word = words(i)
          idx = i
        }
      (word, idx)
    }
    val longest = longestWord("The quick brown fox".split(" "))
    assert(longest == ("quick", 1))

    // To access elements of a tuple, you can use method _1 to access
    // the first element, _2 to access teh second, and so on:
    assert(longest._1 == "quick")
    assert(longest._2 == 1)


    // Additionally, you can assign each element of
    // the tuple to its own variable.

    val (w, i) = longest
    assert(w == "quick")
    assert(i == 1)

    // If you leave off the parentheses you get a different result:
    val wo, id = longest
    assert(wo == ("quick",1))
    assert(id == ("quick",1))
  }
}
