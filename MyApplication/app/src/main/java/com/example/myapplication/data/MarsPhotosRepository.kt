package com.example.myapplication.data

import com.example.myapplication.network.MarsApiService
import com.example.myapplication.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos():List<MarsPhoto>
}
class DefaultMarsPhotoRepository(
    private val marsApiService: MarsApiService
):MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}