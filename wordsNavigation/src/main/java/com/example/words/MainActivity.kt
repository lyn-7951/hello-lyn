/*
 * Copyright (C) 2020 The Android Open Source Project
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
package com.example.words

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.words.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        NavHost 用于在 activity 内显示导航图中的目的地。
//        当您在 fragment 之间导航时，NavHost 中显示的目的地会相应更新。
//        您将在 MainActivity 中使用名为 NavHostFragment 的内置实现。
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        您可以使用 NavController 对象控制 NavHost 中显示的目的地之间的导航。
//        在使用 intent 时，您必须通过调用 startActivity 导航到新屏幕。
//        借助 Navigation 组件，您可以通过调用 NavController 的 navigate() 方法来交换显示的 fragment。
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)//操作按钮【<-】,点击返回上一个fragment
    }

    /*按向上按钮回到之前显示的fragment
    * navigateUp返回false调用super。
    * navigateUp为true，super不执行*/
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
