
package com.example.amphibians.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {

    // 添加 _status，这是一个私有 MutableLiveData 变量，用于保存 AmphibianApiStatus 枚举和各状态的后备属性 status
    private val _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> = _status

    // 为类型为 List<Amphibian> 的两栖动物列表添加一个 amphibians 变量和私有后备属性 _amphibians。
    private val _amphibians=MutableLiveData<List<Amphibian>>()
    val amphibians: LiveData<List<Amphibian>> = _amphibians

    // 为所选的两栖动物对象（类型为 LiveData<Amphibian>）添加一个类型为 MutableLiveData<Amphibian> 的 amphibian 变量和后备属性 amphibian。
    // 此变量将用于存储详情屏幕上显示的所选两栖动物。
    private val _amphibian=MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> = _amphibian


    init {
        getAmphibianList()
    }

    /*定义一个名为 getAmphibianList() 的函数。
    使用 ViewModelScope 启动一个协程，
    在协程内，通过调用 Retrofit 服务的 getAmphibians() 方法执行 GET 请求，以下载两栖动物数据。
    您需要使用 try 和 catch 适当地处理错误。
    在发出请求之前，请将 _status 的值设为 AmphibianApiStatus.LOADING。
    如果请求成功，_amphibians 应设为来自服务器的两栖动物列表，_status 应设为 AmphibianApiStatus.DONE。
    如果发生错误，_amphibians 应设为一个空列表，_status 应设为 AmphibianApiStatus.ERROR。*/
    public fun getAmphibianList() {
//        启动携程
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try{
                _amphibians .value= AmphibianApi.retrofitService.getAmphibians()
                _status.value= AmphibianApiStatus.DONE
            }catch (e:Exception){
                Log.d("bb",""+e.message);
                _status.value=AmphibianApiStatus.ERROR
                _amphibians.value= listOf()
            }


        }

    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        // TODO: Set the amphibian object
        _amphibian.value=amphibian
    }
}
