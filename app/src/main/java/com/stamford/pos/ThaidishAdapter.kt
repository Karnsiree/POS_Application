package com.stamford.pos

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ThaidishAdapter(
    private val mThaidishes: List<Thaidish>,
    private val onItemClicked: (Thaidish) -> Unit
) : RecyclerView.Adapter<ThaidishAdapter.ViewHolder>() {
    private val TAG = "ThaidishAdapter"

    inner class ViewHolder(
        listItemView: View,
        onItemClicked_fun: (Int) -> Unit
    ) : RecyclerView.ViewHolder(listItemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val productImageView: ImageView = listItemView.findViewById<ImageView>(R.id.imageView_product)
        val productNameTextView: TextView = listItemView.findViewById<TextView>(R.id.textView_productName)
        val productPriceTextView: TextView = listItemView.findViewById<TextView>(R.id.textView_productPrice)
        val addProductButton: Button = listItemView.findViewById<Button>(R.id.button_product_add)
        val delProductButton: Button = listItemView.findViewById<Button>(R.id.button_product_del)
        init {
            listItemView.setOnClickListener {
                Log.d(TAG, "adapterPosition = $adapterPosition")
                onItemClicked_fun(adapterPosition)
            }
            addProductButton.setOnClickListener{
                OrderActivity.addorremovefromthelist="ADD"
                onItemClicked_fun(adapterPosition)
            }
            delProductButton.setOnClickListener {
                OrderActivity.addorremovefromthelist="REMOVE"
                onItemClicked_fun(adapterPosition)
            }


        }


    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThaidishAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val thaidishView = inflater.inflate(R.layout.layout_item_product, parent, false)
        //return a new holder instance
        return ViewHolder(thaidishView) {
            Log.d(TAG, "ViewHolder with item no. $it")
            onItemClicked(mThaidishes[it])
        }
    }

    override fun onBindViewHolder(viewHolder: ThaidishAdapter.ViewHolder, position: Int){
        Log.d(TAG, "position = $position")
        //Get the data model based on position
        val thaidish: Thaidish = mThaidishes[position]

        //set item views based on your views and data model
        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.thaidish_tom_yum_goong)
            1 -> imageView.setImageResource(R.drawable.thaidish_tom_kha_gai)
            2 -> imageView.setImageResource(R.drawable.thaidish_pad_thai)
            3 -> imageView.setImageResource(R.drawable.thaidish_khao_pad)
            4 -> imageView.setImageResource(R.drawable.thaidish_pad_krapow)
            5 -> imageView.setImageResource(R.drawable.thaidish_khao_soi)
            6 -> imageView.setImageResource(R.drawable.thaidish_kai_jeow)
            else -> {
                imageView.setImageResource(R.drawable.thaidish_tom_yum_goong)
            }
        }

        nameTextView.text = thaidish.name
        priceTextView.text = thaidish.price.toString()
        addButton.text = "+"
        delButton.text = "-"


    }



    //returns the total count of items in the list
    override fun getItemCount(): Int{
        return mThaidishes.size
    }
}

