package com.taka.taka.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.taka.taka.R
import com.taka.taka.databinding.FragmentHomeBinding
import com.taka.taka.presentation.detail.DetailActivity
import com.taka.taka.presentation.home.adapter.TicketCardAdapter
import com.taka.taka.presentation.home.adapter.TicketGroupAdapter
import com.taka.taka.presentation.mypage.MypageActivity
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    private var isModeGroup = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.homeIvMode.setOnClickListener {
            if (isModeGroup) {
                (it as ImageView).setImageResource(R.drawable.ic_view_linear)
                binding.homeVpTickets.isVisible = true
                binding.homeTvTicketCount.isVisible = true
                binding.homeTvCurrentTicket.isVisible = true
                binding.indicator.isVisible = true
                binding.homeRvTickets.isVisible = false
            } else {
                (it as ImageView).setImageResource(R.drawable.ic_view_grid)
                binding.homeVpTickets.isVisible = false
                binding.homeTvTicketCount.isVisible = false
                binding.homeTvCurrentTicket.isVisible = false
                binding.indicator.isVisible = false
                binding.homeRvTickets.isVisible = true
            }
            isModeGroup = !isModeGroup
        }
        binding.homeIvMypage.setOnClickListener {
            startActivity(Intent(context, MypageActivity::class.java))
        }

        // 티켓 이미지 뷰페이저 설정
        binding.homeVpTickets.adapter = TicketCardAdapter { ticketId ->
            startActivity(
                Intent(context, DetailActivity::class.java)
                    .putExtra(DetailActivity.TICKET_ID_KEY, ticketId)
            )
        }
        binding.homeVpTickets.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.homeTvCurrentTicket.text = (position+1).toString()
            }
        })
        // 티켓 그룹 목록
        binding.homeRvTickets.adapter = TicketGroupAdapter { ticketId ->
            startActivity(
                Intent(context, DetailActivity::class.java)
                    .putExtra(DetailActivity.TICKET_ID_KEY, ticketId)
            )
        }

        lifecycleScope.launch {
            viewModel.getTickets().let { ticketCards ->
                binding.homeTvEmpty.isVisible = ticketCards.isEmpty()
                binding.homeTvTicketCount.text = "/${ticketCards.size}"
                (binding.homeVpTickets.adapter as TicketCardAdapter).setTicketList(viewModel.getTickets())
            }
            viewModel.getTicketGroups().let { ticketGroups ->
                (binding.homeRvTickets.adapter as TicketGroupAdapter).setTicketGroupList(ticketGroups)
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