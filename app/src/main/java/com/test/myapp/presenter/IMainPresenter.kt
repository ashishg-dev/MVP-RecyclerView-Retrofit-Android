package com.test.myapp.presenter

import com.test.myapp.modal.UserInfo

interface IMainPresenter {

    fun getDataFromServer()

    fun doLogin(userInfo: UserInfo)
}