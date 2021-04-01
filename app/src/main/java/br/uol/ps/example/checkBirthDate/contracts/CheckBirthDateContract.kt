package br.uol.ps.example.checkBirthDate.contracts

interface CheckBirthDateContract {
    interface View {
        fun showResultBirthDate(result: String)
        fun showError(message: String)
    }

    interface Presenter {
        fun verifyBirthDate(date: String)
    }
}