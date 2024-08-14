package com.example.testportal.ui.firstScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.testportal.R
import com.example.testportal.ViewModelFactory
import com.example.testportal.databinding.ActivityMainBinding
import com.example.testportal.ui.secondScreen.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<FirstScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.palindromeCheck.setOnClickListener {
            var input = binding.palindromeInput.text.toString()
            if (input != ""){
                if (viewModel.checkPalindrome(input)) {
                    binding.palindromeMessage.text = getString(R.string.successPalindrome)
                    binding.palindromeMessage.setTextColor(ContextCompat.getColor(this, R.color.white))
                    binding.palindromeMessage.visibility = View.VISIBLE
                } else {
                    binding.palindromeMessage.text = getString(R.string.failedPalindrome)
                    binding.palindromeMessage.setTextColor(ContextCompat.getColor(this, R.color.red))
                    binding.palindromeMessage.visibility = View.VISIBLE
                }
            } else {
                binding.palindromeMessage.text = getString(R.string.fill_input_palindrome)
                binding.palindromeMessage.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.palindromeMessage.visibility = View.VISIBLE
            }
        }

        binding.next.setOnClickListener {
            var input = binding.nameInput.text.toString()
            if (input != ""){
                val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_NAME, binding.nameInput.text.toString())
                startActivity(intent)
            } else {
                binding.palindromeMessage.text = getString(R.string.fill_input_name)
                binding.palindromeMessage.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.palindromeMessage.visibility = View.VISIBLE
            }
        }

//        window.apply {
//            // Set the status bar to transparent
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            statusBarColor = Color.TRANSPARENT
//        }
    }

    private fun makeToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}