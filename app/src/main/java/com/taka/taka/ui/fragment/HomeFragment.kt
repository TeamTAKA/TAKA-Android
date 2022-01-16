package com.taka.taka.ui.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.taka.taka.R
import com.taka.taka.databinding.FragmentHomeBinding
import com.taka.taka.ui.MypageActivity
import com.taka.taka.ui.TicketCardAdapter

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel

        binding.homeIvMypage.setOnClickListener {
            startActivity(Intent(context, MypageActivity::class.java))
        }

        // 티켓 이미지 뷰페이저 설정
        binding.homeVpTickets.adapter = TicketCardAdapter()
        (binding.homeVpTickets.adapter as TicketCardAdapter).setImageList(arrayListOf("","",""))
    }

}