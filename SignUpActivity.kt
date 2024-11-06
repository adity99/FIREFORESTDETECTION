package com.example.firedetection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var signupUsernameEditText: EditText
    private lateinit var signupPasswordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize views
        signupUsernameEditText = findViewById(R.id.signupUsernameEditText)
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText)
        signupButton = findViewById(R.id.signupButton)

        // Set up click listener for the sign-up button
        signupButton.setOnClickListener {
            val username = signupUsernameEditText.text.toString().trim()
            val password = signupPasswordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both a username and password", Toast.LENGTH_SHORT).show()
            } else {
                // Save user details using SharedPreferences
                val sharedPref = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply() // Save data asynchronously

                // Notify user and navigate to login screen
                Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show()

                // Navigate to LoginActivity after sign up
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Finish the activity so the user can't go back
            }
        }
    }
}
