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
package com.example.android.sports.model

import android.util.Log
import com.example.android.sports.R

/**
 * 数据类
 * 包含要在体育项目列表 Recyclerview 的每一行中显示的数据。
 */
data class Sport(
    var id: Int,
    var titleResourceId: Int,
    var subTitleResourceId: Int,
    val imageResourceId: Int,
    val sportsImageBanner: Int,
    var newsDetails: Int = R.string.sports_news_detail_text


){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sport

        Log.e("bb",""+Sport(id,titleResourceId,subTitleResourceId,imageResourceId,sportsImageBanner,newsDetails))

        if (titleResourceId!= other.titleResourceId) return false

        return true
    }

    override fun hashCode(): Int {
        return titleResourceId
    }



    override fun toString(): String {
        Log.e("bb","执行了吗？")
        return "Sport(titleResourceId=$titleResourceId)"
    }


}