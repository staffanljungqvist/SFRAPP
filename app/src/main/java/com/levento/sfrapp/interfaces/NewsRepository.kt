package com.levento.sfrapp.interfaces

import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.NetResponse

interface NewsRepository {

    suspend fun getNews(): NetResponse<List<Article>, Exception>

}