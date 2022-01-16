package com.taka.taka.presentation.home

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taka.taka.databinding.FragmentHomeBinding
import com.taka.taka.presentation.mypage.MypageActivity
import com.taka.taka.presentation.home.adapter.TicketCardAdapter

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
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
        (binding.homeVpTickets.adapter as TicketCardAdapter).setImageList(arrayListOf("", "", ""))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}