package kd.dhyani.quotesapp.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteQuoteViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).favoriteQuoteDao()
    private val repository = QuoteRepository(dao)

    val favorites: StateFlow<List<QuoteEntity>> = repository.allFavorites.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
    )

    fun toggleFavorite(title: String, writer: String) {
        viewModelScope.launch {
            val existing = dao.getQuoteByTitleAndWriter(title, writer)
            if (existing != null) {
                dao.delete(existing)
            } else {
                dao.insert(QuoteEntity(title = title, writer = writer))
            }
        }
    }

    suspend fun isFavorite(title: String, writer: String): Boolean {
        return dao.getQuoteByTitleAndWriter(title, writer) != null
    }
}
