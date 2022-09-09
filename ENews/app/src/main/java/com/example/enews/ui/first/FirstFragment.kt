package com.example.enews.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.enews.databinding.FragmentFirstBinding
import com.example.enews.ui.first.etm.entertainment
import com.example.enews.ui.first.headLine.headLine
import com.google.android.material.tabs.TabLayoutMediator

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 滑动时同步
        binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 8

            override fun createFragment(position: Int)=
                when(position) {
                    0 -> headLine()
                    1 -> entertainment()
                    2 -> sport()
                    3 -> FinanceEconomics()
                    4 -> militaryScience()
                    5 -> game()
                    6 -> militaryScience()
                    else -> game()
                }
        }

        // 点击时同步
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "头条"
                1 -> tab.text = "娱乐"
                2 -> tab.text = "体育"
                3 -> tab.text = "财经"
                4 -> tab.text = "军事"
                5 -> tab.text = "游戏"
                6 -> tab.text = "财经"
                else -> tab.text = "游戏"
            }
        }.attach()

    }

}