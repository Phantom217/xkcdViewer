package com.paoerful.android.xkcd.ui.main

import android.support.v4.widget.DrawerLayout
import android.widget.{FrameLayout, LinearLayout}
import macroid.FullDsl._
import macroid.{ActivityContextWrapper, IdGenerator, Ui}

import com.paoerful.android.xkcd.ui.commons.ToolbarLayout

trait Layout {

  object Id extends IdGenerator(start = 1000)

  var drawerLayout: Option[DrawerLayout] = slot[DrawerLayout]

  var fragmentMenu: Option[FrameLayout] = slot[FrameLayout]

  var fragmentContext: Option[FrameLayout] = slot[FrameLayout]

  def layout(implicit context: ActivityContextWrapper): DrawerLayout = {
    // TODO
    ???
  }
}
