package com.taka.taka.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.taka.taka.databinding.FragmentHomeBinding
import com.taka.taka.presentation.home.adapter.TicketCardAdapter
import com.taka.taka.presentation.mypage.MypageActivity
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.homeIvMypage.setOnClickListener {
            startActivity(Intent(context, MypageActivity::class.java))
        }

        // 티켓 이미지 뷰페이저 설정
        binding.homeVpTickets.adapter = TicketCardAdapter { ticketId ->
            //상세화면으로 이동
        }
        binding.homeVpTickets.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.homeTvCurrentTicket.text = (position+1).toString()
            }
        })

        lifecycleScope.launch {
            viewModel.getTickets().let { ticketCards ->
                binding.homeTvEmpty.isVisible = ticketCards.isEmpty()
                binding.homeTvTicketCount.text = "/${ticketCards.size}"
                (binding.homeVpTickets.adapter as TicketCardAdapter).setTicketList(viewModel.getTickets())
            }
        }

        viewModel.state.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}