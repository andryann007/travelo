package com.example.travelo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.travelo.model.ModelTravel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var toolbar : Toolbar
    private lateinit var travelDetailImg : ImageView
    private lateinit var travelDetailName : TextView
    private lateinit var travelDetailLocation : TextView
    private lateinit var travelDetailDescription : TextView
    private lateinit var travelDetailOpenTime : TextView
    private lateinit var travelDetailPrice : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        setContentView(R.layout.activity_detail)

        setToolbar()
        getIntentData()
    }

    private fun setToolbar(){
        val data = intent.getParcelableExtra<ModelTravel>("DATA")
        val name = data?.name.toString()

        toolbar = findViewById(R.id.detailToolbar)
        setSupportActionBar(toolbar)
        toolbar.subtitle = name

        toolbar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getIntentData(){
        val data = intent.getParcelableExtra<ModelTravel>("DATA")

        val image: String = data?.image.toString()
        travelDetailImg = findViewById(R.id.travelDetailImg)
        Picasso.get().load(image).noFade().into(travelDetailImg)

        val name: String = data?.name.toString()
        travelDetailName = findViewById(R.id.travelDetailName)
        travelDetailName.text = "Name : $name"

        val location: String = data?.location.toString()
        travelDetailLocation = findViewById(R.id.travelDetailLocation)
        travelDetailLocation.text = "Location : $location"

        val description: String = data?.description.toString()
        travelDetailDescription = findViewById(R.id.travelDetailDesc)
        travelDetailDescription.text = "Description : $description"

        val openTime: String = data?.open_time.toString()
        travelDetailOpenTime = findViewById(R.id.travelDetailOpenTime)
        travelDetailOpenTime.text = "Open Time : $openTime"

        val price : String = data?.price.toString()
        travelDetailPrice = findViewById(R.id.travelDetailPrice)
        travelDetailPrice.text = "Price : $price"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"

                val chooser = Intent.createChooser(intent, "Share with")
                chooser.putExtra(Intent.EXTRA_TITLE, "Share this travel destination with")

                startActivity(chooser)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}