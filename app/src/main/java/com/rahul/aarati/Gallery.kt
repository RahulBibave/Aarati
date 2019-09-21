package com.rahul.aarati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_gallery.*

class Gallery : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        val arrayList2 = ArrayList<Int>(5)

        arrayList2.add(R.drawable.splash)
        arrayList2.add(R.drawable.demo_image)
        arrayList2.add(R.drawable.namokar)
        arrayList2.add(R.drawable.aarthi_image)
        val adapter=AdapterGallery(this,arrayList2)
        recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recycler.adapter=adapter



    }
}
