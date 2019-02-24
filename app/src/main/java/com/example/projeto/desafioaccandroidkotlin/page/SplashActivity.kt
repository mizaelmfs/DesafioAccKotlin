package com.example.projeto.desafioaccandroidkotlin.page

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.projeto.desafioaccandroidkotlin.R
import com.example.projeto.desafioaccandroidkotlin.Utils.NetworkChange

class SplashActivity : AppCompatActivity() {

    private lateinit var connectionDetector: NetworkChange

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags (
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        supportActionBar?.hide()

        connectionDetector =
            NetworkChange(context = baseContext)

        goToMainActivity()
    }

    private fun goToMainActivity() {
            if (connectionDetector.hasInternetConnection()) {
                Handler().postDelayed({
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }, 2000)
            } else {
                val builder = AlertDialog.Builder(this)
                    .setTitle(R.string.title_dialog_internet_error)
                    .setPositiveButton(R.string.body_dialog_internet_error_retry) {
                            dialogInterface, _ ->
                        run {
                            dialogInterface.dismiss()
                            goToMainActivity()
                        }
                    }
                    .setNegativeButton(R.string.finish) {
                            _, _ -> this.finish()
                    }
                    .setCancelable(false)
                    .create()
                builder.show()
            }
    }

}
