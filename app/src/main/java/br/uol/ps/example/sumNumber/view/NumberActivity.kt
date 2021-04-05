package br.uol.ps.example.sumNumber.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.uol.ps.cards.R
import br.uol.ps.example.sumNumber.contracts.NumberContract
import br.uol.ps.example.sumNumber.presenter.NumberPresenter
import kotlinx.android.synthetic.main.activity_number.*

class NumberActivity : AppCompatActivity(), NumberContract.View {

    private lateinit var presenter: NumberContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)
        presenter = NumberPresenter(this)

        btnSum.setOnClickListener {
            presenter.sum(
                edtNumber1.text.toString(),
                edtNumber2.text.toString()
            )
        }
    }

    override fun showResult(result: String) {
        tvResult.run {
              visibility = View.VISIBLE
             text = result
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}