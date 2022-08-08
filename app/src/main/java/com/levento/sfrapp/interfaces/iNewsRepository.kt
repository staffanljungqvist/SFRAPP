package com.levento.sfrapp.interfaces

import com.levento.sfrapp.models.Article

interface iNewsRepository {

    suspend fun getNews(): List<Article>

}