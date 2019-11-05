package com.paoerful.android.xkcd.ui.main

import scala.language.postfixOps
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import macroid._
import macroid.FullDsl._
import com.paoerful.android.xkcd.R

class MainActivity extends AppCompatActivity with Contexts[FragmentActivity] { self =>

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    // setContentView()
  }

}
