package com.topzonestudio.recycleviewwithdiff

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.topzonestudio.recycleviewwithdiff.databinding.ActivityPermissionBinding
import kotlin.system.exitProcess
import kotlin.Int as Int1


class PermissionActivity : AppCompatActivity() {
    lateinit var binding: ActivityPermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        binding.accept.setOnClickListener {
            checkPermissions()

        }
        binding.dont.setOnClickListener {
            exitProcess(0)
        }


    }

    private fun checkPermissions(): Boolean {
        return if (ActivityCompat.checkSelfPermission(this,
                WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            requestPermission()


            false
        }
    }

    private fun requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse(String.format("package:%s", applicationContext.packageName))
                startActivityForResult(intent, 100)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                startActivityForResult(intent, 100)
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(this@PermissionActivity,
                arrayOf(WRITE_EXTERNAL_STORAGE),
                100)
        }
    }
    override fun onActivityResult(requestCode: Int1, resultCode: Int1, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                    val intent = Intent(this@PermissionActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}