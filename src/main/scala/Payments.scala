object Payments {

  def main(args: Array[String]): Unit = {

    println("input agreement date in format dd-mm-yyyy: ")
    val agreementDate = scala.io.StdIn.readLine()
    println("input calculation date in format dd-mm-yyyy: ")
    val calculationDate = scala.io.StdIn.readLine()
    println("input initial investment: ")
    val initInv = scala.io.StdIn.readLine().toInt
    println("input interest rate in %: ")
    val intRate = scala.io.StdIn.readLine().toInt
    println("input investment duration in years): ")
    val invDur = scala.io.StdIn.readLine().toInt

    println(CalculatePayments(agreementDate, calculationDate, initInv, intRate, invDur))

  }

}
