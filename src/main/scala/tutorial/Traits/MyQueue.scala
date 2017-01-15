package tutorial.Traits

class MyQueue extends BasicIntQueue with Doubling {

  /*
  * To trait or not to trait?
  *
  * If the behavior will not be reused, then make it a concrete class.
  * It is not reusable behavior after all.
  *
  * If it might be reused in multiple, unrelated classes, make it a trait.
  * Only traits can be mixed into different parts of the class hierarchy.
  *
  * If you want to inherit from it in Java code, use an abstract class.
  * Since traits with code do not have a close Java analog, it tends to be
  * awkward to inherit from a trait in a Java class.
  * Inheriting from a Scala class, meanwhile, is exactly like
  * inheriting from a Java class.
  *
  * As one exception, a Scala trait with only abstract members translates
  * directly to a Java interface, so you should feel free to define such traits
  * even if you expect Java code to inherit from it.
  * */


}

object MyQueue{
  def main(args: Array[String]): Unit ={
    val queue = new MyQueue
    queue.put(10)
    println(queue.get())
  }
}
