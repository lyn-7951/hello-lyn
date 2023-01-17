
package com.example.android.sports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.android.sports.databinding.FragmentSportsListBinding
import com.example.android.sports.model.Sport

/**
 * 这是第一个画面 fragment，其中会显示体育项目列表。
 */

class SportsListFragment : Fragment() {

    private val sportsViewModel: SportsViewModel by activityViewModels()
    private lateinit var adapter: SportsAdapter

    /*绑定对象膨胀 fragment_sports_list*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSportsListBinding.inflate(inflater, container, false).root
    }

    /*设置 RecyclerView 适配器。
    它会将用户选择的体育项目更新为共享 ViewModel（即 SportsViewModel）中的当前体育项目。
    此外，它会导航到包含体育新闻的详细信息画面，并使用 submitList(List) 将体育项目列表提交给适配器以供显示。*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSportsListBinding.bind(view)

        val slidingPaneLayout = binding.slidingPaneLayout
        /*注册回调*/
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SportsListOnBackPressedCallback(slidingPaneLayout))

//        用户无法在列表窗格和详细信息窗格之间轻扫，但应用可以通过编程方式打开或关闭详细信息窗格。
        slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED//锁定模式


//        ListAdapter
        adapter = SportsAdapter ({
            it.titleResourceId=R.string.text
            sportsViewModel.updateCurrentSport(it)
//            adapter.submitList(sportsViewModel.sportsData.toList())//传入一个新的集合，列表才会更新
            //openPane()，使第二个窗格显示在第一个窗格前方。
            //如果两个窗格都可见（例如在平板电脑上），该操作将不会产生任何可见的影响。

            val apply = sportsViewModel.sportsData.toMutableList().apply {
                this.add( Sport(0,
                    R.string.text,
                    R.string.text,
                    R.drawable.icon_badminton,
                    R.drawable.img_badminton))
            }
            adapter.submitList(apply)
            binding.slidingPaneLayout.openPane()
        },{
            binding.recyclerView.scrollToPosition(0)
        })

        binding.recyclerView.adapter = adapter
        //ListAdapter 通过 submitList() 方法获取数据，该方法提交了一个列表来与当前列表进行对比并显示。
        adapter.submitList(sportsViewModel.sportsData)


//         RecyclerView.Adapter
//       val adapter=SportsAdapter2({
//
//           sportsViewModel.sportsData.toMutableList().apply {
//               it.titleResourceId=R.string.text
//               it.subTitleResourceId=R.string.text
//           }
//           sportsViewModel.updateCurrentSport(it)
//           binding.slidingPaneLayout.openPane()
//
//
//
//       },requireContext(),sportsViewModel.sportsData)
//
//        binding.recyclerView.adapter=adapter







    }




}



/*
*   OnBackPressedCallback：定义回调处理返回键按下操作
    *   OnBackPressedCallback 类负责处理 onBackPressed 回调
    *   OnBackPressedCallback 的构造函数会接受一个指示初始启用状态的布尔值。
    *       仅当回调处于启用状态时（即 isEnabled() 返回 true 时），调度程序才会调用回调的 handleOnBackPressed() 来处理返回按钮事件
    *       仅当第二个窗格可滑动时，布尔值 isSlideable 才会为 true，这种情况会出现在较小的屏幕上，且只会显示一个窗格。如果第二个窗格（内容窗格）完全打开，isOpen 的值将为 true。
    *   仅在小屏幕设备上以及内容窗格处于打开状态时，才会启用回调
    *
*   SlidingPaneLayout.PanelSlideListener:监听与监控与滑动窗格相关的事件
* */
class SportsListOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
): OnBackPressedCallback(slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen) ,SlidingPaneLayout.PanelSlideListener{

    /*该列表中的监听器将收到详细信息窗格滑动事件通知*/
    init{
        slidingPaneLayout.addPanelSlideListener(this)
    }


    override fun handleOnBackPressed() {
        //SlidingPaneLayout 始终允许您手动调用 open() 和 close()，以便在手机上的列表窗格和详细信息窗格之间转换。如果两个窗格均可见且未重叠，这些方法将不会产生任何影响。
        slidingPaneLayout.closePane()//关闭内容窗格并返回到列表窗格
    }

    /*在详细信息窗格滑动*/
    override fun onPanelSlide(panel: View, slideOffset: Float) {

    }

    /*在详细信息窗格打开*/
    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    /*在详细信息窗格关闭*/
    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }
}