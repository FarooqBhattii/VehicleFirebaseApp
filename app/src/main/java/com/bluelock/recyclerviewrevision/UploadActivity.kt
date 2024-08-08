package com.bluelock.recyclerviewrevision

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bluelock.recyclerviewrevision.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val ownerName = binding.uploadOwnerName.text.toString()
            val vehicleBrand = binding.uploadVehicleBrand.text.toString()
            val vehicleRTO = binding.uploadVehicleRto.text.toString()
            val vehicleNumber = binding.uploadVehicleNumber.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("VehicleData")
            val vehicleData = VehicleData(ownerName, vehicleBrand, vehicleRTO, vehicleNumber)

            databaseReference.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                binding.uploadOwnerName.text.clear()
                binding.uploadVehicleBrand.text.clear()
                binding.uploadVehicleRto.text.clear()
                binding.uploadVehicleNumber.text.clear()

                Toast.makeText(this,"Data Saved Successfully", Toast.LENGTH_LONG).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,"An Error Occurred, Try Again Later", Toast.LENGTH_LONG).show()

            }
        }

    }
}