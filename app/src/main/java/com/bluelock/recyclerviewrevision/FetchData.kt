package com.bluelock.recyclerviewrevision

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bluelock.recyclerviewrevision.databinding.ActivityFetchDataBinding
import com.bluelock.recyclerviewrevision.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FetchData : AppCompatActivity() {

    private lateinit var binding: ActivityFetchDataBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFetchDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.searchButton.setOnClickListener {
    val searchVehicleNumber: String = binding.searchVehicleNumber.text.toString()

    if (searchVehicleNumber.isNotEmpty()){
        readData(searchVehicleNumber)
    } else{
        Toast.makeText(this,"Please Enter Vehicle Number", Toast.LENGTH_LONG).show()

    }
}




    }

    fun readData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("VehicleData")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            if(it.exists()){
                val ownerName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                val vehicleRTO = it.child("vehicleRTO").value

                Toast.makeText(this,"Vehicle Found", Toast.LENGTH_LONG).show()

                binding.searchVehicleNumber.text.clear()
                binding.readOwnerName.text = ownerName.toString()
                binding.readVehicleBrand.text = vehicleBrand.toString()
                binding.readVehicleRTO.text = vehicleRTO.toString()
            } else{
                Toast.makeText(this,"Vehicle Not Found", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Something went Wrong",Toast.LENGTH_LONG).show()
        }
    }
}