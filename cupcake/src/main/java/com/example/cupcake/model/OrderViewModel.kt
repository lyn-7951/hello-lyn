package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00//每个纸杯蛋糕价格
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00//当天取货，订单价格另加 $3.00。
class OrderViewModel:ViewModel() {
    private val _quantity = MutableLiveData<Int>()//订购数量
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()//纸杯蛋糕口味
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()//取货日期
    val date: LiveData<String> = _date

    /*价格格式*/
    private val _price = MutableLiveData<Double>()//价格
    val price: LiveData<String> = Transformations.map(_price){//转换函数：转换类型
        NumberFormat.getCurrencyInstance().format(it)//getCurrencyInstance() 方法将价格转换为本地货币格式
    }

    /*更新属性的方法*/
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }



    /*检查是否已设置订购的纸杯蛋糕口味*/
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    /*取货日期列表*/
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d日", Locale.getDefault())//创建日期格式
        val calendar = Calendar.getInstance()//获取当前日期和时间
        repeat(4) {
            options.add(formatter.format(calendar.time))//将当前的日期和时间按创建的日期格式设置
            calendar.add(Calendar.DATE, 1)//让日历按1天增加
        }
        return options
    }

    val dateOptions:List<String> = getPickupOptions()

    /*重置订单*/
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]//今天
        _price.value = 0.0
    }

    init {
        resetOrder()
    }

    /*计算价格
    * ?:)。elvis 运算符
    * (?:) 表示如果左侧的表达式不为 null，则使用该表达式。
    * 否则，如果左侧的表达式为 null，则使用 elvis 运算符右侧的表达式（在本例中为 0）。*/
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

}