package com.example.lec10_compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lec10_compose.Image

class ImageViewModel  : ViewModel(){
    var images by mutableStateOf(emptyList<Image>())
        private set

    fun updateImages(images: MutableList<Image>) {
        this.images = images
    }
}