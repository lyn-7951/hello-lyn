package com.example.android.unscramble.ui.game

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TtsSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private val _score =MutableLiveData(0)  //得分
    val score : LiveData<Int>
        get() = _score
    private var _currentWordCount=MutableLiveData(0)//单词数
    val currentWordCount:  LiveData<Int>
        get() = _currentWordCount

//    MutableLiveData 是 LiveData 的可变版本，也就是说，其中存储的数据的值是可以更改的。
//    将 _currentScrambledWord 的变量类型更改为 val，因为 LiveData/MutableLiveData 对象的值将保持不变，且只有该对象中存储的数据会发生更改。
    private val _currentScrambledWord= MutableLiveData<String>() //当前乱序词
//    后备属性【var转换为val】,替换getter方法，返回只读版本的数据
//    val currentScrambledWord: LiveData<String>
//        get() = _currentScrambledWord

//    无障碍模式，当前乱序词一个一个读
val currentScrambledWord: LiveData<Spannable> = Transformations.map(_currentScrambledWord) {
    if (it == null) {
        SpannableString("")
    } else {
        val scrambledWord = it.toString()
        val spannable: Spannable = SpannableString(scrambledWord)
        spannable.setSpan(
            TtsSpan.VerbatimBuilder(scrambledWord).build(),
            0,
            scrambledWord.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannable
    }
}


    private var wordsList: MutableList<String> = mutableListOf()//所有单词
    private lateinit var currentWord: String//当前正确的单词


    private fun getNextWord() {
        currentWord = allWordsList.random()//随机选一个单词
        val tempWord = currentWord.toCharArray()//打乱单词的顺序，currentWord留着对比猜的是否是正确的
        tempWord.shuffle()
//        乱序词与原单词不同。
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

//        检查某个单词是否已用过。如果 wordsList 包含 currentWord，就调用 getNextWord()。如果不包含，就使用新打乱字母顺序的单词更新
//        检查这个单词是否出现过
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord.value = String(tempWord)

//            ++_currentWordCount.value
            _currentWordCount.value = (_currentWordCount.value)?.inc()//自增

            wordsList.add(currentWord)
        }
    }

    /*从列表中获取下一个单词，如果单词数少于 MAX_NO_OF_WORDS，就返回 true*/
    fun nextWord(): Boolean {
        return if (_currentWordCount .value!!< MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else {
            false
        }
    }

    /*计算分*/
    private fun increaseScore() {
//        _score.value += SCORE_INCREASE
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

//    猜的是否正确，正确算分
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    /*将得分和单词数设为 0*/
    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }
    /*init:初始化代码块
    * 此代码块将于首次创建和初始化对象实例时运行。*/
    init {
        getNextWord()
    }



}