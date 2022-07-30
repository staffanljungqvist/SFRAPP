package com.levento.sfrapp.screens

import MainViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.levento.sfrapp.screens.articledetail.ArticleDetailViewModel

@Composable
fun ArticleDetailScreen(
    viewModel: MainViewModel
) {

    val article by remember { viewModel.currentArticle }

/*    LaunchedEffect(key1 = articleTitle) {
        viewModel.getArticle(articleTitle)
    }*/

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = article.title ?: "Missing article")
    }
}
