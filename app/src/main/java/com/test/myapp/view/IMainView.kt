package com.test.myapp.view

import com.test.myapp.modal.VideoInfo
import com.test.myapp.modal.VideoList

interface IMainView {

    fun showLoading()
    fun hideLoading()
    fun onGetSuccessResult(videoInfo: VideoInfo)
    fun onGetFailureResult(message: String)
    fun onDummyDataResult(videoList:ArrayList<VideoList>)
}