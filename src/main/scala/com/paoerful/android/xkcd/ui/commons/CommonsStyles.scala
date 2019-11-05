package com.paoerful.android.xkcd.ui.commons

import scala.language.postfixOps

import android.support.v7.widget.Toolbar
import android.view.ViewGroup.LayoutParams._
import android.view.{ViewGroup, Gravity}
import macroid.extras.ViewTweaks._
import macroid.FullDsl._
import macroid.{Tweak, ContextWrapper}

import com.paoerful.android.xkcd.R

trait ToolbarStyles {

  def toolbarStyle(
      height: Int
  )(implicit context: ContextWrapper): Tweak[Toolbar] =
    vContentSizeMatchWidth(height) +
      vBackground(R.color.primary)

}
