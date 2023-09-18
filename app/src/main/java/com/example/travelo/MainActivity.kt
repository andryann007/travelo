package com.example.travelo

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelo.adapter.AdapterTravel
import com.example.travelo.model.ModelTravel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar : Toolbar

    private lateinit var rvTravel : RecyclerView

    private val travelLists = ArrayList<ModelTravel>()

    private val adapterTravel = AdapterTravel(travelLists)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        setContentView(R.layout.activity_main)

        setToolbar()

        rvTravel = findViewById(R.id.rvTravel)
        rvTravel.setHasFixedSize(true)
        loadTravelList()
    }

    private fun loadTravelList() {
        val database = Firebase.database
        val ref = database.getReference("travels")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (ds in snapshot.children) {
                        val model: ModelTravel? = ds.getValue(ModelTravel::class.java)
                        travelLists.add(model!!)
                    }
                }

                if(applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    rvTravel.layoutManager = GridLayoutManager(this@MainActivity, 2)
                } else {
                    rvTravel.layoutManager = LinearLayoutManager(this@MainActivity)
                }
                rvTravel.adapter = adapterTravel
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "Fail to read the database : " + error.message)

                throw error.toException()
            }
        })

        //Mengirim Data ke Detail
        adapterTravel.setOnItemClickCallback(object: AdapterTravel.OnItemClickCallback{
            override fun onItemClicked(data: ModelTravel) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("DATA", data)
                startActivity(intent)
            }
        })
    }

    private fun setToolbar(){
        toolbar = findViewById(R.id.mainToolbar)
        setSupportActionBar(toolbar)
        toolbar.subtitle = "Travel Catalog App"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.about_page -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}