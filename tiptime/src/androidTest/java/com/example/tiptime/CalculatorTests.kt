package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    //测试用例启动activity
    /*ActivityScenarioRule 来自 AndroidX Test 库。它会告知设备启动由开发者指定的 activity。
        您需要使用 @get:Rule 为此 activity 添加注解，
        @get:Rule() 会指定后续规则（在本例中为启动 activity）应该在该类中的每个测试之前执行。
        测试规则是进行测试的重要工具，最终您将学习如何编写自己的测试规则。*/
    @get:Rule()
    val activity=ActivityScenarioRule(MainActivity::class.java)

    /*onView:查找界面组件
    * perform：输入文本调用*/
    @Test
    fun calculate_20_percent_tip(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())//关闭键盘

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.00"))))

    }
}