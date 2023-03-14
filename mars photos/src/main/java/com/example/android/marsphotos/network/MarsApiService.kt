package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL =
    "https://ceshi-terminal.lingyingdms.com"
private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))//以告知 Retrofit 其可以使用 Moshi 将 JSON 响应转换为 Kotlin 对象
    .baseUrl(BASE_URL)
    .build()

    /*该界面定义 Retrofit 如何使用 HTTP 请求与网络服务器通信*/
    interface MarsApiService {
        @Headers("Authorization:Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkZDQTc2MTY4MzZCQzVCMEM2RTQyODg1NDlDNTZBNEFDN0U2MUQwRUZSUzI1NiIsInR5cCI6ImF0K2p3dCIsIng1dCI6Il9LZGhhRGE4V3d4dVFvaFVuRmFrckg1aDBPOCJ9.eyJuYmYiOjE2NzgwNjYzNDksImV4cCI6MTY3ODA3MzU0OSwiaXNzIjoiaHR0cHM6Ly9jZXNoaS1zc28ubGluZ3lpbmdkbXMuY29tIiwiYXVkIjpbImx5c2FsZXNwbGF0Zm9ybSIsImx5c2FsZXMuaW0uYXBpIiwibHlzYWxlcy5hbmRyb2lkLmFwaSJdLCJjbGllbnRfaWQiOiJqYXZhc2NyaXB0Iiwic3ViIjoiZmM2MzMzM2ItN2MzZi00YWQ4LWEwYjUtMTA0OWIyYTBkN2FhIiwiYXV0aF90aW1lIjoxNjc4MDY2MzQ5LCJpZHAiOiJsb2NhbCIsIm5hbWUiOiLnrqHnkIbogIUiLCJFbXBsb3llZWlkIjoiMjdlOGVjMGQtZmQ3OS00NGJlLThiNmItNDYxZWViMmQwNDZhIiwiVXNlcmlkIjoiZmM2MzMzM2ItN2MzZi00YWQ4LWEwYjUtMTA0OWIyYTBkN2FhIiwiRW1wbG95ZWVuYW1lIjoiJUU3JUFFJUExJUU3JTkwJTg2JUU4JTgwJTg1IiwiVXNlcm5hbWUiOiIlRTclQUUlQTElRTclOTAlODYlRTglODAlODUiLCJSb2xlIjoic3VwZXJhZG1pbiIsImp0aSI6IjA0MzgxRTA0NEI5RjdDRERFNkU0ODQ3MEM0QUEwQ0QxIiwic2lkIjoiRjQ0NzhFMTY4MzRBRjZEODg4QzZENERGRjMwOTJFNDkiLCJpYXQiOjE2NzgwNjYzNDksInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJseXNhbGVzcGxhdGZvcm0iLCJseXNhbGVzcGxhdGZvcm0uaWRlbnRpdHkiLCJseXNhbGVzLmltLmFwaSIsImx5c2FsZXMuYW5kcm9pZC5hcGkiXSwiYW1yIjpbInB3ZCJdfQ.YuO6OP3SEuVNhLlG6ai9g8Xrjc9qCVwJ0PYlXtAjG3SM0NjVOmRuUC8gZX8xxfYJdF8GegjOmjUFDA49xr5qVStMMIMBXHwtqHybdCHOF_MBWXVFU1UhwaEt5yj86masCaOqivh4mvbjxp5V_3zKAf7GK_8SoYtxrqHKFxWvXsOqbOr1fCNsXUXbe1isn49vzM_B3PttqQ5t6Pr049HUgb4TstpDaxAck9V132kyAX-z_TlUkr6NewpojEtPV2aHmVUOQAI6d8EurPDO2VRO3fBSd5CeZHPxEAOMcs4kGgNm9Wd3evVFm262khxSyC1jX2EJAGLe17ZMunnPABclaw")
        @GET("image/checkProject/queryCheckImage?id=17875")
        suspend fun getPhotos():MarsPhoto//以从网络服务中获取响应字符串
    }

    /*定义一个名为 MarsApi 的公共对象，以初始化 Retrofit 服务*/
    object MarsApi{
        val retrofitService : MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)}
    }

