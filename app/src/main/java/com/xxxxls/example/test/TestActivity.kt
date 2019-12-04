package com.xxxxls.example.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xxxxls.example.R
import com.xxxxls.xsuper.support.falseLet
import com.xxxxls.xsuper.support.trueLet
import kotlinx.android.synthetic.main.app_activity_test.*

/**
 *
 * @author Max
 * @date 2019-11-26.
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_test)

        tv_test_01.setOnClickListener {
            val boolean = true

            boolean.trueLet {
                Log.e("SUPER", "true - trueLet - trueLet")
            }.elseLet {
                Log.e("SUPER", "true - trueLet - elseLet")
            }

            boolean.falseLet {
                Log.e("SUPER", "true - falseLet - trueLet")
            }.elseLet {
                Log.e("SUPER", "true - falseLet - elseLet")
            }

            (!boolean).trueLet {
                Log.e("SUPER", "false - trueLet - trueLet")
            }.elseLet {
                Log.e("SUPER", "false - trueLet - elseLet")
            }

            (!boolean).falseLet {
                Log.e("SUPER", "false - falseLet - trueLet")
            }.elseLet {
                Log.e("SUPER", "false - falseLet - elseLet")
            }
        }
    }
}