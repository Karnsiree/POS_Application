package com.stamford.pos

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IcecreamAdapter(
    private val mIcecreams: List<Icecream>,
    private val onItemClicked: (Icecream) -> Unit
) : RecyclerView.Adapter<IcecreamAdapter.ViewHolder>() {
    private val TAG = "IcecreamAdapter"

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IcecreamAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val icecreamView = inflater.inflate(R.layout.layout_item_product, parent, false)
        //return a new holder instance
        return ViewHolder(icecreamView) {
            Log.d(TAG, "ViewHolder with item no. $it")
            onItemClicked(mIcecreams[it])
        }
    }

    override fun onBindViewHolder(viewHolder: IcecreamAdapter.ViewHolder, position: Int){
        Log.d(TAG, "position = $position")
        //Get the data model based on position
        val icecream: Icecream = mIcecreams[position]

        //set item views based on your views and data model
        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.icecream_vanilla)
            1 -> imageView.setImageResource(R.drawable.icecream_chocolate)
            2 -> imageView.setImageResource(R.drawable.icecream_matcha)
            3 -> imageView.setImageResource(R.drawable.icecream_strawberry)
            4 -> imageView.setImageResource(R.drawable.icecream_cookies_and_cream)
            5 -> imageView.setImageResource(R.drawable.icecream_mint_chocolate_chip)
            6 -> imageView.setImageResource(R.drawable.icecream_cotton_candy)
            else -> {
                imageView.setImageResource(R.drawable.icecream_vanilla)
            }
        }

        nameTextView.text = icecream.name
        priceTextView.text = icecream.price.toString()
        addButton.text = "+"
        delButton.text = "-"


    }



    //returns the total count of items in the list
    override fun getItemCount(): Int{
        return mIcecreams.size
    }
}

