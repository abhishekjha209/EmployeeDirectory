package com.abhishekdev.employeedirectory

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavType
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishekdev.employeedirectory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var exampleList: ArrayList<ExampleEmp>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Array Initialisation
        exampleList = generateDummyList(50);

        // Recycler-view integration
        binding.recyclerView.adapter = MyAdapter(exampleList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)


        // Create/Add Employee Operation
        binding.floatingAddButton.setOnClickListener{
            Log.d("float", "onCreate: ")

            val editDialog: Dialog = Dialog(this)
            editDialog.setContentView(R.layout.activity_dialog_box)

            var etName = editDialog.findViewById<EditText>(R.id.etName)
            var etDesignation = editDialog.findViewById<EditText>(R.id.etDesignation)
            var etDepartment = editDialog.findViewById<EditText>(R.id.etDepartment)
            var etStation = editDialog.findViewById<EditText>(R.id.etStation)
            var etCompany = editDialog.findViewById<EditText>(R.id.etCompany)
            var btnModify = editDialog.findViewById<Button>(R.id.btnModify)
            var btnDecline = editDialog.findViewById<Button>(R.id.btnDecline)
            var tvBasic = editDialog.findViewById<TextView>(R.id.tvBasicInfo)

            tvBasic.setText("Create New Employee")

            btnModify.setOnClickListener{
                exampleList.add(0,ExampleEmp( R.drawable.ic_profile_pic, etName.text.toString(),
                    etDesignation.text.toString(), etDepartment.text.toString(),
                    etStation.text.toString(), etCompany.text.toString()))

                binding.recyclerView.adapter?.notifyDataSetChanged()
                editDialog.dismiss()
            }
            editDialog.show()

            btnDecline.setOnClickListener{
                editDialog.hide();
            }

        }

        binding.ivSlider.setOnClickListener{
            Toast.makeText(this, "This feature isn't implemented yet. :(", Toast.LENGTH_SHORT).show()
        }


    }

    // over-riding onClick Behaviour
    override fun onClick(position: Int) {
        Log.d("Check", "onClick: ")
        // Intent to pass data from recycler-view item to ProfileViewActivity.
        Intent(this, ProfileViewActivity()::class.java).also {
            it.putExtra("EXTRA_ARR", exampleList)
            it.putExtra("EXTRA_POS", position)
            startActivity(it)
        }
    }

    // Edit Employee Details operation.
    override fun onEdit(position: Int) {
        val editDialog: Dialog = Dialog(this)
        editDialog.setContentView(R.layout.activity_dialog_box)

        var etName = editDialog.findViewById<EditText>(R.id.etName)
        var etDesignation = editDialog.findViewById<EditText>(R.id.etDesignation)
        var etDepartment = editDialog.findViewById<EditText>(R.id.etDepartment)
        var etStation = editDialog.findViewById<EditText>(R.id.etStation)
        var etCompany = editDialog.findViewById<EditText>(R.id.etCompany)
        var btnModify = editDialog.findViewById<Button>(R.id.btnModify)
        var btnDecline = editDialog.findViewById<Button>(R.id.btnDecline)

        etName.setText(exampleList[position].name)
        etDesignation.setText(exampleList[position].designation)
        etDepartment.setText(exampleList[position].department)
        etStation.setText(exampleList[position].station)
        etCompany.setText(exampleList[position].company)

        btnModify.setOnClickListener{
            exampleList[position] =
                ExampleEmp( exampleList[position].imageResource, etName.text.toString(),
                    etDesignation.text.toString(), etDepartment.text.toString(),
                    etStation.text.toString(), etCompany.text.toString())
            binding.recyclerView.adapter?.notifyDataSetChanged()
            editDialog.dismiss()
        }
        editDialog.show()

        btnDecline.setOnClickListener{
            editDialog.hide();
        }

    }

    // Example Employee List
    private fun generateDummyList(size: Int): ArrayList<ExampleEmp>{
        val list = ArrayList<ExampleEmp>()
        for (i in 0 until size) {
            val item = when (i % 5) {
                0 -> ExampleEmp(
                    R.drawable.ic_one, "Dwight Scrute", "Lead", "Design",
                    "California", "Facebook Inc.",)
                1 -> ExampleEmp(R.drawable.ic_fiv, "Christina Applegate",
                    "Senior Developer", "Development", "New York",
                    "Adobe Systems")
                2 -> ExampleEmp(R.drawable.ic_two, "Adam Levine", "Product Manager",
                    "Research", "Las Vegas", "HashedIn Ltd.")
                3 -> ExampleEmp(R.drawable.ic_fou,"Nancy Malkova", "Manger",
                    "Consultancy", "Texas", "McKinsey & Rice")
                else -> ExampleEmp(R.drawable.ic_thr, "Hasan Minaj",
                    "Software Engineer",
                    "Development", "India", "Trivago" )
            }
            list += item
        }
        return list
    }
}