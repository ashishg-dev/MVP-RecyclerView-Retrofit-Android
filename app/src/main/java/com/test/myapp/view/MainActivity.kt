package com.test.myapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.Menu
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.test.myapp.R
import com.test.myapp.adapter.VideoAdapter
import com.test.myapp.modal.VideoInfo
import com.test.myapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var materialDialog: MaterialDialog
    private lateinit var mainPresenter: MainPresenter
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var searchView: SearchView

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu!!.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search Title")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                if (TextUtils.isEmpty(newText)) {
                    videoAdapter.getFilter().filter("")
                } else {
                    videoAdapter.getFilter().filter(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
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

    override fun onGetFailureResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }




}
