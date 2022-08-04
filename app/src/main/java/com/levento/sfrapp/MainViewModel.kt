import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import com.levento.sfrapp.SFRAPP
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.models.PlaceHolders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepository = getApplication<SFRAPP>().newsRepository
    private val benefitsRepository = getApplication<SFRAPP>().benefitRepository
    private val imageLoader = getApplication<SFRAPP>().imageLoader

    private var allBenefits = listOf<Benefit>()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

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
            getNews()
            getBenefits()
            loadAllImages(allBenefits, _populatedCategories.value)
            _isLoading.value = false

        }
    }

    //Hämtar och publicerar en lista med artiklar
    private suspend fun getNews() {
        _articles.value = newsRepository.getNews()
    }

    /*
    Hämtar en lista med alla Benefits från repository. Hämtar sedan en lista med alla Category.
    Dessa 2 listor skickas sedan till funktionen "fillCategoriesWithBenefits", som skickar tillbaka en
    lista med Category-object, med vardera en lista med Benefit-objekt. Denna lista publiceras sedan till
    state-variabeln categoriesSorted. En lista skapas och publiceras även för exclusiveBenefits, från funktionen
    extractExclusiveBenefits
     */
    private suspend fun getBenefits() {
        var populatedCategories = listOf<BenefitCategory>()
        val benefitdata = benefitsRepository.getAllBenefitsFromFirestore()
        val categorydata = benefitsRepository.getCategoriesFromFirestore()

        if (benefitdata.data != null && categorydata.data != null) {
            Log.d(TAG, "Received " + benefitdata.data?.size + " benefits from repository")
            allBenefits = benefitdata.data!!
            populatedCategories =
                fillCategoriesWithBenefits(categorydata.data!!, benefitdata.data!!)
        } else {
            Log.d(TAG, "något gick fel")
            populatedCategories =
                fillCategoriesWithBenefits(PlaceHolders.categories, PlaceHolders.benefits)
        }
        _populatedCategories.value = populatedCategories
        _exclusiveBenefits.value =
            extractExclusiveBenefits(populatedCategories) ?: listOf<Benefit>()
        Log.d(TAG, "Hämtade exklusiva förmåner: " + _exclusiveBenefits.value)
    }

    /*
    Söker igenom en medskickad lista med Category, Hittar elementet med Aktuella förmåner, som skickas tillbaka som en ny lista av benefits"
     */
    fun extractExclusiveBenefits(categories: List<BenefitCategory>): List<Benefit>? {
        val exclusiveCategory = categories.firstOrNull { it.title!!.contains("Aktuella") }
        if (exclusiveCategory != null) {
            Log.d(TAG, "Det fanns inga aktuella kategorier")
        }
        Log.d(TAG, "Antal aktuella förmåner: " + exclusiveCategory?.benefits?.size)
        return exclusiveCategory?.benefits
    }

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

    suspend fun loadAllImages(benefits: List<Benefit>, categories: List<BenefitCategory>) {

        for (benefit in benefits) {
            benefit.imageURL?.let {
                benefit.image = loadImage(benefit.imageURL!!)
            }
        }

        Log.d("initload", "loadAllImages är färdig med förmånerna")

        for (category in categories) {
            category.imageURL?.let {
                category.image = loadImage(category.imageURL!!)
            }
        }
        Log.d("initload", "loadAllImages är färdig med kategorierna")
    }

    suspend fun loadImage(imageUrl: String): ImageResult {
        val request = ImageRequest.Builder(getApplication())
            .data(imageUrl)
            // Optional, but setting a ViewSizeResolver will conserve memory by limiting the size the image should be preloaded into memory at.
            .build()
        return imageLoader.execute(request)
    }

}

const val TAG = "mydebug"