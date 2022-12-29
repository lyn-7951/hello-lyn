package com.example.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.words.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {
    //    伴生对象。伴生对象名称和类对象名称一样
    companion object{
        const val LETTER="letter"
        const val SEARCH_PREFIX = "https://www.baidu.com/s?wd="//百度搜索的基准网址
        const val SEARCH_PREFIX2 = "https://www.google.com/search?q="//Google搜索的基准网址
    }

    private var _binding: FragmentWordListBinding? = null


    private val binding get()=_binding!!

    private lateinit var letterId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    /*创建fragment视图*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentWordListBinding.inflate(inflater,container,false)
        return binding.root
    }

    /*设置recycleView属性的值*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //        ?:属性可为null
//        注意：null访问属性，调用方法，应用会崩溃
//        如果intent为null，不会访问extras，如果extras为null,不会调用getString
       val  recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )


    }

    /*视图不存在*/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
     }

    }

