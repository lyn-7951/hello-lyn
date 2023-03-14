package com.example.android.marsphotos.network

data class MarsPhoto (
     val code:Int,
     val msg:String,
     val data:List<InnerMarsPhoto>
        )

data class InnerMarsPhoto(
    val id: Int,
    val storeId:String,
    val imagePath: String,
    val checkProjectStoreId:Int,
    val checkProjectStoreUploadId:Int,
    val state:Int,
    val type:Int,
    val typeDetail:Int,
    val checkIsUpdateStoreImage:Int,
    val creator:String,
    val createTime:String,
    val storeName:String?,
    val projectName:String?

)
