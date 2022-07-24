package com.hmju.presentation

//@AndroidEntryPoint
//class MainFragment : BaseFragment<FMainBinding, MainFragmentViewModel>(R.layout.f_main) {
//
//    override val viewModel: MainFragmentViewModel by initViewModel()
//    override val bindingVariable: Int = BR.vm
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        with(binding) {
//            expiredToken.setOnClickListener {
//                moveFragment(RefreshTokenFragment())
//            }
//            simpleLike.setOnClickListener {
//                moveFragment(com.features.recyclerview.ui.independent_viewholder.SimpleLikeRecyclerViewFragment())
//            }
//            customPaging.setOnClickListener {
//                moveFragment(com.features.recyclerview.ui.custom_paging.CustomPagingFragment())
//            }
//            jsonJSend.setOnClickListener {
//                moveFragment(JsonJsendFragment())
//            }
//            refactorDiffUtil.setOnClickListener {
//                moveFragment(com.features.recyclerview.ui.diffutil_refactor.RefactorDiffUtilFragment())
//            }
//            performDiffUtil.setOnClickListener {
//                moveFragment(com.features.recyclerview.ui.diffutil_performance.DiffUtilPerformanceFragment())
//            }
//            mvvmLifecycle.setOnClickListener {
//                moveFragment(MvvmLifecycleFragment())
//            }
//            baseRefactor.setOnClickListener {
//                moveFragment(RefactorBaseRootTestFragment())
//            }
//            baseRefactorAct.setOnClickListener {
//                Intent(requireContext(), RefactorBaseTestActivity::class.java).apply {
//                    putExtra(IntentKey.TOKEN, "randomToken")
//                    startActivity(this)
//                }
//            }
//            errorHandling.setOnClickListener {
//                moveFragment(ErrorHandlingFragment())
//            }
//            diffUtilV2.setOnClickListener {
//                moveFragment(com.features.recyclerview.ui.diffutil_v2.DiffUtil2Fragment())
//            }
//        }
//    }
//
//    private fun moveFragment(fragment: Fragment) {
//        parentFragmentManager.beginTransaction().apply {
//            replace(R.id.fragment, fragment)
//            addToBackStack(null)
//            commit()
//        }
//    }
//}
