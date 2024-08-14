package com.example.testportal.ui.thirdScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.activity.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testportal.R
import com.example.testportal.ViewModelFactory
import com.example.testportal.databinding.ActivityThirdScreenBinding
import com.example.testportal.network.response.DataItem
import com.example.testportal.ui.adapaterRecyclerView.UserAdapter
import com.example.testportal.ui.secondScreen.SecondScreenActivity

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var adapter: UserAdapter
    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        adapter = UserAdapter()

        setContentView(binding.root)
        getListUser()

        val lac = LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.item_anim))
        lac.delay = 0.4f
        lac.order = LayoutAnimationController.ORDER_NORMAL

        binding.listStory.layoutManager = LinearLayoutManager(this@ThirdScreenActivity)
        binding.listStory.layoutAnimation = lac
        binding.listStory.adapter = adapter
        val username = intent.getStringExtra(SecondScreenActivity.EXTRA_NAME)
        val selectedUser = intent.getStringExtra(SecondScreenActivity.EXTRA_SELECTED_USER)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickListener {

            override fun onItemClick(data: DataItem) {
                val intent = Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_NAME, username)
                intent.putExtra(SecondScreenActivity.EXTRA_SELECTED_USER, data.firstName + " " + data.lastName)
                startActivity(intent)
            }
        })

        binding.backButton.setOnClickListener {
            val intent = Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java)
            intent.putExtra(SecondScreenActivity.EXTRA_NAME, username)
            intent.putExtra(SecondScreenActivity.EXTRA_SELECTED_USER, selectedUser)
            startActivity(intent)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        adapter.addLoadStateListener { loadState ->
            binding.swipeRefreshLayout.isRefreshing = loadState.refresh is LoadState.Loading
        }
    }

    private fun getListUser(){

        viewModel.apiResponse.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun refreshData(){
        adapter.refresh()
    }
}