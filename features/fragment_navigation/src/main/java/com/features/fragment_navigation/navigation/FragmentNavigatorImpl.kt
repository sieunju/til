package com.features.fragment_navigation.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle

/**
 * Description : [FragmentNavigator] 구현체
 *
 * 스택에 남아있는 Fragment 는 remove 하지 않고 hide 상태로 유지한다 (View, 인스턴스 보존).
 * ViewPager2 의 FragmentStateAdapter 가 현재 페이지만 RESUMED, 나머지는 STARTED 로 묶어두는 것과 동일하게
 * 화면에 보이는 top 만 RESUMED, 그 아래 hide 된 항목들은 STARTED 로 눌러둬서
 * 화면 밖에서 onResume 로직(센서/영상재생 등)이 계속 돌지 않도록 한다.
 *
 * Created by juhongmin on 2026. 7. 26.
 */
class FragmentNavigatorImpl(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int
) : FragmentNavigator {

    private data class StackEntry(val instanceTag: String, val destinationTag: String)

    private val stack = mutableListOf<StackEntry>()
    private var instanceSeq = 0

    override fun navigate(destination: FragmentDestination, launchMode: LaunchMode) {
	when (launchMode) {
	    LaunchMode.STANDARD -> standard(destination)
	    LaunchMode.SINGLE_TOP -> singleTop(destination)
	    LaunchMode.CLEAR_TOP -> clearTop(destination)
	}
    }

    override fun back(): Boolean {
	if (stack.size <= 1) return false
	val top = stack.removeAt(stack.lastIndex)
	val newTop = stack.last()
	fragmentManager.commit {
	    remove(requireFragment(top.instanceTag))
	    show(requireFragment(newTop.instanceTag))
	    setMaxLifecycle(requireFragment(newTop.instanceTag), Lifecycle.State.RESUMED)
	}
	return true
    }

    override fun canGoBack(): Boolean = stack.size > 1

    override fun currentStack(): List<String> = stack.map { it.instanceTag }

    override fun currentFragment(): Fragment? =
	stack.lastOrNull()?.let { fragmentManager.findFragmentByTag(it.instanceTag) }

    private fun standard(destination: FragmentDestination) {
	val current = stack.lastOrNull()
	val instanceTag = newInstanceTag(destination.tag)
	val fragment = destination.createFragment()
	fragmentManager.commit {
	    if (current != null) {
		val currentFragment = requireFragment(current.instanceTag)
		hide(currentFragment)
		setMaxLifecycle(currentFragment, Lifecycle.State.STARTED)
	    }
	    add(containerId, fragment, instanceTag)
	    setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
	}
	stack.add(StackEntry(instanceTag, destination.tag))
    }

    private fun singleTop(destination: FragmentDestination) {
	val existingIndex = stack.indexOfLast { it.destinationTag == destination.tag }
	if (existingIndex == -1) {
	    standard(destination)
	    return
	}
	if (existingIndex == stack.lastIndex) return // 이미 top

	val current = stack.last()
	val target = stack[existingIndex]
	val toDrop = stack.subList(existingIndex + 1, stack.size).toList()

	fragmentManager.commit {
	    val currentFragment = requireFragment(current.instanceTag)
	    hide(currentFragment)
	    toDrop.forEach { remove(requireFragment(it.instanceTag)) }
	    val targetFragment = requireFragment(target.instanceTag)
	    show(targetFragment)
	    setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED)
	}
	repeat(toDrop.size) { stack.removeAt(stack.lastIndex) }
    }

    private fun clearTop(destination: FragmentDestination) {
	val toDrop = stack.toList()
	val instanceTag = newInstanceTag(destination.tag)
	val fragment = destination.createFragment()

	fragmentManager.commit {
	    toDrop.forEach { remove(requireFragment(it.instanceTag)) }
	    add(containerId, fragment, instanceTag)
	    setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
	}
	stack.clear()
	stack.add(StackEntry(instanceTag, destination.tag))
    }

    private fun newInstanceTag(destinationTag: String): String = "$destinationTag#${instanceSeq++}"

    private fun requireFragment(instanceTag: String): Fragment =
	checkNotNull(fragmentManager.findFragmentByTag(instanceTag)) {
	    "Fragment not found for tag: $instanceTag"
	}
}
