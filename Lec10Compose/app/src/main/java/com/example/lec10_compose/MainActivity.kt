package com.example.lec10_compose

import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import coil.compose.AsyncImage
import com.example.lec10_compose.ImageViewModel
import com.example.lec10_compose.R
import com.example.lec10_compose.ui.theme.Lec10ComposeTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ImageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES),0)
        setContentView(R.layout.activity_main)
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
        )


        val millisYesterday = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR,-1)
        }.timeInMillis

        val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        val selectionArgs = arrayOf(millisYesterday.toString())


        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use {
                cursor ->

            val idColumns = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

            val images = mutableListOf<Image>()
            while(cursor.moveToNext()) {
                val id = cursor.getLong(idColumns)
                val name = cursor.getString(nameColumn)
                val uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                images.add(Image(id,name,uri))
            }
            viewModel.updateImages(images)

        }
        setContent {
            Lec10ComposeTheme {
                LazyColumn( modifier = Modifier.fillMaxSize()  ) {
                    items(viewModel.images) {
                        image -> Column(
                        modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        AsyncImage(
                            model = image.uri,
                            contentDescription = null
                        )
                        Text(text = image.name)
                    }
                    }
                }
            }
        }

    }
}

data class Image (
    val id : Long,
    val name : String,
    val uri : Uri
)

