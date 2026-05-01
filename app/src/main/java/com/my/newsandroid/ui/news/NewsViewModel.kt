package com.my.newsandroid.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.newsandroid.domain.model.Article
import com.my.newsandroid.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NewsUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false
)

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    private var currentPage = 1

    init {
        fetchNews()
    }

    fun fetchNextPage() {
        if (_uiState.value.isPaginationLoading || _uiState.value.endReached) return

        viewModelScope.launch {
            _uiState.update { it.copy(isPaginationLoading = true) }
            try {
                val nextArticles = repository.getNews(currentPage + 1)
                if (nextArticles.isEmpty()) {
                    _uiState.update { it.copy(endReached = true, isPaginationLoading = false) }
                } else {
                    currentPage++
                    _uiState.update {
                        it.copy(
                            articles = it.articles + nextArticles,
                            isPaginationLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage, isPaginationLoading = false) }
            }
        }
    }

    private fun fetchNews() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val articles = repository.getNews(currentPage)
                _uiState.update { 
                    it.copy(
                        articles = articles, 
                        isLoading = false,
                        endReached = articles.isEmpty()
                    ) 
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage, isLoading = false) }
            }
        }
    }
}
