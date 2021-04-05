package br.uol.ps.example.checkBirthDate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.uol.ps.cards.R
import br.uol.ps.example.checkBirthDate.contracts.CheckBirthDateContract
import br.uol.ps.example.checkBirthDate.presenter.CheckBirthDatePresenter

class CheckBirthDateActivity : AppCompatActivity(), CheckBirthDateContract.View {
    private lateinit var presenter: CheckBirthDateContract.Presenter

    var birthDate: EditText? = null
    var btnVerify: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_birth_date)
        presenter = CheckBirthDatePresenter(this)
        birthDate = findViewById(R.id.birthDate)
        btnVerify = findViewById(R.id.btnVerify)

        btnVerify?.setOnClickListener {
            presenter.verifyBirthDate(birthDate?.text.toString())
        }
    }

    override fun showResultBirthDate(result: String) {
        var tvResp: TextView = findViewById(R.id.tvResp)
        tvResp.text = result
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}