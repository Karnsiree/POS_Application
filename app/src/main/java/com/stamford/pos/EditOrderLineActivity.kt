package com.stamford.pos

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stamford.pos.databinding.ActivityEditOrderlineBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EditOrderLineActivity: AppCompatActivity() {
    private val TAG = "EditOrderLineActivity"
    private lateinit var binding: ActivityEditOrderlineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditOrderlineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val updatebtn = findViewById<Button>(R.id.edit_orderline_update_btn)
        val orderid_textview = findViewById<TextView>(R.id.orderIDTextView)
        val productid_textview = findViewById<TextView>(R.id.ProductIDTextView)
        val productprice_userinput = findViewById<TextView>(R.id.PriceEditText)
        val productquantity_userinput = findViewById<TextView>(R.id.QuantityEditText)
        val id = intent.getLongExtra("order_id",0)
        val productid = intent.getLongExtra("product_id",0)
        val price = intent.getIntExtra("product_price",0)
        val quantity = intent.getIntExtra("product_quantity",0)

        orderid_textview.text = id.toString()
        productid_textview.text = productid.toString()
        productprice_userinput.text = price.toString()
        productquantity_userinput.text = quantity.toString()

        val updatedproductprice = productprice_userinput.text.toString().toInt()
        val updatedproductquantity = productquantity_userinput.text.toString().toInt()


        updatebtn.setOnClickListener {
//            Toast.makeText(this,productprice_userinput.text.toString(),Toast.LENGTH_SHORT).show()

            val inputprice = productprice_userinput.text.toString().toInt()
            val inputquantity = productquantity_userinput.text.toString().toInt()

            GlobalScope.launch {
                val db = POSAppDatabase.getInstance(this@EditOrderLineActivity)
                //NOTE: .toString().toInt() is just for conversion purpose to put into the update function

                val changedorderline = OrderLine(null,id,productid,inputprice,inputquantity)

                db.orderLineDao().update(changedorderline)

                Log.i("EditOrderActivity",db.orderLineDao().getAll().toString())
                Log.i("EditOrderActivity",id.toString())
                Log.i("EditOrderActivity",productid.toString())
                Log.i("EditOrderActivity",productprice_userinput.toString())
                Log.i("EditOrderActivity",productquantity_userinput.toString())
            }
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Update Operation")
            alertDialog.setMessage("Update succeed")

            alertDialog.setNeutralButton("Updated"){
                    dialog, which ->
                val intent = Intent(this,OrderManagerActivity::class.java)
                startActivity(intent)
                finishActivity(0)
            }
            Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show()
            alertDialog.show()
        }

    }
}