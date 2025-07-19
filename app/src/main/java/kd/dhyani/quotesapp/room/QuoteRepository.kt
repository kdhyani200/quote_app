package kd.dhyani.quotesapp.room

import kotlinx.coroutines.flow.Flow

class QuoteRepository(private val dao: FavoriteQuoteDao) {

    val allFavorites: Flow<List<QuoteEntity>> = dao.getAllQuotes()

    suspend fun toggleFavorite(quote: QuoteEntity) {
        val existing = dao.getQuoteByTitleAndWriter(quote.title, quote.writer)
        if (existing == null) {
            dao.insert(quote)
        } else {
            dao.delete(existing)
        }
    }

    suspend fun isFavorite(title: String, writer: String): Boolean {
        return dao.getQuoteByTitleAndWriter(title, writer) != null
    }
}
