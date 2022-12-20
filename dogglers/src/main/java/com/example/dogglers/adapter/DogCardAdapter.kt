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
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int,
    private val dataset:List<Dog>
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // TODO: Initialize the data using the List found in data/DataSource

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
         val dogImage:ImageView= view!!.findViewById(R.id.dog_image)
         val dogName:TextView=view!!.findViewById(R.id.dog_name)
         val dogAge:TextView=view!!.findViewById(R.id.dog_age)
         val dogHobbies:TextView=view!!.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        if(layout==1){
            var adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item,parent,false)
            return DogCardViewHolder(adapterLayout)
        }else if(layout==2){
            var adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item,parent,false)
            return DogCardViewHolder(adapterLayout)
        }else if(layout==3){
            var adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item,parent,false)
            return DogCardViewHolder(adapterLayout)
        }
        return  DogCardViewHolder(null)


    }

    override fun getItemCount(): Int {
        return dataset.size
    } // TODO: return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val item=dataset[position]//列表项
        // TODO: Set the image resource for the current dog
        // TODO: Set the text for the current dog's name
        // TODO: Set the text for the current dog's age
        val resources = context?.resources
        holder.dogImage.setImageResource(item.imageResourceId)
        holder.dogName.text=item.name
        holder.dogAge.text=resources?.getString(R.string.dog_age,item.age)
        holder.dogHobbies.text=resources?.getString(R.string.dog_hobbies,item.hobbies)
    }
}
