package com.levento.sfrapp

import com.levento.sfrapp.data.PlaceHolders
import com.levento.sfrapp.data.repository.NewsRepositoryImpl
import com.levento.sfrapp.models.BenefitCategory
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
        val newsRepository = NewsRepositoryImpl()

        GlobalScope.launch {
            val result = newsRepository.getNews()
            assertEquals(4, 2 + 2)
        }


    }

    @Test
    fun testCategorySort() {

        val categoriesSortedCorrect = listOf(
            BenefitCategory(title = "Aktuellt"),
            BenefitCategory(title = "Administration"),
            BenefitCategory(title = "Fordon"),
            BenefitCategory(title = "Elektronik")
        )

        val categoryList = PlaceHolders.categories.toMutableList()
      //  val sortedList = categoryList.sortCategories()

     //   assertEquals(categoriesSortedCorrect, sortedList)
    }

}