package com.features.fragment_navigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.features.fragment_navigation.RootActivity
import com.features.fragment_navigation.databinding.FragmentSearchBinding
import com.features.fragment_navigation.navigation.FragmentBackPressHandler
import com.features.fragment_navigation.navigation.FragmentDestination
import com.features.fragment_navigation.navigation.FragmentNavigatorHost
import com.features.fragment_navigation.navigation.LaunchMode
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Description : SINGLE_TOP 데모. Home 이나 Detail 에서 "검색 열기" 를 눌러 다시 들어오면
 * 새 인스턴스를 만들지 않고 이 인스턴스를 그대로 재사용한다 (hide/show, onCreateView 재호출 없음).
 * et_query 에 입력한 텍스트와 tv_created 시각이 그대로 남아있는지로 확인 가능.
 *
 * [FragmentBackPressHandler] 구현 예시: 검색어가 남아있으면 첫 back press 는 화면 이탈 대신 검색어부터 지운다.
 *
 * Created by juhongmin on 2026. 7. 26.
 */
internal class SearchFragment : Fragment(), FragmentBackPressHandler {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
	inflater: LayoutInflater,
	container: ViewGroup?,
	savedInstanceState: Bundle?
    ): View {
	_binding = FragmentSearchBinding.inflate(inflater, container, false)
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

    override fun onBackPressed(): Boolean {
	val query = binding.etQuery.text
	if (query.isNullOrEmpty()) return false
	query.clear()
	return true
    }

    private fun now(): String = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()).format(System.currentTimeMillis())
}
