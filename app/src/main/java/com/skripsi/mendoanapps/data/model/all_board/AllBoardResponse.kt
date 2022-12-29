package com.skripsi.mendoanapps.data.model.all_board

import com.google.gson.annotations.SerializedName

data class AllBoardResponse(

    @field:SerializedName("Boards")
    val boards: List<BoardsItem>
)

data class BoardsItem(

    @field:SerializedName("lastactivity")
    val lastactivity: String? = null,

    @field:SerializedName("shortUrl")
    val shortUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)
