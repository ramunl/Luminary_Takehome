package cvdevelopers.githubstalker.data.util

import android.content.Context
import cvdevelopers.githubstalker.data.R
import cvdevelopers.githubstalker.utils.AppResult


fun Context.noNetworkConnectivityError(): AppResult.Error {
    return AppResult.Error(Exception(this.resources.getString(R.string.network_error)))
}
