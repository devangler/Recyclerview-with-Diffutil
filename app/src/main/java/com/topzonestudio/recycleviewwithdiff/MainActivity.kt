package com.topzonestudio.recycleviewwithdiff

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.topzonestudio.recycleviewwithdiff.`interface`.OnItemClickListener
import com.topzonestudio.recycleviewwithdiff.adaptor.AdaptorClass
import com.topzonestudio.recycleviewwithdiff.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var arrayList: ArrayList<String> = ArrayList()
    private val list: List<String> get() = arrayList.toList()
    private lateinit var adapter: AdaptorClass
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AdaptorClass(object : OnItemClickListener {
            override fun onItemClick(value: String) {
                Toast.makeText(this@MainActivity, "position$value", Toast.LENGTH_SHORT).show()
            }

            override fun onDeleteItem(value: String) {
                arrayList.remove(value)
                adapter.submitList(list)
            }
        })

        arrayList.add("A")
        arrayList.add("B")
        arrayList.add("C")
        binding.recyclerViewCard.adapter = adapter
        adapter.submitList(list)

    }
}