package com.abhishekdev.employeedirectory

import android.os.Parcelable
import java.io.Serializable

// data class
data class ExampleEmp(val imageResource: Int,  val name: String,  val designation: String,
    val department: String, val station: String,  val company:String): Serializable{

     fun printStatement(): String{
         return "$designation at $company"
     }
 }


