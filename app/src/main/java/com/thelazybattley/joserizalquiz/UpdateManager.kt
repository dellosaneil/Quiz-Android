package com.thelazybattley.joserizalquiz

import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import timber.log.Timber

class UpdateManager(private val context: Context) {

    private val appUpdateManager by lazy {
        AppUpdateManagerFactory.create(context)
    }

    private var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>

    init {
        val activity = context as MainActivity
        activityResultLauncher =
            activity.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                if (result.resultCode != RESULT_OK) {
                    Timber.e("${result.resultCode}")
                }
            }
    }

    fun checkInAppUpdate() {
        appUpdateManager
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                ) {
                    appUpdateManager
                        .startUpdateFlowForResult(
                            appUpdateInfo,
                            activityResultLauncher,
                            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE)
                                .setAllowAssetPackDeletion(true).build()
                        )
                }
            }
            .addOnFailureListener {
                Timber.e(it)
            }
    }
}
