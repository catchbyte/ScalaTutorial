package tutorial.NextStep

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

import scala.collection.immutable.HashSet
import scala.collection.mutable

/**
  * Created by JeffMinsungKim on 11/01/2017.
  */
class CollectionTutorialTest extends JUnit3Suite{

  /*
  * To add a new element to a set, you call + on the set, passing in the new element.
  * On both mutable and immutable sets, the + method will create and return a new set with the element added.
  * Although mutable sets offer an actual += method, immutable sets do not.
  * Scala provides two subtraits, one for mutable sets and another for immutable sets.
  * */
  // An immutable set. The default way to create a set.
  @Test
  def testCreateBasicSet: Unit = {
    // The Scala compiler infers jetSet's type to be the immutable Set[String].
    var jetSet = Set("Boeing", "Airbus")
    jetSet += "Lear"
    println(jetSet)
    println(jetSet.contains("Cessna"))
  }

  // A mutable set.
  @Test
  def testCreateMutableSet: Unit = {
    val movieSet = mutable.Set("Hitch", "Poltergeist")
    // movieSet += "Shrek"
    movieSet.+=("Shrek")
    println(movieSet)
  }

  // An immutable HashSet.
  @Test
  def testImmutableHashSet(): Unit = {
    val hashSet = HashSet("Tomatoes", "Chilies")
    println(hashSet + "Coriander")
  }

  // An mutable HashSet.
  @Test
  def testMutableHashSet(): Unit = {
    val hashSet = HashSet("Tomatoes", "Chilies")
    println(hashSet + "Coriander")
  }

  /*
  * Scala provides mutable and immutable version of Map.
  * No import is necessary, as immutable is the default map.
  *
  * */

  // Can create and initialize maps using factory methods.
  @Test
  def testInitializeMaps(): Unit = {
    // Initizalie it with an empty mutable Map that has integer keys and string values.
    // The map is empty because you pass nothing to the factory method.
    // The parentheses in "Map[Int, String]()" are empty.
    // Add key/value pairs to the map using the -> and += methods.
    // The scala compiler transforms a binary operation expression like 1 -> "Go to island."
    // into (1).->("Go to island.")
    // -> (actually calling a method named -> on an integer with the value 1,
    // passing in a string with the value "Go to island".
    val treasureMap = mutable.Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big x on ground.")
    treasureMap += (3 -> "Dig")
    println(treasureMap(2))
  }





}
