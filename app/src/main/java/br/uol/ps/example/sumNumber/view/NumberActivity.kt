package br.uol.ps.example.sumNumber.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.uol.ps.cards.R
import br.uol.ps.example.sumNumber.contracts.NumberContract
import br.uol.ps.example.sumNumber.presenter.NumberPresenter

class NumberActivity : AppCompatActivity(), NumberContract.View {

    private val tvResult: TextView = findViewById(R.id.tvResult)
    private val btnSum: Button = findViewById(R.id.btnSum)
    private val edtNumber1: EditText = findViewById(R.id.edtNumber1)
    private val edtNumber2: EditText = findViewById(R.id.edtNumber2)

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
        tvResult.text = result
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}