package com.abhishekdev.employeedirectory

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.abhishekdev.employeedirectory.databinding.ItemViewBinding

// Adapter class adapts your data so it can be consumable to recycler view.`
// Recycler view request data from adapter, adapter provides the data.
class MyAdapter(private var exampleList: ArrayList<ExampleEmp>,
                private val clickListener: ItemClickListener)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    // creates ViewHolder, which stores your item view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // LayoutInflater converts XML to java/kotlin obj
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    // for binding data to the ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageResource(exampleList[position].imageResource)
        holder.tvName.text = exampleList[position].name
        holder.tvStatement.text = exampleList[position].printStatement()

        // ClickListener
        holder.relativeLayout.setOnClickListener{
            // Calling on-click
            clickListener.onClick(position)
        }

        holder.btnDelete.setOnClickListener{
            exampleList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, exampleList.size)
        }

        holder.btnEdit.setOnClickListener{
            clickListener.onEdit(position)
        }

    }

    // for getting passed list length
    override fun getItemCount(): Int {
        return exampleList.size
    }

    // view holder class, which holds view.
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // Reference to single item
        val relativeLayout: RelativeLayout = itemView.findViewById(R.id.relative_ly)

        // Reference of views inside Item, inside of XML.
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvStatement: TextView = itemView.findViewById(R.id.tvStatement)
        val btnDelete: ImageView = itemView.findViewById(R.id.iv_del)
        val btnEdit: ImageView = itemView.findViewById(R.id.ivPencil)
    }
}


