package com.example.words3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), A {
    private var letterListFragment:LetterListFragment=LetterListFragment()
    private var wordListFragment:WordListFragment= WordListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        letterListFragment.a=this
        supportFragmentManager.beginTransaction().replace(R.id.letter_fragment,letterListFragment).commit()
    }

    override fun myReplace() {
        if(num==1&&wordListFragment.isVisible){
            supportFragmentManager.beginTransaction().replace(R.id.letter_fragment,letterListFragment).commit()
        }else if(num==2 && letterListFragment.isVisible){
            supportFragmentManager.beginTransaction().replace(R.id.letter_fragment,wordListFragment).commit()
        }
    }

    override var num: Int=1
}

