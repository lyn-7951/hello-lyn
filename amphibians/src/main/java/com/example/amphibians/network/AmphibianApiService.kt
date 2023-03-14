/*
 * Copyright (C) 2021 The Android Open Source Project.
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
package com.example.amphibians.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// TODO: Create a property for the base URL provided in the codelab
private const val BASE_URL ="https://developer.android.google.cn/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"

// 使用 Kotlin 适配器工厂构建 Moshi 对象，Retrofit 将用其解析 JSON
private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
// 使用 Moshi 转换器构建 aRetrofit 实例
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface AmphibianApiService {
    @GET("android-basics-kotlin-unit-4-pathway-2-project-api.json")
    // 使用 suspend 函数为每个 API 方法实现 AmphibianApiService 接口（此应用只有一个方法，即用于获取两栖动物列表的方法）。
    suspend fun getAmphibians():List<Amphibian>
}

//创建一个 AmphibianApi 对象，用于公开使用 AmphibianApiService 接口的延迟初始化 Retrofit 服务
object AmphibianApi {
    val retrofitService : AmphibianApiService  by lazy {
        retrofit.create(AmphibianApiService ::class.java)}
}

