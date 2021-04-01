package br.uol.ps.example.sumNumber.presenter

import br.uol.ps.example.sumNumber.contracts.NumberContract

class NumberPresenter(
    var view: NumberContract.View?
) : NumberContract.Presenter {

    override fun sum(num1: String, num2: String) {
       val n1 = num1.toDoubleOrNull()
       val n2 = num2.toDoubleOrNull()

       if(n1!=null && n2!=null){
           view?.showResult((n1+n2).toString())
       }else{
           view?.showError("Número informado é invalido")
       }
    }
}