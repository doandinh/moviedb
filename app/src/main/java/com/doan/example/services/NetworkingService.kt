package com.doan.example.services

import android.content.Intent
import android.os.*
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.doan.example.domain.usecases.GetMovieDetailUseCase
import com.doan.example.domain.usecases.GetMoviesUseCase
import com.doan.example.enums.MessageActions
import com.doan.example.model.toUiModel
import com.doan.example.util.DispatchersProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@AndroidEntryPoint
class NetworkingService : LifecycleService() {

    @Inject
    lateinit var dispatchersProvider: DispatchersProvider

    @Inject
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @Inject
    lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private lateinit var mMessenger: Messenger

    /**
     * Handler of incoming messages from clients.
     */
    internal class IncomingHandler(
        private val service: NetworkingService
    ) : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MessageActions.ACTION_GET_MOVIES.ordinal ->
                    service.getMovies(msg.replyTo)
                MessageActions.ACTION_GET_MOVIE_DETAIL.ordinal ->
                    service.getMovieDetail((msg.obj as? Long) ?: 0, msg.replyTo)

                else -> super.handleMessage(msg)
            }
        }
    }

    fun getMovies(replyTo: Messenger) {
        getMoviesUseCase()
            .onEach { result ->
                val uiModels = result.results.map { it.toUiModel() }
                replyTo.send(
                    Message.obtain(
                        null,
                        MessageActions.ACTION_GET_MOVIES.ordinal,
                        uiModels
                    )
                )
            }
            .flowOn(dispatchersProvider.io)
            .catch { e ->
                replyTo.send(
                    Message.obtain(
                        null,
                        MessageActions.ACTION_GET_MOVIES.ordinal,
                        e
                    )
                )
            }
            .launchIn(lifecycleScope)
    }

    fun getMovieDetail(movieId: Long, replyTo: Messenger) {
        getMovieDetailUseCase(movieId)
            .onEach { result ->
                replyTo.send(
                    Message.obtain(
                        null,
                        MessageActions.ACTION_GET_MOVIE_DETAIL.ordinal,
                        result.toUiModel()
                    )
                )
            }
            .flowOn(dispatchersProvider.io)
            .catch { e ->
                replyTo.send(
                    Message.obtain(
                        null,
                        MessageActions.ACTION_GET_MOVIE_DETAIL.ordinal,
                        e
                    )
                )
            }
            .launchIn(lifecycleScope)
    }
}
