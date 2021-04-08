package br.uol.ps.cards.contracts

import br.uol.ps.cards.models.Card

interface DetailContract {
    interface View {
        fun setLimit(limit: String)
        fun setBlockStatus(status: Boolean)
        fun showError()
        fun showLoading()
        fun stopLoading()
    }

    interface Presenter {
        fun getDetailCard(id: Int)
        fun dispose()
    }
}