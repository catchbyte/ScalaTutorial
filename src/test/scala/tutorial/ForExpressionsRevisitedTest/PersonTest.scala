package tutorial.ForExpressionsRevisitedTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite
import tutorial.ForExpressionsRevisited.Person


class PersonTest extends JUnit3Suite {

  val per = new Person

  @Test
  def testPersonFilter(): Unit ={
    val result = per.persons filter(p => !p.isMale) flatMap (p => (p.children map (c => (p.name, c.name))))
    assert(result == List(("Julie","Lara"), ("Julie", "Bob")))
  }

  @Test
  def testPersonWithFilter(): Unit ={
    val result = per.persons withFilter(p => !p.isMale) flatMap (p => p.children map (c => (p.name, c.name)))
    assert(result == List(("Julie","Lara"), ("Julie", "Bob")))
  }

  @Test
  def testPersonSimpleWay(): Unit ={
    for(p <- per.persons; if !p.isMale; c <- p.children)
      yield (p.name, c.name, println(p.name,c.name))
  }
}
