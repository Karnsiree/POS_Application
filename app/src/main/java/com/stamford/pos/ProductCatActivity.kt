package com.stamford.pos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.stamford.pos.databinding.ActivityProductCatBinding
import com.stamford.pos.databinding.ActivityProductCrudactivityBinding

class ProductCatActivity : AppCompatActivity() {
    private val TAG = "ProductCatActivity"
    private lateinit var binding: ActivityProductCatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_cat)
        Toast.makeText(this, "Pick a category", Toast.LENGTH_SHORT).show()

        binding = ActivityProductCatBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val btnClickMe = findViewById<Button>(R.id.macaronBtn)
//        // set on-click listener
//        btnClickMe.setOnClickListener {
//            Toast.makeText(this, "Macaron", Toast.LENGTH_SHORT).show()
//            //This code here will be executed whenever the login button is clicked
//            val intent = Intent(this,OrderActivity::class.java)
//            startActivity(intent)
//        }

    }

    fun onClickMacaronsBtn (view: View){
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("CatID", MACARON_CAT_ID)
        startActivity(intent)
    }

    fun onClickDrinksBtn (view: View){
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("CatID", DRINK_CAT_ID)
        startActivity(intent)
    }

    fun onClickDessertsBtn (view: View){
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("CatID", DESSERT_CAT_ID)
        startActivity(intent)
    }

    fun onClickIceCreamsBtn (view: View){
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("CatID", ICECREAM_CAT_ID)
        startActivity(intent)
    }

    fun onClickThaiDishesBtn (view: View){
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("CatID", THAIDISH_CAT_ID)
        startActivity(intent)
    }

    fun onClickFruitsBtn (view: View){
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("CatID", FRUIT_CAT_ID)
        startActivity(intent)
    }
}