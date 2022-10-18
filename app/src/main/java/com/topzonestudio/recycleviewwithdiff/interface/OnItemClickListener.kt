package com.topzonestudio.recycleviewwithdiff.`interface`

import com.topzonestudio.recycleviewwithdiff.model.User

interface OnItemClickListener {

    fun onItemClick(user: User)
    fun onDeleteItem(user: User)

}