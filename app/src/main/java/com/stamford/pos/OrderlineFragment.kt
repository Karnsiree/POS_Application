package com.stamford.pos

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [OrderlineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderlineFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var orderLineList = arrayListOf<OrderLine>()
        val layout = inflater.inflate(R.layout.fragment_orderline, container, false)
        val clickedOrderID = arguments?.getInt("order_local_id")
        val orderIDToDelete = arguments?.getInt("order_local_id_to_del")
        val localOrderLineRV = layout.findViewById<RecyclerView>(R.id.local_orderline_rv)
        var adapter:OrderLineAdapter? = null
        adapter= OrderLineAdapter(orderLineList) { orderLine ->
            if (EditOrDelete.equals("Edit")) {
                val intent = Intent(this.requireContext(), EditOrderLineActivity::class.java)
                intent.putExtra("order_id", orderLine.orderID)
                intent.putExtra("product_id", orderLine.productID)
                Log.i("OrderlineFragment", "product id: ${orderLine.productID}")
                intent.putExtra("product_price", orderLine.price)
                intent.putExtra("product_quantity", orderLine.quantity)
                startActivity(intent)
            } else if (EditOrDelete.equals("Delete")) {
                val alertDialog = AlertDialog.Builder(this.requireContext())
                alertDialog.setTitle("OrderLine Removal")
                alertDialog.setMessage("Are you sure you want to delete?")

                alertDialog.setPositiveButton("Yes") { dialog, which ->
                    val index = orderLineList.indexOf(orderLine)
                    GlobalScope.launch {
                        val db = POSAppDatabase.getInstance(requireContext())
                        db.orderLineDao().delete(orderLine)
                        orderLineList.removeAt(index)
                    }
                    adapter?.notifyItemRemoved(index) //update the recyclerview
                    Toast.makeText(requireContext(), "Selected orderline removed", Toast.LENGTH_SHORT).show()
                }
                alertDialog.setNegativeButton("No") {
                        dialog,which ->
                    Toast.makeText(this.requireContext(), "Canceled", Toast.LENGTH_SHORT).show()
                }
                alertDialog.show()
            }
        }

//        val adapter = OrderLineAdapter(orderLineList)
        localOrderLineRV.adapter=adapter
        val db = POSAppDatabase.getInstance(this.requireContext())
        GlobalScope.launch {
            val orderLineInDB = db.orderLineDao().getAll()
            for (orderLine in orderLineInDB){
                if (orderLine.orderID.toInt()==(clickedOrderID)) {
                    orderLineList.add(orderLine)
                }
                else if (orderLine.orderID.toInt()==(orderIDToDelete)) {
                    orderLineList.remove(orderLine)
                    db.orderLineDao().delete(orderLine)
                }
            }
        }
        adapter.notifyDataSetChanged()
        localOrderLineRV.layoutManager=LinearLayoutManager(this.requireContext())

        return layout

    }

    companion object {
        var EditOrDelete:String?=null
        var DeleteDataFromOrderlines: String= ""
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderlineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderlineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}