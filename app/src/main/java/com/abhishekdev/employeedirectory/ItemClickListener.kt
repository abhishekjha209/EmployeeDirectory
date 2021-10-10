package com.abhishekdev.employeedirectory

import android.view.View

interface ItemClickListener {
    fun onClick( position:Int);
    fun onEdit(position: Int)
}