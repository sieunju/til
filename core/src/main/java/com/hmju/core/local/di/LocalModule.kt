package com.hmju.core.local.di

import android.content.Context
import androidx.room.Room
import com.hmju.core.local.AppDataBase
import com.hmju.core.local.dao.GoodsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 11.
 */
@InstallIn(SingletonComponent::class)
@Module
internal object LocalModule {

	@Provides
	@Singleton
	fun provideAppDataBase(
		@ApplicationContext context: Context
	): AppDataBase {
		return Room.databaseBuilder(
			context, AppDataBase::class.java,
			"til-database"
		).fallbackToDestructiveMigration()
			.setQueryExecutor(Executors.newSingleThreadExecutor())
			.setQueryCallback({ sqlQuery, bindArgs ->
				val queryHash = sqlQuery.hashCode().toString()
				Timber.tag("RoomLOG").d("=====Query Start==== ${Thread.currentThread()}")
				Timber.tag("RoomLOG").d("SQL: $sqlQuery")
				val args = bindArgs.filterNotNull()
				if (args.isNotEmpty()) {
					Timber.tag("RoomLOG").d("Bind Args ${args.joinToString { formatArg(it) }}")
				}
				Timber.tag("RoomLOG").d("Query Hash $queryHash")
				Timber.tag("RoomLOG").d("======Query End=====")
			}, Executors.newSingleThreadExecutor())
			.build()
	}

	private fun formatArg(arg: Any?): String = when (arg) {
		is String -> "'$arg'"
		is Long -> "${arg}L"
		is Int -> arg.toString()
		is Boolean -> arg.toString()
		null -> "NULL"
		else -> arg.toString()
	}

	@Provides
	@Singleton
	fun provideGoodsDao(dataBase: AppDataBase): GoodsDAO = dataBase.goodsDao()
}
