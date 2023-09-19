package com.stamford.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
class OrderLineAdapter(private val localOrderLineList: List<OrderLine>, private val onOrderLineClicked: (OrderLine)->Unit):RecyclerView.Adapter<OrderLineAdapter.ViewHolder>() {

    class ViewHolder(listItemView: View,onOrderLineClicked_fun:(Int)->Unit):RecyclerView.ViewHolder(listItemView){
*/

class OrderLineAdapter(private val localOrderLineList: List<OrderLine>, private val onOrderLineClicked: (OrderLine)->Unit):RecyclerView.Adapter<OrderLineAdapter.ViewHolder>() {

    class ViewHolder(listItemView: View,onOrderLineClicked_fun:(Int)->Unit):RecyclerView.ViewHolder(listItemView){

        val localOrderLineID = listItemView.findViewById<TextView>(R.id.orderline_local_id_tv)
        val localOrderLineProductID = listItemView.findViewById<TextView>(R.id.order_product_id_tv)
        val localOrderLineProductPrice = listItemView.findViewById<TextView>(R.id.order_product_price_tv)
        val localOrderLineProductQuantity = listItemView.findViewById<TextView>(R.id.orderline_product_quantity_tv)
        val localOrderLineEditBtn = listItemView.findViewById<Button>(R.id.orderline_edit_btn)
        val localOrderLineDeleteBtn = listItemView.findViewById<Button>(R.id.orderline_delete_btn)

        init {

            localOrderLineEditBtn.setOnClickListener {
                OrderlineFragment.EditOrDelete="Edit"
                onOrderLineClicked_fun(adapterPosition)
            }

            localOrderLineDeleteBtn.setOnClickListener {
                OrderlineFragment.EditOrDelete="Delete"
                onOrderLineClicked_fun(adapterPosition)
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val localOrderLineView = LayoutInflater.from(context)
            .inflate(R.layout.localorderline_manager_viewholder, parent, false)
        return ViewHolder(localOrderLineView)
        {
            onOrderLineClicked(localOrderLineList[it])
        }
    }

    override fun onBindViewHolder(holder: OrderLineAdapter.ViewHolder, position: Int) {
        val item = localOrderLineList[position]
        holder.localOrderLineID.text = item.uid.toString()
        holder.localOrderLineProductID.text = item.productID.toString()
        holder.localOrderLineProductPrice.text = item.price.toString()
        holder.localOrderLineProductQuantity.text = item.quantity.toString()
    }

//        val context = holder.itemView.context
//        holder.localOrderLineDeleteBtn.setOnClickListener{
//            val db = POSAppDatabase.getInstance(getContext())
//            db.orderLineDao().delete(orderLine)
//
//            val index = orderLineList.indexOf(orderLine)
//
//            db.orderLineDao().delete(orderLine)
//            orderLineList.removeAt(index)
//            deleteItem(position)
//        }




//    fun deleteItem(index: Int){
////        val index = orderLineList.indexOf(orderLine)
//        var orderLineList = arrayListOf<OrderLine>()
//        GlobalScope.launch {
////            val db = POSAppDatabase.getInstance(requireContext())
////            db.orderLineDao().delete(orderLine)
////            orderLineList.removeAt(index)
//            orderLineList.removeAt(index)
//            notifyDataSetChanged()
//        }
//
//    }


    override fun getItemCount(): Int {
        return  localOrderLineList.size
    }
}