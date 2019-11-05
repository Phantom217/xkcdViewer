package com.paoerful.android.xkcd.ui.commons

import scala.language.postfixOps

import android.support.v7.widget.Toolbar
import android.view.{ContextThemeWrapper, View}
import macroid.extras.ResourcesExtras._
import macroid.FullDsl._
import macroid.{ActivityContextWrapper, Ui}

import com.paoerful.android.xkcd.R

trait ToolbarLayout extends ToolbarStyles {

  var toolbar = slot[Toolbar]

  def toolbarLayout(
      children: Ui[View]*
  )(implicit context: ActivityContextWrapper): Ui[Toolbar] =
    Ui {
      val darkToolbar = getToolbarThemeDarkActionBar
      children foreach (uiView => darkToolbar.addView(uiView.get))
      toolbar = Some(darkToolbar)
      darkToolbar
    } <~ toolbarStyle(resGetDimensionPixelSize(R.dimen.height_toolbar))

  def expandedToolbarLayout(
      children: Ui[View]*
  )(height: Int)(implicit context: ActivityContextWrapper): Ui[Toolbar] =
    Ui {
      val darkToolbar = getToolbarThemeDarkActionBar
      children foreach (uiView => darkToolbar.addView(uiView.get))
      toolbar = Some(darkToolbar)
      darkToolbar
    } <~ toolbarStyle(height)

  private def getToolbarThemeDarkActionBar(
      implicit context: ActivityContextWrapper
  ) = {
    val contextTheme = new ContextThemeWrapper(
      context.getOriginal,
      R.style.ThemeOverlay_AppCompat_Dark_ActionBar
    )
    val darkToolbar = new Toolbar(contextTheme)
    darkToolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light)
    darkToolbar
  }
}
