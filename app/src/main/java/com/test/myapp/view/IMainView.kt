package com.test.myapp.view

import com.test.myapp.modal.VideoInfo

interface IMainView {

    fun showLoading()
    fun hideLoading()
    fun onGetSuccessResult(videoInfo: VideoInfo)
    fun onGetFailureResult(message: String)
    fun onLoginResultSuccess()
}