package com.stamford.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class OrderManagerAdapter(private val localOrderList:List<Order>, private val onOrderClicked: (Order) -> Unit):RecyclerView.Adapter<OrderManagerAdapter.ViewHolder>() {

    class ViewHolder(listItemView: View, onOrderClicked_fun: (Int) -> Unit):RecyclerView.ViewHolder(listItemView){
        val localOrderId = listItemView.findViewById<TextView>(R.id.order_local_id_tv)
        val localOrderBranchId = listItemView.findViewById<TextView>(R.id.order_branch_id_tv)
        val localOrderStaffId = listItemView.findViewById<TextView>(R.id.order_staff_id_tv)
        val localOrderDeleteBtn = listItemView.findViewById<Button>(R.id.order_delete_btn)
        init {
            listItemView.setOnClickListener {
                OrderFragment.DeleteOrNot = ""
                onOrderClicked_fun(adapterPosition)
            }
            localOrderDeleteBtn.setOnClickListener{
                OrderFragment.DeleteOrNot = "Delete"
                OrderlineFragment.DeleteDataFromOrderlines = "Delete"
                onOrderClicked_fun(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val localOrderView = LayoutInflater.from(context).inflate(R.layout.localorder_manager_viewholder, parent,false)
        return ViewHolder(localOrderView) {
            onOrderClicked(localOrderList[it])
        }
    }

    override fun onBindViewHolder(holder: OrderManagerAdapter.ViewHolder, position: Int) {
        val item = localOrderList[position]
        holder.localOrderId.text = item.uid.toString()
        holder.localOrderBranchId.text = item.branchID.toString()
        holder.localOrderStaffId.text = item.staffID.toString()

    }

    override fun getItemCount(): Int {
        return localOrderList.size
    }
}