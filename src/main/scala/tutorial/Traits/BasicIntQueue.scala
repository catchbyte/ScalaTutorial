package tutorial.Traits

import scala.collection.mutable.ArrayBuffer


class BasicIntQueue extends IntQueue {
  // A private field holding an array buffer.
  private val buf = new ArrayBuffer[Int]()
  // Removes an entry from one end of the buffer.
  override def get(): Int = buf.remove(0)
  // Adds elements to the other end.
  override def put(x: Int): Unit = {buf += x}

}
