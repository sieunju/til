package com.features.fragment_navigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.features.fragment_navigation.RootActivity
import com.features.fragment_navigation.databinding.FragmentHomeBinding
import com.features.fragment_navigation.navigation.FragmentDestination
import com.features.fragment_navigation.navigation.FragmentNavigatorHost
import com.features.fragment_navigation.navigation.LaunchMode
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Description : 루트 화면. 여기서 세 가지 launchMode 를 각각 시연한다.
 *
 * Created by juhongmin on 2026. 7. 26.
 */
internal class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
	inflater: LayoutInflater,
	container: ViewGroup?,
	savedInstanceState: Bundle?
    ): View {
	_binding = FragmentHomeBinding.inflate(inflater, container, false)
	return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
	super.onViewCreated(view, savedInstanceState)
	binding.tvCreated.text = "created at ${now()}"

	val host = requireActivity() as FragmentNavigatorHost
	binding.btnDetail.setOnClickListener {
	    host.navigate(
		FragmentDestination(RootActivity.TAG_DETAIL) { DetailFragment() },
		LaunchMode.STANDARD
	    )
	}
	binding.btnSearch.setOnClickListener {
	    host.navigate(
		FragmentDestination(RootActivity.TAG_SEARCH) { SearchFragment() },
		LaunchMode.SINGLE_TOP
	    )
	}
	binding.btnCheckout.setOnClickListener {
	    host.navigate(
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
