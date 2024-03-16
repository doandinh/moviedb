package com.doan.example.ui.screens

import android.content.*
import android.os.*
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.doan.example.R
import com.doan.example.databinding.ActivityMainBinding
import com.doan.example.enums.MessageActions
import com.doan.example.lib.AppEvent
import com.doan.example.lib.AppEventBus
import com.doan.example.model.MovieUiModel
import com.doan.example.services.NetworkingService
import com.doan.example.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    /** Messenger for communicating with the service.  */
    private var mService: Messenger? = null
    private var mBound: Boolean = false
    private var getMoviesJob: Job? = null

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    val mMessenger by lazy { Messenger(IncomingHandler(lifecycleScope)) }

    /** Defines callbacks for service binding, passed to bindService().  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mService = Messenger(service)

            // We've bound to LocalService, cast the IBinder and get LocalService instance.
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mService = null
            mBound = false
        }
    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = { inflater -> ActivityMainBinding.inflate(inflater) }

    override val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.tMainAppBar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcvMainNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService.
        Intent(this, NetworkingService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
        getMoviesJob?.cancel()
    }

    fun getMovies() {
        getMoviesJob = CoroutineScope(Dispatchers.IO).launch {
            while (!mBound && !isDestroyed) {
                delay(500)
            }
            if (mBound && !isDestroyed) {
                val msg = Message.obtain(null, MessageActions.ACTION_GET_MOVIES.ordinal, 0, 0)
                try {
                    msg.replyTo = mMessenger
                    mService?.send(msg)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * Handler of incoming messages from service.
     */
    internal class IncomingHandler(
        private val lifecycleScope: LifecycleCoroutineScope,
    ) :
        Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MessageActions.ACTION_GET_MOVIES.ordinal -> {
                    msg.obj?.let {
                        lifecycleScope.launch {
                            if (it is List<*>) {
                                AppEventBus.publish(AppEvent.MovieList(it as List<MovieUiModel>))
                            } else if (it is Throwable) {
                                AppEventBus.publish(AppEvent.Error(it))
                            }
                        }
                    }
                }

                else -> super.handleMessage(msg)
            }
        }
    }
}
