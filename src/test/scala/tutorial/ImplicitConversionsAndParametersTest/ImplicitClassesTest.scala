package tutorial.ImplicitConversionsAndParametersTest

import org.junit.Test
import org.scalatest.junit.JUnit3Suite

class ImplicitClassesTest extends JUnit3Suite {

  @Test
  def testImplicitClass(){
    case class Rectangle(width: Int, height: Int)

    implicit class RectangleMaker(width: Int){
      def x(height: Int) = Rectangle(width, height)
    }
    val myRectangle = 3 x 4

    assert(myRectangle == Rectangle(3, 4))
  }


}
