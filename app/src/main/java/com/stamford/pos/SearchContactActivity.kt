package com.stamford.pos

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.stamford.pos.R
import java.util.jar.Manifest
import androidx.core.view.MenuItemCompat
import android.R.menu







class SearchContactActivity: AppCompatActivity() {
    val column = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID,
        ).toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_contact)

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,Array(1) {android.Manifest.permission.READ_CONTACTS},111)
        } else
            readContact()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            readContact()
    }

    private fun readContact() {
        var from = listOf<String>(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER).toTypedArray()
        var to = intArrayOf(android.R.id.text1,android.R.id.text2)
        var result = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, column,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        var adapter = SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,result,from,to,0)

        val contactList = findViewById<ListView>(R.id.listview1)
        val Search = findViewById<SearchView>(R.id.searchView)

        contactList.adapter = adapter

        Search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
            return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var result = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    column,"${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} LIKE?",Array(1){"%$p0%"},ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                adapter.changeCursor(result)
                return false
            }
        })
    }
}