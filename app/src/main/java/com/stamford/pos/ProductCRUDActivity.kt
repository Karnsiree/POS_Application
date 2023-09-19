package com.stamford.pos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.ui.AppBarConfiguration
import com.stamford.pos.databinding.ActivityProductCrudactivityBinding

class ProductCRUDActivity : AppCompatActivity() {
    private val TAG = "ProductCRUDActivity"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityProductCrudactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductCrudactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add the first fragment
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ProductFragment>(R.id.product_product_fragmentContainerView)
        }

        // Add the second fragment only if device is xlarge
//        val view: FragmentContainerView? = binding.root.findViewById<FragmentContainerView>(R.id.show_product_fragmentContainerView2)
        val view: FragmentContainerView? = binding.root.findViewById<FragmentContainerView>(R.id.show_product_fragmentContainerView2)
        if (view != null)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
//                add<ShowProductFragment>(R.id.show_product_fragmentContainerView2)
                add<ShowProductFragment>(R.id.show_product_fragmentContainerView2)
            }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_product_crudactivity)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}