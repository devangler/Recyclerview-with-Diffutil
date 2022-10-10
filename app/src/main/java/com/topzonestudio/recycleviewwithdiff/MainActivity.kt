package com.topzonestudio.recycleviewwithdiff

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.topzonestudio.recycleviewwithdiff.`interface`.OnItemClickListener
import com.topzonestudio.recycleviewwithdiff.adaptor.AdaptorClass
import com.topzonestudio.recycleviewwithdiff.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.io.File

class MainActivity : AppCompatActivity() {
    //
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdaptorClass

    private var _originalList: ArrayList<User> = ArrayList()
    private val originalList: List<User> get() = _originalList.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDocList()

        adapter = AdaptorClass(object : OnItemClickListener {
            override fun onItemClick(user: User) {
                Toast.makeText(this@MainActivity, "Username: ${user.name}", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onDeleteItem(user: User) {
                val newList = originalList.map { it.copy() } as ArrayList<User>
                newList.remove(user)
                adapter.submitList(newList) {
                    _originalList.clear()
                    _originalList.addAll(newList)
                }
            }
        })
        binding.recyclerViewCard.adapter = adapter
        val newList = originalList.map { it.copy() } as ArrayList<User>
        adapter.submitList(newList)
     //   fillList()

    }

    private fun getDocList() {

        val projection = arrayOf(
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.DATE_MODIFIED,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns._ID,
        )
        val sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC"

        val pdf = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf")

        val where = MediaStore.Files.FileColumns.MIME_TYPE +
                " IN ('" + pdf + "')"


        val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Files.getContentUri("external")
        }
        application.contentResolver.query(collection, projection, where, null, sortOrder)
            .use { cursor ->
                assert(cursor != null)
                if (cursor!!.moveToFirst()) {
                    Log.d("AllDocs", "cursor not null")

                    val columnPath: Int = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)

                    do {
                        val filePath: String = cursor.getString(columnPath)

                        val file = File(filePath)
                        if (file.exists() && file.extension.trim() != "") {
                            _originalList.add(
                                User(
                                    1,
                                    name = file.name,
                                    file.absolutePath
                                )
                            )
                        }

                        Log.d("AllDocs", file.name.toString().trim())

                        //you can get your doc files
                    } while (cursor.moveToNext())
                    Log.d("AllDocs", "--------------------------")
                } else {
                    Log.d("AllDocs", "cursor null")
                }
            }
        // docList.clear()
        //  docList.addAll(docListData)
    }

}
