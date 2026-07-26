package com.features.fragment_navigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.features.fragment_navigation.RootActivity
import com.features.fragment_navigation.databinding.FragmentDetailBinding
import com.features.fragment_navigation.navigation.FragmentDestination
import com.features.fragment_navigation.navigation.FragmentNavigationViewModel
import com.features.fragment_navigation.navigation.LaunchMode
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Description : STANDARD 로 여러 번 열면 매번 새 인스턴스가 스택에 쌓인다.
 * tv_created 시각이 "상세 또 열기" 를 누를 때마다 새로 찍히는 것으로 확인 가능.
 *
 * Created by juhongmin on 2026. 7. 26.
 */
internal class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val navigationViewModel: FragmentNavigationViewModel by activityViewModels()

    override fun onCreateView(
	inflater: LayoutInflater,
	container: ViewGroup?,
	savedInstanceState: Bundle?
    ): View {
	_binding = FragmentDetailBinding.inflate(inflater, container, false)
	return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
	super.onViewCreated(view, savedInstanceState)
	binding.tvCreated.text = "created at ${now()}"

	binding.btnDetail.setOnClickListener {
	    navigationViewModel.navigate(
		FragmentDestination(RootActivity.TAG_DETAIL) { DetailFragment() },
		LaunchMode.STANDARD
	    )
	}
	binding.btnSearch.setOnClickListener {
	    navigationViewModel.navigate(
		FragmentDestination(RootActivity.TAG_SEARCH) { SearchFragment() },
		LaunchMode.SINGLE_TOP
	    )
	}
	binding.btnCheckout.setOnClickListener {
	    navigationViewModel.navigate(
		FragmentDestination(RootActivity.TAG_RESULT) { ResultFragment() },
		LaunchMode.CLEAR_TOP
	    )
	}
    }

    override fun onDestroyView() {
	super.onDestroyView()
	_binding = null
    }

    private fun now(): String = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()).format(System.currentTimeMillis())
}
