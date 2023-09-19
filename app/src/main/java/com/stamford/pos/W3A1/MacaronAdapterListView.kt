package com.stamford.pos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MacaronAdapterListView(
    private val context: Context,
    private val dataSource: ArrayList<Macaron>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val listItemView = inflater.inflate(R.layout.layout_item_product, parent, false)

        val imageView: ImageView = listItemView.findViewById<ImageView>(R.id.imageView_product)
        val nameTextView: TextView = listItemView.findViewById<TextView>(R.id.textView_productName)
        val priceTextView: TextView = listItemView.findViewById<TextView>(R.id.textView_productPrice)
        val addButton: Button = listItemView.findViewById<Button>(R.id.button_product_add)
        val delButton: Button = listItemView.findViewById<Button>(R.id.button_product_del)

        val macaron=dataSource[position]

        when (position) {
            0 -> imageView.setImageResource(R.drawable.macaron_black)
            1 -> imageView.setImageResource(R.drawable.macaron_blue)
            2 -> imageView.setImageResource(R.drawable.macaron_green)
            3 -> imageView.setImageResource(R.drawable.macaron_navy)
            4 -> imageView.setImageResource(R.drawable.macaron_pink)
            5 -> imageView.setImageResource(R.drawable.macaron_red)
            6 -> imageView.setImageResource(R.drawable.macaron_yellow)
            else -> {
                imageView.setImageResource(R.drawable.macaron_black)
            }
        }

        nameTextView.setText(macaron.name)
        priceTextView.setText(macaron.price.toString())
        addButton.setText("+")
        delButton.setText("-")

        return listItemView
    }
}