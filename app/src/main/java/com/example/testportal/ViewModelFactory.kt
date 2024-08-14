package com.example.testportal

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testportal.databinding.ActivityMainBinding
import com.example.testportal.ui.firstScreen.FirstScreenViewModel
import com.example.testportal.ui.secondScreen.SecondScreenViewModel
import com.example.testportal.ui.thirdScreen.ThirdScreenViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FirstScreenViewModel::class.java) -> {
                FirstScreenViewModel() as T
            }

            modelClass.isAssignableFrom(SecondScreenViewModel::class.java) -> {
                SecondScreenViewModel() as T
            }

            modelClass.isAssignableFrom(ThirdScreenViewModel::class.java) -> {
                ThirdScreenViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}