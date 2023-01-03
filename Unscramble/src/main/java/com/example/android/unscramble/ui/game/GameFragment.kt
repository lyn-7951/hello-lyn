/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.example.android.unscramble.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.unscramble.R
import com.example.android.unscramble.databinding.GameFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Fragment where the game is played, contains the game logic.
 */
class GameFragment : Fragment() {

//    by viewModels（）委托初始化
    private val viewModel: GameViewModel by viewModels()//viewmode

    // Binding object instance with access to the views in the game_fragment.xml layout
    private lateinit var binding: GameFragmentBinding

//膨胀布局
    /*首次创建 fragment 时以及每次因任何事件（例如配置更改）而重新创建 fragment 时，都会触发 onCreateView()*/
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
//    视图绑定
//        binding = GameFragmentBinding.inflate(inflater, container, false)
//    数据绑定
        binding=DataBindingUtil.inflate(inflater,R.layout.game_fragment,container,false)
        return binding.root
    }

//    对按钮进行监听并渲染界面
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gameViewModel=viewModel
        binding.maxNoOfWords= MAX_NO_OF_WORDS
        binding.lifecycleOwner=viewLifecycleOwner

        // Setup a click listener for the Submit and Skip buttons.
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }


//    viewLifecycleOwner监视fragment的视图生命周期
//    newWord：新乱序单词
//        viewModel.currentScrambledWord.observe(viewLifecycleOwner
//        ) { newWord ->
//            binding.textViewUnscrambledWord.text = newWord
//        }
//        viewModel.score.observe(viewLifecycleOwner
//        ) { newScore ->
//            binding.score.text =getString(R.string.score,newScore)
//        }
//        viewModel.currentWordCount.observe(viewLifecycleOwner
//        ) { newWordCount ->
//            binding.wordCount.text =getString(R.string.word_count,newWordCount, MAX_NO_OF_WORDS)
//        }
}

    /*
       是 Submit 按钮的点击监听器，此函数用于显示下一个乱序词，清空文本字段，并增加得分和单词数（无需确认玩家猜出的单词是否正确）。
    */
    private fun onSubmitWord() {
        val playWord= binding.textInputEditText.text.toString()
        if(viewModel.isUserWordCorrect(playWord)){
            setErrorTextField(false)
            if (!viewModel.nextWord()) {

                showFinalScoreDialog()
            }
        }else{
            setErrorTextField(true)//单词不正确，显示错误信息
        }

    }

    /*
       是 Skip 按钮的点击监听器
       如为 true，就在屏幕上显示单词并重置文本字段。如为 false，并且本局游戏中再没有剩下的单词，就显示包含最终得分的提醒对话框
     */
    private fun onSkipWord() {

        if (viewModel.nextWord()) {
            setErrorTextField(false)
        } else {
            showFinalScoreDialog()
        }
    }


    /*
          重新开始和结束游戏
     */
    private fun restartGame() {
        viewModel.reinitializeData()
        setErrorTextField(false)
    }

    /*
     * Exits the game.
     */
    private fun exitGame() {
        activity?.finish()
    }

    /*
    清除文本字段内容并重置错误状态，提示单词错误信息
    */
    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true//错误显示
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false//错误不显示
            binding.textInputEditText.text = null//错误信息清除
        }
    }



/*对话框*/
    private fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))//标题
            .setMessage(getString(R.string.you_scored,viewModel.score.value))//设置消息
            .setCancelable(false)//对话框不能点击取消
//                两个参数，一个String一个DialogInterface.OnClickListener()，后者可以用一个 lambda 来表示。
//                尾随λ语法
            .setNegativeButton(getString(R.string.exit)){_,_->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)){_,_->
                restartGame()
            }
            .show()
    }
}
