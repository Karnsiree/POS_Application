package com.stamford.pos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    //1st fragment or fragment A
    private val TAG = "ProductFragment"
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
        val layout = inflater.inflate(R.layout.fragment_product, container, false)
        // Initialize macarons
        val rvMacaron = layout.findViewById<View>(R.id.rvProductsListInFragment) as RecyclerView
        val macarons = Macaron.createMacaronsList()
        // Create adapter passing in the sample user data
        // Pay attention that we are passing the onClick listener callback
        // as the 2nd paratamer (as lamdba)
        // macaron is the argument and it is an Int
        val adapter = MacaronAdapter(macarons) { macaron ->
            Log.d(TAG, "onClick Listener: ${macaron.name}")
            // Use bungles if you need to pass some information to fragment
            val bundle = bundleOf("macaron_id_int" to macaron.id)
            val transaction = parentFragmentManager.beginTransaction()
            val showProductFrag = ShowProductFragment() //fragment B
            showProductFrag.arguments = bundle
//            showProductFrag.arguments = Bundle(1).apply {
//                putInt("macaron_id_int", macaron.id.toInt())

            val view: FragmentContainerView? =
//                activity?.findViewById<FragmentContainerView>(R.id.show_product_fragmentContainerView2)
                activity?.findViewById<FragmentContainerView>(R.id.show_product_fragmentContainerView2)

            if (view != null) {
                // device is xlarge and FragmentContainerView exists
//                transaction.replace(R.id.show_product_fragmentContainerView2, showProductFrag)
                transaction.replace(R.id.show_product_fragmentContainerView2, showProductFrag)

            } else {
                // device is small and FragmentContainerView does not exists
//                transaction.replace(R.id.show_product_fragmentContainerView, showProductFrag)
                transaction.replace(R.id.product_product_fragmentContainerView, showProductFrag)

            }

            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
            }

            // Attach the adapter to the recyclerview to populate items
            rvMacaron.adapter = adapter
            // Set layout manager to position the items
            rvMacaron.layoutManager = LinearLayoutManager(layout.context)
            return layout
        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}