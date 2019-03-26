package com.test.myapp.modal

data class VideoInfo(val status: Boolean, val message: String, val data: VideoData) {
}

data class VideoData(val subscribers: String, val videos: ArrayList<VideoList>) {

}

data class VideoList(
    val video_id: String, val title: String, val video_desc: String,
    val duration: String, val views: String, val likes: String, val dislikes: String,
    val img_url: String, val video_date: String, val extra_para: String
){
    var isVideoChecked: Boolean = false
        get() = field
        set(value) {
            field = value
        }
}