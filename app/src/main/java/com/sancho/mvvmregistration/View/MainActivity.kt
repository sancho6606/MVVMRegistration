package com.sancho.mvvmregistration.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sancho.mvvmregistration.Model.User
import com.sancho.mvvmregistration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var databaseReferecnce: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReferecnce = FirebaseDatabase.getInstance().getReference().child("")

        binding.apply {
            textviewregistration.setOnClickListener {
                startActivity(Intent(this@MainActivity,MainActivityRegistration::class.java))
            }
        }

    }
}