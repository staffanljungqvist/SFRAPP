import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.domain.*
import com.levento.sfrapp.repository.BenefitsRepository
import com.levento.sfrapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "mydebug"

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

  //  private val newsRepository: NewsRepository = NewsRepository()
    private val benefitsRepository: BenefitsRepository = BenefitsRepository()
    private var allBenefits = listOf<Benefit>()

    var loading = mutableStateOf(false)

    private val _benefitsLoaded = mutableStateOf(false)
    var benefitsLoaded = _benefitsLoaded

    private val _articles = mutableStateOf(listOf<Article>())
    val articles = _articles

    private val _exclusiveBenefits = mutableStateOf(listOf<Benefit>())
    val exclusiveBenefits = _exclusiveBenefits

    private val _populatedCategories = mutableStateOf(listOf<BenefitCategory>())
    val populatedCategories = _populatedCategories


    //Funktioner som körs när appen startar.
    init {
   //     getNews()
        getBenefits()
    }

    //Hämtar och publicerar en lista med Article
/*
    fun getNews() {
        viewModelScope.launch {
            val result = newsRepository.getNews()
            val content = result[0].content

            _articles.value = result
        }
    }
*/

    /*
    Hämtar en lista med alla Benefits från repository. Hämtar sedan en lista med alla Category.
    Dessa 2 listor skickas sedan till funktionen "fillCategoriesWithBenefits", som skickar tillbaka en
    lista med Category-object, med vardera en lista med Benefit-objekt. Denna lista publiceras sedan till
    state-variabeln categoriesSorted. En lista skapas och publiceras även för exclusiveBenefits, från funktionen
    extractExclusiveBenefits
     */
    fun getBenefits() {
        _benefitsLoaded.value = false
        viewModelScope.launch {
            var populatedCategories = listOf<BenefitCategory>()
            val benefitdata = benefitsRepository.getAllBenefitsFromFirestore()
            val categorydata = benefitsRepository.getCategoriesFromFirestore()

            benefitdata.data?.get(0)?.let {
                printContent(it)
            }

         //   benefitsRepository.getBenefitFromFirestore()


            if (benefitdata.data != null && categorydata.data != null) {
                Log.d(TAG, "Received " + benefitdata.data?.size + " benefits from repository")
                allBenefits = benefitdata.data!!
                populatedCategories = fillCategoriesWithBenefits(categorydata.data!!, benefitdata.data!!)
            } else {
                Log.d(TAG, "något gick fel")
                populatedCategories =
                    fillCategoriesWithBenefits(PlaceHolders.categories, PlaceHolders.benefits)
            }
            _populatedCategories.value = populatedCategories
            _exclusiveBenefits.value = extractExclusiveBenefits(populatedCategories) ?: listOf<Benefit>()
            Log.d(TAG, "Hämtade exklusiva förmåner: " + _exclusiveBenefits.value)
            _benefitsLoaded.value = true
        }
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

    fun fillCategoriesWithBenefits(
        categoryList: List<BenefitCategory>,
        benefitList: List<Benefit>
    ): List<BenefitCategory> {

        val cateryListPopulated: MutableList<BenefitCategory> = categoryList.toMutableList()

        for (category in categoryList) {
            for (benefit in benefitList) {
                for (partOfCategory in benefit.category!!) {
                    if (partOfCategory == category.title) {
                        category.benefits.add(benefit)
                    }
                }
            }
        }
        return cateryListPopulated
    }

    fun retrieveBenefit(benefitId: String): Benefit? {
        val benefit = allBenefits.find {it.id == benefitId }
        Log.d(TAG, "Received single benefit: " + benefit?.title + " with id: " + benefit?.id)
        return benefit
    }

    fun getBenefitBackground(): String {
        return "https://firebasestorage.googleapis.com/v0/b/sfr-app.appspot.com/o/affarsnatverka.jpeg?alt=media&token=0afa42de-1503-4e5f-a36a-88bba4c2fe9f"
    }

    //Används endast i testsyfte
   private fun printContent(benefit: Benefit) {
        Log.d("parser", "Benefit title: " + benefit.title + "benefit content: " + benefit.content)
    }
}