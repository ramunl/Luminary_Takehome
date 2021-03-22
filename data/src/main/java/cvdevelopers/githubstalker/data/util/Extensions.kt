package cvdevelopers.githubstalker.data.util

import android.content.Context
import androidx.annotation.StringRes
import cvdevelopers.githubstalker.data.R
import cvdevelopers.githubstalker.utils.AppResult


fun Context.localStorageError() = errorAppResult(this, R.string.cache_error)

fun Context.noNetworkConnectivityError() = errorAppResult(this, R.string.network_error)


fun errorAppResult(context: Context, @StringRes res: Int): AppResult.Error =
        AppResult.Error(Exception(context.resources.getString(res)))

