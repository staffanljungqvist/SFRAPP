package com.levento.sfrapp.screens

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.TAG
import com.levento.sfrapp.interfaces.BenefitsRepository
import com.levento.sfrapp.interfaces.NewsRepository
import com.levento.sfrapp.interfaces.UserRepository
import com.levento.sfrapp.models.*
import com.levento.sfrapp.utils.populate
import com.levento.sfrapp.utils.sortCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val newsRepository: NewsRepository,
    private val benefitsRepository: BenefitsRepository,
    private val userRepository: UserRepository
        ) : ViewModel() {

  //  private val imageLoadHelper = HtmlImageParser(application)

    private val _signInResponse = mutableStateOf<Response<Boolean>>(Response.Loading)

    private val _user = mutableStateOf(User())
    val user = _user

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn = _isLoggedIn

    private val _articles = mutableStateOf(listOf<Article>())
    val articles = _articles

    private val _currentArticle = mutableStateOf(Article())
    val currentArticle = _currentArticle

    private val _exclusiveBenefits = mutableStateOf(listOf<Benefit>())
    val exclusiveBenefits = _exclusiveBenefits

    private val _populatedCategories = mutableStateOf(listOf<BenefitCategory>())
    val populatedCategories = _populatedCategories

    private val _currentBenefit = mutableStateOf(Benefit())
    val currentBenefit = _currentBenefit


    //Funktioner som körs när appen startar.
    fun load() {
        viewModelScope.launch(Dispatchers.IO) {

            _isLoading.value = true

            //Kollar om en användare är inloggad
            checkLoginStatus()

            //Hämtar nyheterna från ett RSS flöde
            getNews()

            //Hämtar förmånerna från firebase. Delar även in dom i kategorier
            getBenefits()

            _isLoading.value = false
        }
    }


    suspend fun checkLoginStatus() {
        if (userRepository.checkLogin()) {
            _isLoggedIn.value = true

            //Om en användare är inloggad, hämta användarens uppgifter
            getUserData()
            Log.d(TAG, "Current user: " + user.value.companyName)
        } else {
            _isLoggedIn.value = false
        }
    }

    //Hämtar användaruppgifter ( Org nr, giltighetstid etc)
    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getUserData()
            user?.let {
                _user.value = it
            }
        }
    }


    //Hämtar och publicerar en lista med nyhetsartiklar
    private suspend fun getNews() {
        val newsResponse = newsRepository.getNews()
        if (newsResponse.data != null) {
            _articles.value = newsResponse.data!!
        } else {
            Log.d(TAG, "kunde inte hämta nyheter")
        }
    }

    //Hämtar alla förmåner från firebase.
    private suspend fun getBenefits() {

        //hämtar en lista med alla förmåner från firebase. Varje förmån har en String-array med titlar på kategorier den tillhör
        val benefitResponse = benefitsRepository.getBenefits()

        if (benefitResponse.data != null) {

            //Går igenom alla förmåners tillhörande kategorier och skapar en ny lista av typen BenefitCategory för varje unikt kategorinamn som dyker upp.
            val categories = createCategoryList(benefitResponse.data!!)

            //Varje Kategoriobjekt har i sin tur en variabel för en lista med Förmånsojekt.  Dessa listor populeras nu utifrån förmånslistan
            categories.populate(benefitResponse.data!!)

            //Sorterar kategorilistan med kategorin "Aktuella" först, och resterande i bokstavsordning. Den sorterade listan tilldelas State-variabeln.
            _populatedCategories.value = categories.sortCategories()
        } else {
            Log.d(TAG, "något gick fel")
        }
    }


    fun createCategoryList(benefits: List<Benefit>): MutableList<BenefitCategory> {

        val categoryNames = mutableSetOf<String>()
        val categoryList = mutableListOf<BenefitCategory>()

        for (benefit in benefits) {
            if (benefit.category != null) {
                for (category in benefit.category!!) {
                    categoryNames.add(category)
                }
            }
        }
        for (categoryName in categoryNames) {
            categoryList.add(BenefitCategory(title = categoryName))
        }
        return categoryList
    }


    fun setCurrentArticle(article: Article) {
        _currentArticle.value = article
    }

    fun setCurrentBenefit(benefit: Benefit) {
        _currentBenefit.value = benefit
    }

}


