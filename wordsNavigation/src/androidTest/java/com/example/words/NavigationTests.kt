package com.example.words

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Assert.assertEquals
import org.junit.Test

class NavigationTests {

//    测试navigation
    @Test//避免重复代码
    fun navigate_to_words_nav_component() {
//    1.创建导航的控制器
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
/*   2.启动fragment
       (1)(themeResId =R.style.Theme_Words)这个必须要不然要出错【传递应用的主题】
       (2)使用 launchFragmentInContainer() 方法时，无法进行实际的导航，
         因为容器不知道我们可能会导航到的其他 fragment 或 activity。
         它只知道我们指定要在其中启动的 fragment。*/
        val letterListScenario = launchFragmentInContainer<LetterListFragment>(themeResId =
        R.style.Theme_Words)
//    3.导航图
        letterListScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
//    4.触发导航事件
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
//    5.确保当前导航控制器的目的地的 ID 与我们期望的 fragment 的 ID 相同
        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
    }



}