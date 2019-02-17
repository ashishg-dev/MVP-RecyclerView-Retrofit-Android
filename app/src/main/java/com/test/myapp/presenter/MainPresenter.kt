package com.test.myapp.presenter

import com.test.myapp.Constant
import com.test.myapp.modal.VideoInfo
import com.test.myapp.restapi.ApiClient
import com.test.myapp.restapi.ApiInterface
import com.test.myapp.view.IMainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val view: IMainView) : IMainPresenter {


    override fun getDataFromServer() {

        view.showLoading()

        val service = ApiClient.createService(ApiInterface::class.java)
        val call: Call<VideoInfo> = service.getVideo(Constant.ACCESS_TOKEN)

        call.enqueue(object : Callback<VideoInfo> {

            override fun onResponse(call: Call<VideoInfo>?, response: Response<VideoInfo>?) {
                try {

                    if (response!!.isSuccessful) {

                        val videoInfo = response.body()

                        if (videoInfo.status) {
                            view.onGetSuccessResult(videoInfo)

                            view.hideLoading()

                        } else {
                            view.hideLoading()
                            view.onGetFailureResult(response.message())
                        }
                    } else {
                        view.hideLoading()
                        view.onGetFailureResult(response.message())
                    }

                } catch (e: Exception) {
                    view.hideLoading()
                    view.onGetFailureResult(e.message!!)
                }
            }

            override fun onFailure(call: Call<VideoInfo>?, t: Throwable?) {
                view.hideLoading()
                view.onGetFailureResult(t!!.message!!)
            }


        })


    }


}