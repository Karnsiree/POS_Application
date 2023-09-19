package com.stamford.pos

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
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
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
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
        val layout = inflater.inflate(R.layout.fragment_order, container, false)
        val localOrderRecyclerView = layout.findViewById<RecyclerView>(R.id.local_order_rv)
        val db = POSAppDatabase.getInstance(this.requireContext())
        var localOrderList = arrayListOf<Order>()
        var orderLineList = arrayListOf<OrderLine>()
        GlobalScope.launch {
            val orders = db.orderDao().getAll()
            localOrderList.addAll(orders)
        }

        var adapter:OrderManagerAdapter? =null
        adapter = OrderManagerAdapter(localOrderList){
                order ->
            if (DeleteOrNot.equals("")) {
                val arg = Bundle()
                arg.putInt(
                    "order_local_id",
                    order.uid!!.toInt()
                ) //pass information from this fragment to OrderlinesFragment for further use
                fragmentManager?.commit {
                    setReorderingAllowed(true)
                    //replace whatever is in the orderlinesFragmentContainer with OrderlinesFragment
                    replace<OrderlineFragment>(
                        R.id.orderlineFragmentContainerView,
                        "order_local_id",
                        arg
                    )
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                }
            }

            else if (DeleteOrNot.equals("Delete")) {

                val arg = Bundle()
                arg.putInt(
                    "order_local_id_to_delete",
                    order.uid!!.toInt()
                ) //pass information from this fragment to OrderlinesFragment for further use


                val alertDialog = AlertDialog.Builder(this.requireContext())
                alertDialog.setTitle("Order Removal")
                alertDialog.setMessage("Do you want to remove this order?")

                alertDialog.setPositiveButton("Yes") { dialog, which ->
                    //creates a button that when clicked,it deletes tje clicked orderline from db
                    val orderindex = localOrderList.indexOf(order)//Get index of that order from listoforder
                    GlobalScope.launch {
                        val db = POSAppDatabase.getInstance(requireContext())
                        db.orderDao().delete(order) //delete that from the dabase
                        localOrderList.removeAt(orderindex) //remove that orderline from the listoforderline

                    }
                    adapter?.notifyItemRemoved(orderindex) //update the recyclerview
                    Toast.makeText(requireContext(), "Selected order removed", Toast.LENGTH_SHORT).show()
                    fragmentManager?.commit {
                        setReorderingAllowed(true)
                        //replace whatever is in the orderlinesFragmentContainer with OrderlinesFragment
                        replace<OrderlineFragment>(
                            R.id.orderlineFragmentContainerView,
                            "order_local_id_to_delete",
                            arg
                        )
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    }
                }


                //create a button that when clicked,will cancel the operation if the user misclicks
                alertDialog.setNegativeButton("No") {
                        dialog,which ->
                    Toast.makeText(this.requireContext(), "Canceled", Toast.LENGTH_SHORT).show()
                }
                alertDialog.show()

            }

            val alertDialog = AlertDialog.Builder(this.requireContext())
            alertDialog.setTitle("Update Operation")
            alertDialog.setMessage("Update succeed")
            alertDialog.setNeutralButton("Return to Order Manager"){
                    dialog, which ->
                val intent = Intent(this.requireContext(),OrderManagerActivity::class.java)
                startActivity(intent)
            }
        }


        localOrderRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        localOrderRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        return layout
    }

    companion object {
        var DeleteOrNot:String= ""

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}