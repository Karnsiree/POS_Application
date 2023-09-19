package com.stamford.pos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider.getUriForFile
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.File

class SettingActivity: AppCompatActivity(){
    private val TAG = "SettingActivity"
    private var imageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Activity",null).show()
//        }

        val btnSearchContact = findViewById<Button>(R.id.search_contact_btn)
        // set on-click listener
        btnSearchContact.setOnClickListener {
            val intent = Intent(this, SearchContactActivity::class.java)
            startActivity(intent)
        }

        val takePic = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Alert Title")
                builder.setMessage("registerForActivityResult() successful.")
                builder.setPositiveButton("Yes") { dialog, which ->
                    Toast.makeText(this, "YES", Toast.LENGTH_SHORT).show()

                    builder.setNegativeButton("No") { dialog, which ->
                        Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show()
                    }
                    builder.setNeutralButton("Maybe") { dialog, which ->
                        Toast.makeText(this, "MAYBE", Toast.LENGTH_SHORT).show()
                    }

                    val linearLayout = findViewById<LinearLayout>(R.id.image_panel_ll)
                    val factor: Float = linearLayout.context.resources.displayMetrics.density
//                    val width = (linearLayout.width * factor * 0.5)
//                    val height = (linearLayout.height * factor * 0.3)
                    val width = (linearLayout.width * factor)
                    val height = (linearLayout.height * factor)

                    val imageView = ImageView(this)
                    imageView.layoutParams =
                        LinearLayout.LayoutParams(width.toInt(), height.toInt()) //pixels
                    imageView.setImageURI(imageUri)
                    linearLayout?.addView(imageView)
                }
                builder.show()

            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Alert Title")
                builder.setMessage("registerForActivityResult() failed.")

                builder.setPositiveButton("Yes") { dialog, which ->
                    Toast.makeText(this, "YES", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show()
                }
                builder.setNeutralButton("Maybe") { dialog, which ->
                    Toast.makeText(this, "MAYBE", Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }
        }

        //get reference to button
        val uploadDailyReportBtn = findViewById<Button>(R.id.upload_daily_report_button)
        //set on-click listener
        uploadDailyReportBtn.setOnClickListener {
            val imagePath: File = File(getExternalFilesDir(null), "my_images")
            imagePath.mkdirs()
            val newFile = File(imagePath, "img_" + System.currentTimeMillis() + ".jpg")
            val imgUri: Uri =
                getUriForFile(this@SettingActivity, "com.stamford.pos.fileprovider", newFile)
            this.imageUri = imgUri
            takePic.launch(imgUri)
        }

        //get reference to button
        val uploadImageToRemoteServerBtn = findViewById<Button>(R.id.upload_image_to_remote_server_btn)
        //set on-click listener
        uploadImageToRemoteServerBtn.setOnClickListener {
            Log.i(TAG,"Submit Image To Remote Server...")
            //Get orders from local database
            GlobalScope.launch {
                //The return value is Deferred<List<Order>>
                //Deferred value is a non-blocking cancellable future - it is a Job with a result


                    // Instantiate the RequestQueue.
                    val url = "http://10.0.2.2/ITE343/pos_api/public/image"

                    val params = JSONObject()
                    params.put("image_id", "0")
                    params.put("image_url", "$imageUri")
//                    params.put("order_local_id", "${imageUri}")
//                    params.put("branch_id", "${order.branchID}")
//                    params.put("staff_id", "${order.staffID}")

                    // Request a string response from the provided URL.
                    val jsonRequest = JsonObjectRequest(
                        Request.Method.POST, url, params, { response ->
                            // Display the response string.
                            Log.i(TAG, "Response is: $response")
                        },
                        {
                            Log.i(TAG, "That didn't work! Error is: $it")
                        }
                    )

                    // Volley request policy, only one time request to avoid
                    // duplicate transaction
                    jsonRequest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        // 0 means no retry
                        0,  // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                        1f) // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT

                    // Add the volley post request to the request queue
                    VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)
            }
        }


        val submitOrderToRemoteServerBtn = findViewById<Button>(R.id.submit_orders_to_remote_server_btn)
        // set on-click listener
        submitOrderToRemoteServerBtn.setOnClickListener {
            Log.i(TAG,"Submit Order To Remote Server...")
            //Get orders from local database
            GlobalScope.launch {
                //The return value is Deferred<List<Order>>
                //Deferred value is a non-blocking cancellable future - it is a Job with a result
                val localOrders = getOrdersFromLocalDBAsync()

                Log.i(TAG, "Order:")
                for (order in localOrders.await()) {
                    Log.i(
                        TAG, "Order ID = ${order.uid}, " +
                                "Branch ID = ${order.branchID}, " +
                                "Staff ID = ${order.staffID}"
                    )

                    // Instantiate the RequestQueue.
                    val url = "http://10.0.2.2/ITE343/pos_api/public/orders"

                    val params = JSONObject()
                    params.put("order_local_id", "${order.uid}")
                    params.put("branch_id", "${order.branchID}")
                    params.put("staff_id", "${order.staffID}")

                    // Request a string response from the provided URL.
                    val jsonRequest = JsonObjectRequest(
                        Request.Method.POST, url, params, { response ->
                            // Display the response string.
                            Log.i(TAG, "Response is: $response")
                        },
                        {
                            Log.i(TAG, "That didn't work! Error is: $it")
                        }
                    )

                    // Volley request policy, only one time request to avoid
                    // duplicate transaction
                    jsonRequest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        // 0 means no retry
                        0,  // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                        1f) // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT

                    // Add the volley post request to the request queue
                    VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)
                }
            }
        }

        // Get a refence to retrieve button
        val retrieveOrderToRemoteServerBtn = findViewById<Button>(R.id.retrieve_orders_from_remote_server_btn)
        // set on-click listener
        retrieveOrderToRemoteServerBtn.setOnClickListener {
            Log.i(TAG,"Retrieve Order To Remote Server...")
            // We should create a coroutine to run our network operation in
            // another thread and not on UI thread
            GlobalScope.launch {
                // This is the URL of our RESTful Web API - GET orders
                val url = "http://10.0.2.2/ITE343/pos_api/public/orders"
                val jsonRequest = JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null, // We don’t need to pass values in a GET method
                    { response ->
                        // Display the first 500 characters of the response string.
                        Log.i(TAG, "Response: %s".format(response.toString()))

                        for (i in 0 until response.length()) {
                            val order = response.getJSONObject(i.toString())
                            // Your code to perform operations on retrieved data
                            // should be here
                            Log.i(
                                TAG,
                                "Order $i = ${order.get("id")}, branch ID is = ${order.get("branch_id")}"
                            )
                        }
                    },
                    {
                        Log.i(TAG, "That didn't work! Error is: $it")
                    }
                )

                // Volley request policy, only one time request to avoid
                // duplicate transaction
                // 0 means no retry
                // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                jsonRequest.retryPolicy =
                    DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
                // Add the volley post request to the request queue
                VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)
            }
        }

        //get reference to button
        val orderManagerBtn = findViewById<Button>(R.id.order_manager_btn)
        //set on-click listener
        orderManagerBtn.setOnClickListener {
            Toast.makeText(this@SettingActivity, "Order Manager", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@SettingActivity, OrderManagerActivity::class.java)
            startActivity(intent)
        }
    }

    // Returns a CoroutineScope
    private fun getOrdersFromLocalDBAsync() = GlobalScope.async {
        val db = POSAppDatabase.getInstance(applicationContext)
        db.orderDao().getAll()
    }
}
