package com.test.myapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.test.myapp.R
import com.test.myapp.adapter.VideoAdapter
import com.test.myapp.modal.UserInfo
import com.test.myapp.modal.VideoInfo
import com.test.myapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var materialDialog: MaterialDialog
    private lateinit var mainPresenter: MainPresenter
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        videoAdapter = VideoAdapter(baseContext)
        recyclerView.adapter = videoAdapter

        mainPresenter.getDataFromServer()
    }

    override fun showLoading() {

        materialDialog = MaterialDialog.Builder(this)
            .title("My App")
            .content("Please Wait")
            .progress(true, 0)
            .show()

    }

    override fun hideLoading() {
        materialDialog.dismiss()
    }

    override fun onGetSuccessResult(videoInfo: VideoInfo) {


        videoAdapter.setDataValue(videoInfo.data.videos)
        videoAdapter.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onGetFailureResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginResultSuccess() {

        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item!!.itemId == R.id.item1) {
            val userInfo = UserInfo("ashish@gmail.com", "123456")

            mainPresenter.doLogin(userInfo)

        }

        return true
    }


}
