package com.hmju.core.util


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableTransformer
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeTransformer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

object RxUtil {
	fun <T : Any> observerUi(): ObservableTransformer<T, T> {
		return ObservableTransformer { upstream: Observable<T> ->
			upstream.observeOn(AndroidSchedulers.mainThread())
		}
	}

	fun <T : Any> singleUi(): SingleTransformer<T, T> {
		return SingleTransformer { upstream: Single<T> ->
			upstream.observeOn(AndroidSchedulers.mainThread())
		}
	}

	fun <T : Any> maybeUi(): MaybeTransformer<T, T> {
		return MaybeTransformer { upstream: Maybe<T> ->
			upstream.observeOn(AndroidSchedulers.mainThread())
		}
	}

	fun <T : Any> flowableUi(): FlowableTransformer<T, T> {
		return FlowableTransformer { upstream: Flowable<T> ->
			upstream.observeOn(AndroidSchedulers.mainThread())
		}
	}

	/**
	 * Single.merge 를 사용할때 병렬로 처리 하기 위해선
	 * Single.mergeDelayError(api().compose(singleWork)) 로 처리하면
	 * 좀더 병렬로 비동기 처리 할수 있다.
	 */
	fun <T : Any> singleMergeIo(): SingleTransformer<T, T> {
		return SingleTransformer { upstream: Single<T> ->
			upstream.subscribeOn(Schedulers.io())
		}
	}

	fun <T : Any> flowableMergeIo(): FlowableTransformer<T, T> {
		return FlowableTransformer { upstream: Flowable<T> ->
			upstream.subscribeOn(Schedulers.io())
		}
	}

	/**
	 * Single 작업 쓰레드 IO, 작업 완류후 MainThread 처리
	 * ex.) RemoteConfig 에서 사용
	 */
	fun <T : Any> singleWithUi(): SingleTransformer<T, T> {
		return SingleTransformer { upstream: Single<T> ->
			upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
		}
	}

	/**
	 * Flowable 작업 쓰레드 IO, 작업 완료후 MainThread 처리
	 */
	fun <T : Any> flowableWithUi(): FlowableTransformer<T, T> {
		return FlowableTransformer { upstream: Flowable<T> ->
			upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
		}
	}
}