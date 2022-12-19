package com.example.affirmations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myDataset = Datasource().loadAffrimations()
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter=ItemAdapter(this,myDataset)
//由于 activity 布局中的 RecyclerView 布局大小是固定的，因此您可以将 RecyclerView 的 setHasFixedSize 参数设为 true。
// 只有在提高性能时才需要使用此设置。如果您知道内容的更改不会更改 RecyclerView 的布局大小，请使用此设置。
        recyclerView.setHasFixedSize(true)

    }
}