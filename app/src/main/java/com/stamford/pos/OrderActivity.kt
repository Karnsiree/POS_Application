package com.stamford.pos

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*




class OrderActivity: AppCompatActivity() {
    private val TAG = "OrderActivity"
    private var totalAmount: Int = 0
    private val model: OrderViewModel by viewModels()  //added the view model
    private lateinit var orderlistma:ArrayList<Macaron>
    private lateinit var orderlistdr:ArrayList<Drink>
    private lateinit var orderlistde:ArrayList<Dessert>
    private lateinit var orderlistic:ArrayList<Icecream>
    private lateinit var orderlistth:ArrayList<Thaidish>
    private lateinit var orderlistfr:ArrayList<Fruit>


    companion object{
        var addorremovefromthelist:String?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val total_price_tv = findViewById<TextView>(R.id.total_price_textView)
        // We don’t need to update the UI
      total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)

        // Create the observer which updates the UI.
//      val amountObserver = Observer<Int> { newAmount ->
        //Update the UI, in this case, a TextView.
//      total_price_tv.text = getString(R.string.total_amount_str, newAmount)
//      }


        // Observe the LiveData, passing in this activity as the
        // LifecycleOwner and the observer. 
//        model.getTotalAmount().observe(this,Observer)


        val intent = intent
        val catID = intent.getIntExtra("CatID", totalAmount)

        when (catID) {
//            MACARON_CAT_ID -> {
//                val rvMacarons = findViewById<View>(R.id.rvProductsList) as RecyclerView
//                val rvOrderlist = findViewById<RecyclerView>(R.id.orderlist_lv)
//                rvOrderlist.layoutManager=LinearLayoutManager(this) //set layoutmanage f
//                orderlist = arrayListOf<Macaron>() //create an empty array
//                val orderlistAdapter = OrderListRecyclerViewAdapter(orderlist)
//                rvOrderlist.adapter=orderlistAdapter
//                val macarons = Macaron.createMacaronsList() //Data Creation
//                val adapter = MacaronAdapter(macarons) { macaron ->
            MACARON_CAT_ID -> {
                val rvMacarons = findViewById<View>(R.id.rvProductsList) as RecyclerView
                val rvOrderlist = findViewById<RecyclerView>(R.id.rvOrder)
                rvOrderlist.layoutManager=LinearLayoutManager(this) //set layout manage
                orderlistma = arrayListOf<Macaron>() //create an empty array
                val orderlistAdapter = OrderListRVAdapterMacaron(orderlistma)
                rvOrderlist.adapter=orderlistAdapter
                val macarons = Macaron.createMacaronsList() //Data Creation
                val adapter = MacaronAdapter(macarons) { macaron ->
                    Log.d(TAG,"A row is clicked. Macaron price is ${macaron.price}")

                    totalAmount += macaron.price
                    model.totalAmount.value = model.totalAmount.value?.plus(macaron.price)
                    total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)

                    if(addorremovefromthelist.equals("ADD")){
                        if(orderlistma.contains(macaron)){
                            val index = orderlistma.indexOf(macaron)
                            macaron.quantity+=1
                            orderlistma.set(index,macaron)
                            orderlistAdapter.notifyItemChanged(index)
                        }else{
                            orderlistma.add(macaron)
                            orderlistAdapter.notifyItemChanged(orderlistma.size)
                        }

                    }else if(addorremovefromthelist.equals("REMOVE")) {
                        if (orderlistma.contains(macaron)) {
                            val index = orderlistma.indexOf(macaron)
                            if (macaron.quantity > 1) {
                                macaron.quantity = macaron.quantity - 1
                                orderlistma.set(index, macaron)
                                orderlistAdapter.notifyItemChanged(index)
                            } else {
                                orderlistma.removeAt(index)
                                orderlistAdapter.notifyItemRemoved(index)
                            }
                        }
                    }

                    /*
                    orderlist = {"Yuth","POM","TU","TAMANAS"}
                    for(item : orderlist){
                    println("HERE"+item)
                    }
                    HERE YUTH
                    HERE POM
                    HERE TU
                    HERE TAMANAS

                     */
                    totalAmount=0
                    for(item in orderlistma){
                        Log.i("for each",item.price.toString())
                        totalAmount+=item.price*item.quantity
                    }
                    total_price_tv.text = totalAmount.toString()
                }

                //Using System.nanoTime() function
                val begin = System.nanoTime()
                rvMacarons.adapter = adapter
                rvMacarons.layoutManager = LinearLayoutManager(this)
                val end = System.nanoTime()
                println("Elapsed time in nanoseconds (RecyclerView): ${end-begin}")
                Toast.makeText(this, "Macaron Category", Toast.LENGTH_SHORT).show()
            }
            DRINK_CAT_ID -> {
                val rvDrinks = findViewById<View>(R.id.rvProductsList) as RecyclerView
                val rvOrderlist = findViewById<RecyclerView>(R.id.rvOrder)
                rvOrderlist.layoutManager=LinearLayoutManager(this) //set layout manage
                orderlistdr = arrayListOf<Drink>() //create an empty array
                val orderlistAdapter = OrderListRVAdapterDrink(orderlistdr)
                rvOrderlist.adapter=orderlistAdapter
                val drinks = Drink.createDrinksList() //Data Creation
                val adapter = DrinkAdapter(drinks) { drink ->
                    Log.d(TAG, "A row is clicked. Drink price is ${drink.price}")
                    if (addorremovefromthelist.equals("ADD")) {
                        if (orderlistdr.contains(drink)) {
                            val index = orderlistdr.indexOf(drink)
                            drink.quantity += 1
                            orderlistdr.set(index, drink)
                            orderlistAdapter.notifyItemChanged(index)
                        } else {
                            orderlistdr.add(drink)
                            orderlistAdapter.notifyItemChanged(orderlistdr.size)
                        }

                    } else if (addorremovefromthelist.equals("REMOVE")) {
                        if (orderlistdr.contains(drink)) {
                            val index = orderlistdr.indexOf(drink)
                            if (drink.quantity > 1) {
                                drink.quantity = drink.quantity - 1
                                orderlistdr.set(index, drink)
                                orderlistAdapter.notifyItemChanged(index)
                            } else {
                                orderlistdr.removeAt(index)
                                orderlistAdapter.notifyItemRemoved(index)
                            }
                        }
                    }

                    totalAmount = 0
                    for (item in orderlistdr) {
                        Log.i("for each", item.price.toString())
                        totalAmount += item.price * item.quantity
                    }
                    total_price_tv.text = totalAmount.toString()
                }
                rvDrinks.adapter = adapter
                rvDrinks.layoutManager = LinearLayoutManager(this)
                Toast.makeText(this, "Drink Category", Toast.LENGTH_SHORT).show()
            }
            DESSERT_CAT_ID -> {
                val rvDesserts = findViewById<View>(R.id.rvProductsList) as RecyclerView
                val rvOrderlist = findViewById<RecyclerView>(R.id.rvOrder)
                rvOrderlist.layoutManager=LinearLayoutManager(this) //set layout manage
                orderlistde = arrayListOf<Dessert>() //create an empty array
                val orderlistAdapter = OrderListRVAdapterDessert(orderlistde)
                rvOrderlist.adapter=orderlistAdapter
                val desserts = Dessert.createDessertsList() //Data Creation
                val adapter = DessertAdapter(desserts) { dessert ->
                    Log.d(TAG, "A row is clicked. Drink price is ${dessert.price}")
                    if (addorremovefromthelist.equals("ADD")) {
                        if (orderlistde.contains(dessert)) {
                            val index = orderlistde.indexOf(dessert)
                            dessert.quantity += 1
                            orderlistde.set(index, dessert)
                            orderlistAdapter.notifyItemChanged(index)
                        } else {
                            orderlistde.add(dessert)
                            orderlistAdapter.notifyItemChanged(orderlistde.size)
                        }

                    } else if (addorremovefromthelist.equals("REMOVE")) {
                        if (orderlistde.contains(dessert)) {
                            val index = orderlistde.indexOf(dessert)
                            if (dessert.quantity > 1) {
                                dessert.quantity = dessert.quantity - 1
                                orderlistde.set(index, dessert)
                                orderlistAdapter.notifyItemChanged(index)
                            } else {
                                orderlistde.removeAt(index)
                                orderlistAdapter.notifyItemRemoved(index)
                            }
                        }
                    }

                    totalAmount = 0
                    for (item in orderlistde) {
                        Log.i("for each", item.price.toString())
                        totalAmount += item.price * item.quantity
                    }
                    total_price_tv.text = totalAmount.toString()
                }
                rvDesserts.adapter = adapter
                rvDesserts.layoutManager = LinearLayoutManager(this)
                Toast.makeText(this, "Dessert Category", Toast.LENGTH_SHORT).show()
            }
            ICECREAM_CAT_ID -> {
                val rvIcecreams = findViewById<View>(R.id.rvProductsList) as RecyclerView
                val rvOrderlist = findViewById<RecyclerView>(R.id.rvOrder)
                rvOrderlist.layoutManager=LinearLayoutManager(this) //set layout manage
                orderlistic = arrayListOf<Icecream>() //create an empty array
                val orderlistAdapter = OrderListRVAdapterIcecream(orderlistic)
                rvOrderlist.adapter=orderlistAdapter
                val icecreams = Icecream.createIcecreamsList() //Data Creation
                val adapter = IcecreamAdapter(icecreams) { icecream ->
                    Log.d(TAG, "A row is clicked. Drink price is ${icecream.price}")
                    if (addorremovefromthelist.equals("ADD")) {
                        if (orderlistic.contains(icecream)) {
                            val index = orderlistic.indexOf(icecream)
                            icecream.quantity += 1
                            orderlistic.set(index, icecream)
                            orderlistAdapter.notifyItemChanged(index)
                        } else {
                            orderlistic.add(icecream)
                            orderlistAdapter.notifyItemChanged(orderlistic.size)
                        }

                    } else if (addorremovefromthelist.equals("REMOVE")) {
                        if (orderlistic.contains(icecream)) {
                            val index = orderlistic.indexOf(icecream)
                            if (icecream.quantity > 1) {
                                icecream.quantity = icecream.quantity - 1
                                orderlistic.set(index, icecream)
                                orderlistAdapter.notifyItemChanged(index)
                            } else {
                                orderlistic.removeAt(index)
                                orderlistAdapter.notifyItemRemoved(index)
                            }
                        }
                    }

                    totalAmount = 0
                    for (item in orderlistic) {
                        Log.i("for each", item.price.toString())
                        totalAmount += item.price * item.quantity
                    }
                    total_price_tv.text = totalAmount.toString()
                }
                rvIcecreams.adapter = adapter
                rvIcecreams.layoutManager = LinearLayoutManager(this)
                Toast.makeText(this, "Ice-cream Category", Toast.LENGTH_SHORT).show()
            }
            THAIDISH_CAT_ID -> {
                val rvThaidishes = findViewById<View>(R.id.rvProductsList) as RecyclerView
                val rvOrderlist = findViewById<RecyclerView>(R.id.rvOrder)
                rvOrderlist.layoutManager=LinearLayoutManager(this) //set layout manage
                orderlistth = arrayListOf<Thaidish>() //create an empty array
                val orderlistAdapter = OrderListRVAdapterThaidish(orderlistth)
                rvOrderlist.adapter=orderlistAdapter
                val thaidishes = Thaidish.createThaidishesList() //Data Creation
                val adapter = ThaidishAdapter(thaidishes) { thaidish ->
                    Log.d(TAG, "A row is clicked. Drink price is ${thaidish.price}")
                    if (addorremovefromthelist.equals("ADD")) {
                        if (orderlistth.contains(thaidish)) {
                            val index = orderlistth.indexOf(thaidish)
                            thaidish.quantity += 1
                            orderlistth.set(index, thaidish)
                            orderlistAdapter.notifyItemChanged(index)
                        } else {
                            orderlistth.add(thaidish)
                            orderlistAdapter.notifyItemChanged(orderlistth.size)
                        }

                    } else if (addorremovefromthelist.equals("REMOVE")) {
                        if (orderlistth.contains(thaidish)) {
                            val index = orderlistth.indexOf(thaidish)
                            if (thaidish.quantity > 1) {
                                thaidish.quantity = thaidish.quantity - 1
                                orderlistth.set(index, thaidish)
                                orderlistAdapter.notifyItemChanged(index)
                            } else {
                                orderlistth.removeAt(index)
                                orderlistAdapter.notifyItemRemoved(index)
                            }
                        }
                    }

                    totalAmount = 0
                    for (item in orderlistth) {
                        Log.i("for each", item.price.toString())
                        totalAmount += item.price * item.quantity
                    }
                    total_price_tv.text = totalAmount.toString()
                }

                rvThaidishes.adapter = adapter
                rvThaidishes.layoutManager = LinearLayoutManager(this)
                Toast.makeText(this, "Thai Dish Category", Toast.LENGTH_SHORT).show()
            }
            FRUIT_CAT_ID -> {
                val rvFruits = findViewById<View>(R.id.rvProductsList) as RecyclerView
                val rvOrderlist = findViewById<RecyclerView>(R.id.rvOrder)
                rvOrderlist.layoutManager=LinearLayoutManager(this) //set layout manage
                orderlistfr = arrayListOf<Fruit>() //create an empty array
                val orderlistAdapter = OrderListRVAdapterFruit(orderlistfr)
                rvOrderlist.adapter=orderlistAdapter
                val fruits = Fruit.createFruitsList() //Data Creation
                val adapter = FruitAdapter(fruits) { fruit ->
                    Log.d(TAG, "A row is clicked. Drink price is ${fruit.price}")
                    if (addorremovefromthelist.equals("ADD")) {
                        if (orderlistfr.contains(fruit)) {
                            val index = orderlistfr.indexOf(fruit)
                            fruit.quantity += 1
                            orderlistfr.set(index, fruit)
                            orderlistAdapter.notifyItemChanged(index)
                        } else {
                            orderlistfr.add(fruit)
                            orderlistAdapter.notifyItemChanged(orderlistfr.size)
                        }

                    } else if (addorremovefromthelist.equals("REMOVE")) {
                        if (orderlistfr.contains(fruit)) {
                            val index = orderlistfr.indexOf(fruit)
                            if (fruit.quantity > 1) {
                                fruit.quantity = fruit.quantity - 1
                                orderlistfr.set(index, fruit)
                                orderlistAdapter.notifyItemChanged(index)
                            } else {
                                orderlistfr.removeAt(index)
                                orderlistAdapter.notifyItemRemoved(index)
                            }
                        }
                    }

                    totalAmount = 0
                    for (item in orderlistfr) {
                        Log.i("for each", item.price.toString())
                        totalAmount += item.price * item.quantity
                    }
                    total_price_tv.text = totalAmount.toString()
                }

                rvFruits.adapter = adapter
                rvFruits.layoutManager = LinearLayoutManager(this)
                Toast.makeText(this, "Fruit Category", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val rvMacarons = findViewById<View>(R.id.rvProductsList) as RecyclerView
                val macarons = Macaron.createMacaronsList() //Data Creation
                val adapter = MacaronAdapter(macarons) {
                    Log.d(TAG, "Adapter assigned.")
                }
                rvMacarons.adapter = adapter
                rvMacarons.layoutManager = LinearLayoutManager(this)
                Toast.makeText(this, "Unknown Category", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onclick_submit_order_btn (view: View) {
        Log.d(TAG, "Submit order button clicked. Order is going to be stored.")

        //create a launch coroutine
        GlobalScope.launch {
            //Order 1 Example: branch Id = 5001, Staff Id = 2001
            val order1 = Order(null,5001,2001)
            // Get a reference to database
            val db = POSAppDatabase.getInstance(applicationContext)
            val orderID: Long = db.orderDao().insert(order1)

            // Create two dummy order lines with productID = 1001 and 1005
            for(item in orderlistma){
                val orderline = OrderLine(null,orderID,item.id.toLong(),item.price,item.quantity)
                db.orderLineDao().insertAll(orderline)
            }

        }
    }

    fun onclick_retrieve_orders_btn (view: View) {
        Log.d(TAG, "Retrieve Orders button clicked.")

        //create a launch coroutine
        GlobalScope.launch {
            //Get a reference to database
            val db = POSAppDatabase.getInstance(applicationContext)
            val orders = db.orderDao().getAll()

            Log.i(TAG, "Orders:")
            for(order in orders) {
                Log.i(TAG, "Order ID = ${order.uid}, " + "Branch ID = ${order.branchID} " + "Staff ID = ${order.staffID}")
            }

            Log.i(TAG, "orderLines:")
            val orderLines = db.orderLineDao().getAll()
            for (orderLine in orderLines) {
                Log.i(TAG, "orderLine ID = ${orderLine.uid}, " + "Order ID = ${orderLine.orderID} " + "Product ID = ${orderLine.productID} Product Quantity = ${orderLine.quantity}")
            }
        }
    }
}
