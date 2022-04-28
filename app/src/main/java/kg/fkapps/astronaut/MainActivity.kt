package kg.fkapps.astronaut

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val sendWishes: Button = findViewById(R.id.buttonSendWishes)
        val sendWishes2: Button = findViewById(R.id.buttonSendWishes2)
        val wishesText: EditText = findViewById(R.id.editTextTextWishes)
        sendWishes.visibility = View.VISIBLE
        sendWishes2.visibility = View.GONE
        wishesText.visibility = View.GONE

        sendWishes.setOnClickListener {
            sendWishes.visibility = View.GONE
            sendWishes2.visibility = View.VISIBLE
            wishesText.visibility = View.VISIBLE
        }

        @SuppressLint("IntentReset")
        fun sendEmail() {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Wishes")
            emailIntent.putExtra(Intent.EXTRA_TEXT, wishesText.text.toString())
            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@MainActivity, "There is no email client installed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        sendWishes2.setOnClickListener {
            sendEmail()
        }

    }


}