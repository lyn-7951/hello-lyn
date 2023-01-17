/*
 * Copyright (c) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.sports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.android.sports.databinding.FragmentSportsNewsBinding

/**
 *这是应用中的第二个画面，其中会显示体育新闻的占位文本。
 */
class NewsDetailsFragment : Fragment() {

    private val sportsViewModel: SportsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSportsNewsBinding.inflate(inflater, container, false).root
    }

    /*在 SportsViewModel 的 currentSport 属性上附加一个观察器，
    以便在数据发生变化时自动更新界面。在观察器内部，体育项目标题、图片和新闻会保持最新状态。*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSportsNewsBinding.bind(view)

        // Attach an observer on the currentSport to update the UI automatically when the data
        // changes.
        sportsViewModel.currentSport.observe(this.viewLifecycleOwner) {
            binding.titleDetail.text = getString(it.titleResourceId)
            binding.sportsImageDetail.load(it.sportsImageBanner)
            binding.newsDetail.text = getString(it.newsDetails)
        }
    }
}
