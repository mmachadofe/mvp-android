package br.uol.ps.example.sumNumber.contracts

interface NumberContract {
    interface View {
        fun showResult(result: String)
        fun showError(message: String)
    }

    interface Presenter {
        fun sum(num1: String, num2: String)
    }
}