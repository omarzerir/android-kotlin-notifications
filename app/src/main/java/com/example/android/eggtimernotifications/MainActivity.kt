/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android.eggtimernotifications.ui.EggTimerFragment
import com.example.android.eggtimernotifications.util.createChannel
import com.example.android.eggtimernotifications.util.createPushChannel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EggTimerFragment.newInstance())
                .commitNow()
        }

        createChannel(applicationContext)
        createPushChannel(applicationContext)


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val mTry = intent?.getStringExtra("String")
        intent?.extras?.let {
            val extras = intent.extras
            val data = it["data"]
            Log.e("EXTRAS", "$extras")
            Log.e("DATA", "$data")
        }
        Log.e("TRY", "$mTry")
    }
}