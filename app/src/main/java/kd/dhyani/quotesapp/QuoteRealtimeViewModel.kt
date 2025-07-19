package kd.dhyani.quotesapp

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuoteRealtimeViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    private val database = FirebaseDatabase.getInstance().getReference("quotes")
    private val _quotes = mutableStateListOf<Quote>()
    val quotes: List<Quote> = _quotes

    init {
        fetchQuotes()
    }

    fun fetchQuotes() {
        _isLoading.value = true
        _hasError.value = false

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _quotes.clear()
                if (!snapshot.exists() || !snapshot.hasChildren()) {
                    _hasError.value = true
                } else {
                    for (child in snapshot.children) {
                        val quote = child.getValue(Quote::class.java)
                        quote?.let { _quotes.add(it) }
                    }
                    _hasError.value = _quotes.isEmpty()
                }
                _isLoading.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("QuoteViewModel", "onCancelled: ${error.message}")
                _hasError.value = true
                _isLoading.value = false
            }
        })

        android.os.Handler().postDelayed({
            if (_quotes.isEmpty()) {
                _hasError.value = true
            }
            _isLoading.value = false
        }, 4000)
    }

}
