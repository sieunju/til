package com.features.fragment_navigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.features.fragment_navigation.RootActivity
import com.features.fragment_navigation.databinding.FragmentResultBinding
import com.features.fragment_navigation.navigation.FragmentDestination
import com.features.fragment_navigation.navigation.FragmentNavigatorHost
import com.features.fragment_navigation.navigation.LaunchMode

/**
 * Description : CLEAR_TOP 으로 진입한 화면. 이전 스택이 전부 지워졌기 때문에
 * 뒤로가기를 눌러도 Detail/Search 로 못 돌아가고 바로 Activity 가 종료된다.
 *
 * Created by juhongmin on 2026. 7. 26.
 */
internal class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
	inflater: LayoutInflater,
	container: ViewGroup?,
	savedInstanceState: Bundle?
    ): View {
	_binding = FragmentResultBinding.inflate(inflater, container, false)
	return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
	super.onViewCreated(view, savedInstanceState)

	val host = requireActivity() as FragmentNavigatorHost
	binding.btnRestart.setOnClickListener {
	    host.navigate(
		FragmentDestination(RootActivity.TAG_HOME) { HomeFragment() },
		LaunchMode.CLEAR_TOP
	    )
	}
    }

    override fun onDestroyView() {
	super.onDestroyView()
	_binding = null
    }
}
