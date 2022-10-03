package com.topzonestudio.recycleviewwithdiff

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.topzonestudio.recycleviewwithdiff.`interface`.OnItemClickListener
import com.topzonestudio.recycleviewwithdiff.adaptor.AdaptorClass
import com.topzonestudio.recycleviewwithdiff.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdaptorClass

    private var _originalList: ArrayList<User> = ArrayList()
    private val originalList: List<User> get() = _originalList.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        fillList()
    }

    private fun initRecyclerView() {
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
    }

    private fun fillList() {
        _originalList.apply {
            add(User(0, "Sohaib", "phoasdn"))
            add(User(1, "Kamran", "oasdn"))
            add(User(2, "Afnan", "pasdn"))
            add(User(3, "Yaqoob", "pho"))
        }
        val newList = originalList.map { it.copy() } as ArrayList<User>
        adapter.submitList(newList)
    }
}
