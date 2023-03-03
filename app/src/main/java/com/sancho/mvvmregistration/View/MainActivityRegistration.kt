package com.sancho.mvvmregistration.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sancho.mvvmregistration.Model.User
import com.sancho.mvvmregistration.R
import com.sancho.mvvmregistration.databinding.ActivityMainRegistrationBinding

class MainActivityRegistration : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivityMainRegistrationBinding
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Information")

        binding.apply {
            buttonregister.setOnClickListener {
                if (checkedittext()) {
                    progressbarregistrationlogin.visibility= View.VISIBLE
           firebaseAuth.createUserWithEmailAndPassword(edittextregistrationlogin.text.toString(),edittextregistrationpassword.text.toString()).addOnCompleteListener{
               if (it.isSuccessful){
                   progressbarregistrationlogin.visibility= View.INVISIBLE
                   Toast.makeText(this@MainActivityRegistration,"Successfully",Toast.LENGTH_SHORT).show()
                   val user=User(
                       username = edittextregistrationname.text.toString(),
                       surname = edittextregistrationsurname.text.toString(),
                       number = edittextregistrationnumber.text.toString(),
                       login = edittextregistrationlogin.text.toString(),
                       password = edittextregistrationpassword.text.toString())
                   databaseReference.child(edittextregistrationname.text.toString()).setValue(user)
               }else{
                   Toast.makeText(this@MainActivityRegistration,"Successfully",Toast.LENGTH_SHORT).show()
                   progressbarregistrationlogin.visibility= View.INVISIBLE
               }
           }
                }
            }
        }

    }

    fun checkedittext(): Boolean {
        var nice: Boolean? = false
        binding.apply {
            if (edittextregistrationname.text.isEmpty()) {
                edittextregistrationname.setError("Write your Name!")
            } else if (edittextregistrationsurname.text.isEmpty()) {
                edittextregistrationsurname.setError("Write your Surname!")
            } else if (edittextregistrationnumber.text.length < 8) {
                edittextregistrationnumber.setError("Phone number required 8 character!")
            } else if (edittextregistrationlogin.text.isEmpty()) {
                edittextregistrationlogin.setError("Write your Email")
            } else if (edittextregistrationpassword.text.isEmpty()) {
                edittextregistrationpassword.setError("Write your Email Password")
            } else if (edittextregistrationpassword.text.length < 8) {
                edittextregistrationpassword.setError("Password required 8 character!")
            } else {
                Toast.makeText(this@MainActivityRegistration, "Successfully", Toast.LENGTH_SHORT)
                    .show()
                nice = true
            }
            return nice!!
        }
    }

}