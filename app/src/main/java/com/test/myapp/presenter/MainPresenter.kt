package com.test.myapp.presenter

import com.test.myapp.Constant
import com.test.myapp.modal.VideoData
import com.test.myapp.modal.VideoInfo
import com.test.myapp.modal.VideoList
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

    fun callDummydata(){
        var videoListArray = arrayListOf<VideoList>()
        var video  = VideoList("1", "vinay", "nice",
            "1", "123", "3", "6",
            "test", "26 Mar 2018", "nothing")
        video.isVideoChecked=false

        var video1  = VideoList("2", "Sanjay", "bad",
            "3", "456", "3", "6",
            "test", "22 Mar 2018", "nothing")
        video1.isVideoChecked=true

        var video2  = VideoList("3", "Niranjan", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video2.isVideoChecked=false

        var video3  = VideoList("3", "Vyankatesh", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video3.isVideoChecked=false

        var video4  = VideoList("3", "Sandesh", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video4.isVideoChecked=false

        var video5  = VideoList("3", "Andru", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video5.isVideoChecked=false

        var video6  = VideoList("3", "Richard", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video6.isVideoChecked=false

        var video7  = VideoList("3", "Sakshi", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video7.isVideoChecked=false

        var video8  = VideoList("3", "Rohan", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video8.isVideoChecked=false

        var video9  = VideoList("3", "RajKumar", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video9.isVideoChecked=false

        var video10  = VideoList("3", "Ajay", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video10.isVideoChecked=false

        var video11  = VideoList("3", "Naresh", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video11.isVideoChecked=false


        var video12  = VideoList("3", "Ashish", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video12.isVideoChecked=false


        var video13  = VideoList("3", "Shiva", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video13.isVideoChecked=false


        var video14  = VideoList("3", "Narayana", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video14.isVideoChecked=false


        var video15 = VideoList("3", "Dhananjay", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video15.isVideoChecked=false


        var video16 = VideoList("3", "Ching yan", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video16.isVideoChecked=false


        var video17  = VideoList("3", "Vilesh", "great",
            "4", "789", "3", "6",
            "test", "23 Mar 2018", "nothing")
        video17.isVideoChecked=false


        videoListArray.add(video)
        videoListArray.add(video1)
        videoListArray.add(video2)
        videoListArray.add(video3)
        videoListArray.add(video4)
        videoListArray.add(video5)
        videoListArray.add(video6)
        videoListArray.add(video7)
        videoListArray.add(video8)
        videoListArray.add(video9)
        videoListArray.add(video10)
        videoListArray.add(video11)
        videoListArray.add(video12)
        videoListArray.add(video13)
        videoListArray.add(video14)
        videoListArray.add(video15)
        videoListArray.add(video16)
        videoListArray.add(video17)


        view.onDummyDataResult(videoListArray)



    }


}