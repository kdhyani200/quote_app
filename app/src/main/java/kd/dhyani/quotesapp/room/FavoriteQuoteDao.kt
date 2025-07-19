package kd.dhyani.quotesapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(quote: QuoteEntity)

    @Delete
    suspend fun delete(quote: QuoteEntity)

    @Query("SELECT * FROM favorite_quotes")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM favorite_quotes WHERE title = :title AND writer = :writer LIMIT 1")
    suspend fun getQuoteByTitleAndWriter(title: String, writer: String): QuoteEntity?
}
