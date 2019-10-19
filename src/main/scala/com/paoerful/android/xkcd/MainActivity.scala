package com.paoerful.android.xkcd

import scala.language.postfixOps

import android.os.Bundle
import android.widget.{ LinearLayout, TextView, ImageView }
import android.view.ViewGroup.LayoutParams._
import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.{ Toolbar, CardView }
import android.view.{ ContextThemeWrapper, View, Gravity }
import android.widget.ImageView.ScaleType

import macroid._
import macroid.FullDsl._
import macroid.contrib._
import macroid.viewable._
import macroid.extras.FrameLayoutTweaks._
import macroid.extras.LinearLayoutTweaks._
import macroid.extras.ViewTweaks._
import macroid.extras.RecyclerViewTweaks._
import macroid.extras.TextViewTweaks._
import macroid.extras.ResourcesExtras._
import macroid.extras.ImageViewTweaks._
import macroid.extras.TextViewTweaks._
import macroid.extras.CardViewTweaks._
import macroid.{ ActivityContextWrapper, Ui }

import scala.concurrent.ExecutionContext.Implicits.global

// define helpers in a mixable trait
trait Styles {
  // sets text, large font size and a long click handler
  def caption(cap: String)(implicit ctx: ContextWrapper): Tweak[TextView] =
    text(cap) + TextTweaks.large + On.longClick {
      (toast("I'm a caption") <~ gravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL) <~ fry) ~
      Ui(true)
    }

  def comicStyle(implicit ctx: ContextWrapper): Tweak[ImageView] =
    BgTweaks.res(R.drawable.chemistry_nobel) +
    lp[LinearLayout](MATCH_PARENT, WRAP_CONTENT) +
    ivScaleType(ScaleType.CENTER) +
    ivAdjustViewBounds(true)

}

trait ToolbarLayout {

  var toolbar: Option[Toolbar] = slot[Toolbar]

  def toolbarLayout(children: Ui[View]*)(implicit ctx: ActivityContextWrapper): Ui[Toolbar] =
    Ui {
      var darkToolbar = getToolbarThemeDarkActionBar
      children foreach (uiView => darkToolbar.addView(uiView.get))
      toolbar = Some(darkToolbar)
      darkToolbar
    }

  private def getToolbarThemeDarkActionBar(implicit context: ActivityContextWrapper) = {
    val contextTheme = new ContextThemeWrapper(context.getOriginal, R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
    val darkToolbar = new Toolbar(contextTheme)
    darkToolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light)
    darkToolbar
  }
}

class MainActivity extends Activity with Contexts[Activity] with Styles {

  var comic: Option[ImageView] = slot[ImageView]

  val altText: Option[TextView] = slot[TextView]

  var cap: Option[TextView] = slot[TextView]

  var image: Option[ImageView] = slot[ImageView]

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    val view = l[CardView](
      l[LinearLayout](
        w[ImageView] <~ wire(comic) <~ comicStyle
      ) <~
        padding( left = 16 dp, right = 16 dp) <~
        llGravity(Gravity.CENTER_VERTICAL),
      w[TextView] <~
        caption("Comic Title Here") <~
        wire(cap) <~
        tvGravity(Gravity.TOP)
    ) <~
      vElevation( 4.0f )

    setContentView(view.get)
  }
}
