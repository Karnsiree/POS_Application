package com.stamford.pos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stamford.pos.databinding.ActivityLoginBinding
import java.nio.charset.Charset
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


class LoginActivity: AppCompatActivity() {
    private val TAG = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("com.stamford.pos.secret_pref", Context.MODE_PRIVATE)

        val isAnyCredentialsSaved = sharedPref.getBoolean("credential_stored_key", false)

        if (isAnyCredentialsSaved) {
            //credentials exists
            val username = sharedPref.getString("username_key", "")
            val passwordBase64 = sharedPref.getString("password_key", "")
            val keyBytesBase64 = sharedPref.getString("pass_key", "")
            val ivBase64 = sharedPref.getString("iv_key", "")

            val password = Base64.decode(passwordBase64,Base64.NO_WRAP)
            val keyBytes = Base64.decode(keyBytesBase64,Base64.NO_WRAP)
            val iv = Base64.decode(ivBase64,Base64.NO_WRAP)

            val ivSpec = IvParameterSpec(iv)

            val keySpec = SecretKeySpec(keyBytes,"AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init (Cipher.DECRYPT_MODE, keySpec , ivSpec)
            val decryptedPassword: ByteArray = cipher.doFinal(password)
            val passwordStr = String(decryptedPassword, Charset.forName("UTF-8"))
            Log.i(TAG,"Decrypted password is = $passwordStr")

            binding.usernameEditText?.setText(username)
            binding.passwordEditText?.setText(passwordStr)
            binding.rememberMeCheckBox?.isChecked = true
        }

        val titleTextViewToSetting = findViewById<TextView>(R.id.edit_orderline_title_textView)
        //go to setting
        titleTextViewToSetting.delay {
            Toast.makeText(this@LoginActivity, "Setting", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, SettingActivity::class.java)
            startActivity(intent)
//            return@setOnLongClickListener true
        }

//        setContentView(R.layout.activity_login)
//        Log.i("Login", "onCreate...") //The log shows when activity was first create
//
//        // get reference to button
//        val btnClickMe = findViewById<Button>(R.id.login_btn)
//        // set on-click listener
//        btnClickMe.setOnClickListener {
//            Toast.makeText(this@LoginActivity, "Karnsiree has clicked the login button", Toast.LENGTH_SHORT).show()
//            //This code here will be executed whenever the login button is clicked
//            val intent = Intent(this, CommandCenterActivity::class.java)
//            startActivity(intent)
//        }

    }

    fun onclick_login_btn (view:View) {
        Log.i(TAG,"Login button click. Command Center UI started")

        if(binding.rememberMeCheckBox?.isChecked) {
            val username = binding.usernameEditText?.text.toString()
            val password = binding.passwordEditText?.text.toString()

            if (username.isNotEmpty()) {
                val sharePref = getSharedPreferences("com.stamford.pos.secret_pref", Context.MODE_PRIVATE)

                val random = SecureRandom()
                val salt = ByteArray(256)
                val passkey = ByteArray(8)
                random.nextBytes(salt)
                random.nextBytes(passkey)

                val pbKeySpec = PBEKeySpec(passkey.toString().toCharArray(), salt, 1324, 256)
                val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
                val keyBytes = secretKeyFactory.generateSecret(pbKeySpec).encoded
                val keySpec = SecretKeySpec(keyBytes, "AES")
                val ivRandom = SecureRandom()
                val iv = ByteArray(16)
                ivRandom.nextBytes(iv)
                val ivSpec = IvParameterSpec(iv)
                val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
                val encrypted = cipher.doFinal(password.toByteArray(Charset.forName("UTF-8"))) // 2

                //Save username and password into shared preferences
                Log.i(TAG, "Remember me is checked, username is = $username, password = $password")
                Log.i(TAG, "Encoded ciphered password is = $encrypted")

                val passwordBase64 = Base64.encodeToString(encrypted, Base64.NO_WRAP)
                val passkeyBase64 = Base64.encodeToString(passkey, Base64.NO_WRAP)
                val keyBytesBase64 = Base64.encodeToString(keyBytes, Base64.NO_WRAP)
                val saltBase64 = Base64.encodeToString(salt, Base64.NO_WRAP)
                val ivBase64 = Base64.encodeToString(iv, Base64.NO_WRAP)

                with(sharePref.edit()) {
                    putString("username_key", username)
                    putString("password_key", passwordBase64)
                    putString("pass_key", keyBytesBase64)
                    putString("iv_key", ivBase64)
                    putBoolean("credential_stored_key", true)
                    apply()
                }
            }
        }
        else {
            val sharedPref = getSharedPreferences("com.stamford.pos.secret_pref", Context.MODE_PRIVATE)

            with(sharedPref.edit()) {
                putBoolean("credential_stored_key", false)
                apply()
            }
        }

        val intent = Intent(this, CommandCenterActivity::class.java)
        Log.i(TAG,"Login process successful, going to start the AddProductActivity")
        startActivity(intent)
    }

    private val longClickDuration = 1000L
    fun View.delay(listener: () -> Unit) {
        setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    handler.postDelayed({ listener.invoke() }, longClickDuration)
                } else if (event?.action == MotionEvent.ACTION_UP) {
                    handler.removeCallbacksAndMessages(null)
                }
                return true
            }
        })
    }

    /*Override onCreate(), onStart(), onRestart(), onResume(), onPause(), onSaveInstanceState(), on-Stop(), onDestroy() of the MainActivity
    and insert a log message inside the methods.Then observe the logs and comment on them. Explain the difference between each of those.*/

    override fun onStart() {
        super.onStart()
        Log.i("Login","onStart...") //The log shows when activity start or launch
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Login","onRestart...") //The log shows after the activity has been stopped or before it being started again
    }

    override fun onResume() {
        super.onResume()
        Log.i("Login","onResume...") //The log shows when the activity will start interacting with the user, it always followed by onPause()
    }

    override fun onPause() {
        super.onPause()
        Log.i("Login","onPause...") //The log shows when activity was being pause by the user / when the activity loses foreground state
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("Login","onSaveInstanceState...") //It was called before placing the activity in such a background state, allowing you to save away any dynamic instance state in your activity into the given Bundle, to be later received in onCreate(Bundle) if the activity needs to be re-created
    }

    override fun onStop() {
        super.onStop()
        Log.i("Login","onStop...") //The log shows when activity is no longer being visible on the screen / when it becomes invisible to the user
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Login","onDestroy...") //The log shows when activity is finishing or temporarily destroying
    }
}


