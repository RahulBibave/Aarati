package com.rahul.aarati

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_kt.*

class MainActivityKt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kt)
        button4.setOnClickListener{
            val intent=Intent(this,Gallery::class.java)
            startActivity(intent)

        }
       /* button5.setOnClickListener{
            val intent=Intent(this,CameraBaseActivity::class.java)
            intent.putExtra("user", 2)

            startActivity(intent)

        }*/
        button6.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            intent.putExtra("user", 3)
            startActivity(intent)
            finish()

        }
        button1.setOnClickListener {
            startActivity(Intent(this,Aarati::class.java))
        }



    }
}
