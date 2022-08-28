package com.levento.sfrapp.domain.repository

import com.levento.sfrapp.domain.model.Article
import com.levento.sfrapp.domain.model.NetworkResponse

interface NewsRepository {

    suspend fun getNews(): NetworkResponse<List<Article>, Exception>

}