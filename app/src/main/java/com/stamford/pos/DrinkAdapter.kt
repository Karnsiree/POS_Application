package com.stamford.pos

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DrinkAdapter(
    private val mDrinks: List<Drink>,
    private val onItemClicked: (Drink) -> Unit
) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {
    private val TAG = "DrinkAdapter"

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val drinkView = inflater.inflate(R.layout.layout_item_product, parent, false)
        //return a new holder instance
        return ViewHolder(drinkView) {
            Log.d(TAG, "ViewHolder with item no. $it")
            onItemClicked(mDrinks[it])
        }
    }

    override fun onBindViewHolder(viewHolder: DrinkAdapter.ViewHolder, position: Int){
        Log.d(TAG, "position = $position")
        //Get the data model based on position
        val drink: Drink = mDrinks[position]

        //set item views based on your views and data model
        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.drink_mineral_water)
            1 -> imageView.setImageResource(R.drawable.drink_coke)
            2 -> imageView.setImageResource(R.drawable.drink_coffee)
            3 -> imageView.setImageResource(R.drawable.drink_hot_chocolate)
            4 -> imageView.setImageResource(R.drawable.drink_orange_juice)
            5 -> imageView.setImageResource(R.drawable.drink_beer)
            6 -> imageView.setImageResource(R.drawable.drink_wine)
            else -> {
                imageView.setImageResource(R.drawable.drink_mineral_water)
            }
        }

        nameTextView.text = drink.name
        priceTextView.text = drink.price.toString()
        addButton.text = "+"
        delButton.text = "-"


    }



    //returns the total count of items in the list
    override fun getItemCount(): Int{
        return mDrinks.size
    }
}

