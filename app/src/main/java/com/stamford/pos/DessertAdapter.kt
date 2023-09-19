package com.stamford.pos

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DessertAdapter(
    private val mDesserts: List<Dessert>,
    private val onItemClicked: (Dessert) -> Unit
) : RecyclerView.Adapter<DessertAdapter.ViewHolder>() {
    private val TAG = "DessertAdapter"

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val dessertView = inflater.inflate(R.layout.layout_item_product, parent, false)
        //return a new holder instance
        return ViewHolder(dessertView) {
            Log.d(TAG, "ViewHolder with item no. $it")
            onItemClicked(mDesserts[it])
        }
    }

    override fun onBindViewHolder(viewHolder: DessertAdapter.ViewHolder, position: Int){
        Log.d(TAG, "position = $position")
        //Get the data model based on position
        val dessert: Dessert = mDesserts[position]

        //set item views based on your views and data model
        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.dessert_lemon_meringue_pie)
            1 -> imageView.setImageResource(R.drawable.dessert_dark_chocolate_pudding)
            2 -> imageView.setImageResource(R.drawable.dessert_chocolate_chip_cookie_sandwiches)
            3 -> imageView.setImageResource(R.drawable.dessert_caramel_cake)
            4 -> imageView.setImageResource(R.drawable.dessert_crumble_cake_with_berries)
            5 -> imageView.setImageResource(R.drawable.dessert_brownie)
            6 -> imageView.setImageResource(R.drawable.dessert_pumpkin_pie)
            else -> {
                imageView.setImageResource(R.drawable.dessert_lemon_meringue_pie)
            }
        }

        nameTextView.text = dessert.name
        priceTextView.text = dessert.price.toString()
        addButton.text = "+"
        delButton.text = "-"


    }



    //returns the total count of items in the list
    override fun getItemCount(): Int{
        return mDesserts.size
    }
}

