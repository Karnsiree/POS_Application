package com.stamford.pos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowProductFragment : Fragment() {
    //2nd fragment or fragment B
    private val TAG = "ShowProductFragment"
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
        val rootView = inflater.inflate(R.layout.fragment_show_product, container, false)
        // This is how we get the information from Bundle
        // Do you still remember the Mr. Elvis operator ?
        val macaron_id = arguments?.getInt("macaron_id_int") ?: 1000

        val macarons = Macaron.createMacaronsList()
        val macaron_tv = rootView.findViewById<TextView>(R.id.product_name_textView)
        // Just a trick: subtract vy 1000 so we can get the index of macarons
        macaron_tv.text = macarons[macaron_id - 1000].name
        val macaron_img = rootView.findViewById <ImageView>(R.id.product_image_imageView)

        when (macaron_id) {
            1000 -> macaron_img.setImageResource(R.drawable.macaron_black)
            1001 -> macaron_img.setImageResource(R.drawable.macaron_blue)
            1002 -> macaron_img.setImageResource(R.drawable.macaron_green)
            1003 -> macaron_img.setImageResource(R.drawable.macaron_navy)
            1004 -> macaron_img.setImageResource(R.drawable.macaron_pink)
            1005 -> macaron_img.setImageResource(R.drawable.macaron_red)
            1006 -> macaron_img.setImageResource(R.drawable.macaron_yellow)
            else -> {
                macaron_img.setImageResource(R.drawable.macaron_black)
            }
        }
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShowProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShowProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}