package com.mobile.azri.testbackgroundlocationservice

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClientInterface  {
    fun getLocationUpdates(interval : Long) : Flow<Location>

    class LocationException(message:String): Exception()
}