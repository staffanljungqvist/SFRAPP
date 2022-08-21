package com.levento.sfrapp.feature_news.domain.repository

import com.levento.sfrapp.feature_news.domain.model.Article
import com.levento.sfrapp.common.domain.model.NetworkResponse

interface NewsRepository {

    suspend fun getNews(): NetworkResponse<List<Article>, Exception>

}