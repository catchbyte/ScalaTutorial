package tutorial.Traits

trait Doubling extends IntQueue {
  // A trait that doubles integers as they are put in the queue.
  // It declares a superclass, IntQueue.
  // This declaration means that
  // the trait can only be mixed into a class that also extends IntQueue.
  // Thus, you can mix Doubling into BasicIntQueue.
  abstract override def put(x: Int) = {super.put(2 * x)}

}
