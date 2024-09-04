package com.ajidroid.amphibians.data

import com.ajidroid.amphibians.model.Amphibian
import com.ajidroid.amphibians.network.AmphiApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}
class NetworkAmphibianRepository(
    private val amphiApiService : AmphiApiService
) : AmphibiansRepository{
    override suspend fun getAmphibians(): List<Amphibian> = amphiApiService.getData()
}