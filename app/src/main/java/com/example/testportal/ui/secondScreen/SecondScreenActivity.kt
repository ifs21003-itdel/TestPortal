package com.example.testportal.ui.secondScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.testportal.ViewModelFactory
import com.example.testportal.databinding.ActivitySecondScreenBinding
import com.example.testportal.ui.firstScreen.FirstScreenActivity
import com.example.testportal.ui.thirdScreen.ThirdScreenActivity
import com.example.testportal.ui.thirdScreen.ThirdScreenViewModel

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private var username: String? = ""
    private var selectedUser: String? = ""
    private val viewModel2 by viewModels<SecondScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@SecondScreenActivity, FirstScreenActivity::class.java))
        }

        // Observe LiveData for changes
        viewModel2.username.observe(this, Observer { name ->
            binding.username.text = name ?: "Unknown User"
        })

        viewModel2.selectedUser.observe(this, Observer { user ->
            binding.selectedUser.text = user ?: "No User Selected"
        })

        // Set data from Intent
        username = intent.getStringExtra(EXTRA_NAME)
        selectedUser = intent.getStringExtra(EXTRA_SELECTED_USER)

        if (username != "") {
            viewModel2.username(username)
        }

        if (selectedUser != "") {
            viewModel2.selectData(selectedUser)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this@SecondScreenActivity ,ThirdScreenActivity::class.java)
            intent.putExtra(EXTRA_NAME, username)
            intent.putExtra(EXTRA_SELECTED_USER, selectedUser)
            startActivity(intent)
        }
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_SELECTED_USER = "extra_selected_user"
    }
}