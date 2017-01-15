package tutorial.BuiltIn

import java.io.{BufferedReader, InputStreamReader}

import scala.util.control.Breaks.{break, breakable}

/**
  * Created by JeffMinsungKim on 13/01/2017.
  */
object BuiltIn {

  def runBreakable(): Unit ={
    val in = new BufferedReader(new InputStreamReader(System.in))
    breakable {
      while(true){
        println("? ")
        if (in.readLine() == "") break()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    runBreakable()
  }
}
