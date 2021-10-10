package com.abhishekdev.employeedirectory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abhishekdev.employeedirectory.databinding.ActivityMainBinding
import com.abhishekdev.employeedirectory.databinding.ActivityProfileViewBinding
import com.abhishekdev.employeedirectory.ExampleEmp
import android.graphics.ColorSpace.Model
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


//Profile Viewer Activity
class ProfileViewActivity() : AppCompatActivity(){

    private lateinit var binding: ActivityProfileViewBinding

    private lateinit var arr: ArrayList<ExampleEmp>
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make sure to make your data class as Serializable
        arr = intent.getSerializableExtra("EXTRA_ARR") as ArrayList<ExampleEmp>
        pos = intent.getIntExtra("EXTRA_POS", 0)
        var item = arr[pos];

        binding.ivProfilePicture.setImageResource(item.imageResource);
        binding.tvProfileName.setText(item.name)
        binding.tvProfileStatement.setText(item.printStatement())
        binding.tvDesignation.setText(item.designation)
        binding.tvDepartment.setText(item.department)
        binding.tvStation.setText(item.station)
        binding.tvCompany.setText(item.company)

        // back-button
        binding.backButton.setOnClickListener{
            finish()
        }

        binding.options.setOnClickListener{
            Toast.makeText(this, "This feature isn't implemented yet. :(", Toast.LENGTH_SHORT).show()
        }
    }
}