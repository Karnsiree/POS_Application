package com.stamford.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderListRVAdapterIcecream(private val orderlistic: ArrayList<Icecream>) : RecyclerView.Adapter<OrderListRVAdapterIcecream.ViewHolder>(){

    class ViewHolder(Listitemview : View):RecyclerView.ViewHolder(Listitemview){
        val itemnametextview = Listitemview.findViewById<TextView>(R.id.ItemNameTextview)
        val itempricetextview = Listitemview.findViewById<TextView>(R.id.ItemPriceTextView)
        val itemquantitytextview = Listitemview.findViewById<TextView>(R.id.ItemQuantityTextview)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val OrderView = LayoutInflater.from(context).inflate(R.layout.orderviewholder_layout,parent,false)
        return ViewHolder(OrderView)
    }

    override fun onBindViewHolder(holder: OrderListRVAdapterIcecream.ViewHolder, position: Int) {
        val item= orderlistic[position]
        holder.itemnametextview.setText(item.name)
        holder.itempricetextview.setText(item.price.toString())
        holder.itemquantitytextview.setText(item.quantity.toString())
    }

    override fun getItemCount(): Int {
        return orderlistic.size
    }
}