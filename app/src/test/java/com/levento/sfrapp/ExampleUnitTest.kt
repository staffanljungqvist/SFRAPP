package com.levento.sfrapp

import MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.levento.sfrapp.repository.NewsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getArticles() {
        val newsRepository = NewsRepository()

        GlobalScope.launch {
            val result = newsRepository.getNews()
            assertEquals(4, 2 + 2)
        }


    }

    @Test
    fun testViewModel() {
        val viewModel = MainViewModel()
        viewModel.getNews()
        assertEquals(4, 2 + 2)
    }

}