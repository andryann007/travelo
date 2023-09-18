package com.example.travelo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.crashlytics.FirebaseCrashlytics

class ProfileActivity : AppCompatActivity() {
    private lateinit var toolbar : Toolbar
    private lateinit var btnEmail : ImageView
    private lateinit var btnWhatsapp : ImageView
    private lateinit var btnInstagram : ImageView
    private lateinit var btnLinkedIn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        setContentView(R.layout.activity_profile)

        setToolbar()
        myEmail()
        myWhatsapp()
        myInstagram()
        myLinkedIn()
    }

    private fun setToolbar(){
        toolbar = findViewById(R.id.profileToolbar)
        setSupportActionBar(toolbar)
        toolbar.subtitle = "Andryan"

        toolbar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            finish()
        }
    }

    private fun myEmail(){
        btnEmail = findViewById(R.id.btnEmail)
        btnEmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("andryancoolz@gmail.com"))
            intent.`package` = "com.google.android.gm"

            try{
                startActivity(intent)
            } catch (e: ActivityNotFoundException){
                val newIntent = Intent(Intent.ACTION_SEND)
                newIntent.type = "text/plain"
                newIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("andryancoolz@gmail.com"))

                startActivity(Intent(Intent.ACTION_SEND))
            }

            startActivity(intent)
        }
    }

    private fun myWhatsapp() {
        btnWhatsapp = findViewById(R.id.btnWhatsapp)
        btnWhatsapp.setOnClickListener{
            val contact = "+62 8567519148"
            val url = Uri.parse("https://api.whatsapp.com/send?phone=$contact")
            val intent = Intent(Intent.ACTION_VIEW, url)
            intent.setPackage("com.whatsapp")

            try{
                startActivity(intent)
            } catch (e: ActivityNotFoundException){
                val newIntent = Intent(Intent.ACTION_VIEW, url)

                startActivity(newIntent)
            }
        }
    }

    private fun myInstagram(){
        btnInstagram = findViewById(R.id.btnInstagram)
        btnInstagram.setOnClickListener{
            val username = "andryan.doank"
            val url = Uri.parse("https://www.instagram.com/$username")
            val intent = Intent(Intent.ACTION_VIEW, url)
            intent.setPackage("com.instagram.android")

            try{
                startActivity(intent)
            } catch (e: ActivityNotFoundException){
                val newIntent = Intent(Intent.ACTION_VIEW, url)

                startActivity(newIntent)
            }
        }
    }

    private fun myLinkedIn(){
        btnLinkedIn = findViewById(R.id.btnLinkedIn)
        btnLinkedIn.setOnClickListener{
            val username = "andryan007"
            val url = Uri.parse("https://id.linkedin.com/in/$username")
            val intent = Intent(Intent.ACTION_VIEW, url)
            intent.setPackage("com.linkedin.android")

            try{
                startActivity(intent)
            } catch (e: ActivityNotFoundException){
                val newIntent = Intent(Intent.ACTION_VIEW, url)

                startActivity(newIntent)
            }
        }
    }
}