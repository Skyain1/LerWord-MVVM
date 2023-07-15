package com.example.lerword_mvvm.App.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lerword_mvvm.App.Model.Database.WordsRepository
import com.example.lerword_mvvm.App.Model.Word
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by Skyain1 on 13.07.2023.
 */


class MainViewModel(val repository: WordsRepository) : ViewModel() {
    var ResultWords = MutableLiveData<ArrayList<Word>>()
    private var words = ArrayList<Word>()
    private var fullwords = ArrayList<Word>()
    private var curword = 0
    var knowWords = MutableLiveData<Int>()
    private var swipe = 0

    init {
        ResultWords.value = ArrayList<Word>()
        setTenWords()
        knowWords.value = 0
        Log.d("AAA", "MainViewModel Started")
    }

    fun cardSwipe(direction: Direction?) {
        if (direction == Direction.Right) {
            if (swipe == 0) {
                words[words.size - 1] = words[0]
                ResultWords.value!![words.size - 1] = words[0]
                swipe++
            }
        } else if (direction == Direction.Left) {
            updateKnow(words[0])
        }
        iteration()

    }

    fun setTenWords() {
        curword = 0
        swipe = 0
        words.clear()
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                fullwords = repository.getWords()
            }
            repeat(11) {
                val word = fullwords.find { !it.know && !words.contains(it) }
                if (word != null) {
                    words.add(word)
                }
            }
            ResultWords.value = ArrayList(words)
        }
    }

    private fun updateKnow(word: Word) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.setKnow(word)
            }
            knowWords.value = knowWords.value!! + 1
        }
    }

    fun resetKnowWords() {
        knowWords.value = 0
    }

    private fun updateCount(word: Word) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.updateField(word)
            }
        }
    }

    fun RefreshWords() {
        ResultWords.value = ArrayList(words)
    }

    private fun iteration() {
        updateCount(words[0])
        words.removeFirst()
        curword++
        if (curword == 10) {
            Log.d("AAA", "10 words")
            setTenWords()
        }
    }
}