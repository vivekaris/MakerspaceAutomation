package com.example.developer.makerspace

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import android.widget.CompoundButton


class MainActivity : AppCompatActivity() {
   val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val fan = database.getReference("fan")
        val light = database.getReference("light")
        val cooler = database.getReference("cooler")
        val ac = database.getReference("ac")




// Read from the database
        fan.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                textView.text=value;
                Log.d(TAG, "Value is: " + value!!)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                textView.text="Error to read";
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })



        switch1.setOnCheckedChangeListener { buttonView, isChecked -> Log.v("Switch State=", "" + isChecked)
            if (isChecked) {
                textView.text="Light ON ";
                light.setValue("1")
            } else {
                textView.text="Light OFF ";
                light.setValue("0")
            }

        };

        switch2.setOnCheckedChangeListener { buttonView, isChecked -> Log.v("Switch State=", "" + isChecked)
            if (isChecked) {
                textView.text="Fan ON ";
                fan.setValue("1")
            } else {
                textView.text="Fan OFF ";
                fan.setValue("0")
            }

        };

        switch3.setOnCheckedChangeListener { buttonView, isChecked -> Log.v("Switch State=", "" + isChecked)
            if (isChecked) {
                textView.text="Cooler ON ";
                cooler.setValue("1")
            } else {
                textView.text="Cooler OFF ";
                cooler.setValue("0")
            }

        };
        switch4.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                textView.text="AC ON ";
                ac.setValue("1")
            } else {
                textView.text="AC OFF ";
                ac.setValue("0")
            }

        };

    }//oncreate



}
