package br.uol.ps.example.checkBirthDate.presenter

import br.uol.ps.example.checkBirthDate.contracts.CheckBirthDateContract
import java.time.LocalDate
import java.time.Period

class CheckBirthDatePresenter(
    var view: CheckBirthDateContract.View?
): CheckBirthDateContract.Presenter {
    override fun verifyBirthDate(date: String) {
        try{
            val part = date.split("/")
            var convertISO: LocalDate = LocalDate.of(part[2].toInt(),part[1].toInt(),part[0].toInt())
            val period = Period.between(convertISO,LocalDate.now())
            val periodStr = period.toString().split("Y")
            val age = periodStr[0].substring(1,periodStr[0].length)
            val result = if (age.toInt() >= 18) "Maior" else "Menor"

            view?.showResultBirthDate("Idade: $age - $result")
        }catch(e: Exception){
            view?.showError("Erro ao validar a data de nascimento: $e.message")
        }
    }
}