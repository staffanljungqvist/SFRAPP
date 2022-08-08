import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.SFRAPP
import com.levento.sfrapp.data.PlaceHolders
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.models.User
import com.levento.sfrapp.utils.ImageLoadHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepository = getApplication<SFRAPP>().newsRepository
    private val benefitsRepository = getApplication<SFRAPP>().benefitRepository
    private val userRepository = getApplication<SFRAPP>().userRepository
    private val imageLoadHelper = ImageLoadHelper(application)


    private var allBenefits = listOf<Benefit>()

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

            //Laddar ner alla bilder som ska användas till minnet (Exlusive nyhetsbilder samt contentbilder)
            imageLoadHelper.loadAllImages(allBenefits, _populatedCategories.value)

            _isLoading.value = false
        }
    }

    fun checkLoginStatus() {
        viewModelScope.launch {
            if (userRepository.checkLogin()) {
                _isLoggedIn.value = true
                getUserData()
                Log.d(TAG, "Current user: " + user.value.companyName)
            } else {
                _isLoggedIn.value = false
            }
        }
    }

    fun login(email: String?, password: String?) {
        if (validateEmailAndPassword(email, password)) {
            viewModelScope.launch {
                userRepository.loginUser(email!!, password!!)
                checkLoginStatus()
            }
        }
    }

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getUserData()
            user?.let {
                _user.value = it
            }
        }
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.logOut()
            checkLoginStatus()
        }
    }

    //Hämtar och publicerar en lista med artiklar
    private suspend fun getNews() {
        _articles.value = newsRepository.getNews()
    }

    //Hämtar förmåner från firebase.
    private suspend fun getBenefits() {
        var populatedCategories = listOf<BenefitCategory>()

        //hämtar en lista med alla förmåner, samt en lista med alla möjliga kategorier.
        val benefitdata = benefitsRepository.getBenefits()
        val categorydata = benefitsRepository.getCategories()

        if (benefitdata.data != null && categorydata.data != null) {
            Log.d(TAG, "Received " + benefitdata.data?.size + " benefits from repository")

            //
            allBenefits = benefitdata.data!!

            //Skapar en lista av kategorier, där varje kategoriobjekt fylls med en lista av förmåner som tillhör den kategorin
            populatedCategories =
                fillCategoriesWithBenefits(categorydata.data!!, benefitdata.data!!)
        } else {
            Log.d(TAG, "något gick fel")
            populatedCategories =
                fillCategoriesWithBenefits(PlaceHolders.categories, PlaceHolders.benefits)
        }


        _populatedCategories.value = populatedCategories

        //Hämtar ut endast "exclusive" kategorin ur listan med fyllda kategorier
        _exclusiveBenefits.value =
            extractExclusiveBenefits(populatedCategories) ?: listOf<Benefit>()
        Log.d(TAG, "Hämtade exklusiva förmåner: " + _exclusiveBenefits.value)
    }


    //Söker igenom en medskickad lista med kategorier,
    // söker efter kategorin med namnet "Aktuella", och skickar tillbaka en lista med alla förmåner från den kategorin.
    fun extractExclusiveBenefits(categories: List<BenefitCategory>): List<Benefit>? {
        val exclusiveCategory = categories.firstOrNull { it.title!!.contains("Aktuella") }
        if (exclusiveCategory != null) {
            Log.d(TAG, "Det fanns inga aktuella kategorier")
        }
        Log.d(TAG, "Antal aktuella förmåner: " + exclusiveCategory?.benefits?.size)
        return exclusiveCategory?.benefits
    }

    //Tar emot en lista med förmåner, samt en lista med kategorier. Skickar tillbaka en lista med kategorier, där varje
    // kategori-objekt har en lista med förmåner, som är kopplade till den kategorin.
    private fun fillCategoriesWithBenefits(
        categoryList: List<BenefitCategory>,
        benefitList: List<Benefit>
    ): List<BenefitCategory> {

        val categoryListPopulated: MutableList<BenefitCategory> = categoryList.toMutableList()

        for (category in categoryList) {
            for (benefit in benefitList) {
                for (partOfCategory in benefit.category!!) {
                    if (partOfCategory == category.title) {
                        category.benefits.add(benefit)
                    }
                }
            }
        }
        return categoryListPopulated
    }

    fun setCurrentArticle(article: Article) {
        _currentArticle.value = article
    }

    fun setCurrentBenefit(benefit: Benefit) {
        _currentBenefit.value = benefit
    }

    fun getBenefitBackground(): String {
        return "https://firebasestorage.googleapis.com/v0/b/sfr-app.appspot.com/o/affarsnatverka.jpeg?alt=media&token=0afa42de-1503-4e5f-a36a-88bba4c2fe9f"
    }

    private fun validateEmailAndPassword(email: String?, password: String?): Boolean {
        val isValid =
            email != null && email.isNotEmpty() && password != null && password.isNotEmpty()
        Log.d(TAG, isValid.toString())
        return isValid
    }

}

const val TAG = "mydebug"