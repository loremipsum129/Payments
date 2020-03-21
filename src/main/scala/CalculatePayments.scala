import scala.annotation.tailrec

object CalculatePayments {

  def apply(agrDate: String, calcDate: String, initInvst: Int, intRateInPerc: Int, invDuration: Int): Double = {

    val intRate: Double = (intRateInPerc.toDouble / 100) + 1.0

    @tailrec
    def CP_paymentPerMonthMap(remSum: Double, remYears: Int, map: List[Double]): List[Double] = {
      if (remYears == 0) map
      else {
        val thisYearsPayment = remSum / remYears
        CP_paymentPerMonthMap((remSum - thisYearsPayment) * intRate , remYears - 1, thisYearsPayment/12 :: map)
      }
    }

    def CP_amOfPayments(agreementDate: String, calculationDate: String, invDuration: Int): Int = {

      val(calcDay, calcMonth, calcYear) = calculationDate.split("-").map(_.toInt) match {case Array(x1, x2 ,x3) => (x1, x2, x3)}
      val(lastDay, lastMonth, lastYear) = agreementDate.split("-").map(_.toInt) match {case Array(x1, x2 ,x3) => (x1, x2, x3 + invDuration)}

      val firstMonthPayment: Int = if (calcDay != 1 | agreementDate == calculationDate) 0 else 1

      firstMonthPayment + (12 - calcMonth) + lastMonth + ((lastYear - calcYear - 1) * 12)
    }

    val payments = CP_paymentPerMonthMap(initInvst, invDuration, List())
    val amOfPayments = CP_amOfPayments(agrDate, calcDate, invDuration)

    payments.flatMap(List.fill(12)(_)).take(amOfPayments).sum

  }

}
