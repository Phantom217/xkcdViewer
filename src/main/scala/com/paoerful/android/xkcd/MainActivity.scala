// package com.paoerful.android.xkcd

// import scala.language.postfixOps

// import android.app.Activity
// import android.os.Bundle
// import android.support.v7.widget.{ Toolbar, CardView }
// import android.view.ViewGroup.LayoutParams._
// import android.view.{ ContextThemeWrapper, View, Gravity }
// import android.widget.ImageView.ScaleType
// import android.widget.{ LinearLayout, TextView, ImageView }
// import macroid._
// import macroid.contrib._
// import macroid.extras.ImageViewTweaks._
// import macroid.extras.LinearLayoutTweaks._
// import macroid.extras.TextViewTweaks._
// import macroid.extras.ViewTweaks._
// import macroid.FullDsl._
// import macroid.{ ActivityContextWrapper, Ui }


// define helpers in a mixable trait
// trait Styles {
//   // sets text, large font size and a long click handler
//   def caption(cap: String)(implicit ctx: ContextWrapper): Tweak[TextView] =
//     text(cap) + TextTweaks.large + On.longClick {
//       (toast("I'm a caption") <~ gravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL) <~ fry) ~
//       Ui(true)
//     }

//   def altTextCaption(implicit ctx: ContextWrapper): Tweak[TextView] =
//     TextTweaks.large +
//     On.longClick {
//       (toast("Grab from JSON") <~ gravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL) <~ fry) ~
//       Ui(true)
//     }

//   /** Style tweaks for the comic image layout */
//   def comicStyle(implicit ctx: ContextWrapper): Tweak[ImageView] =
//     BgTweaks.res(R.drawable.chemistry_nobel) +
//     lp[LinearLayout](MATCH_PARENT, WRAP_CONTENT) +
//     ivScaleType(ScaleType.CENTER) +
//     ivAdjustViewBounds(true)

// }

// trait ToolbarLayout {

//   var toolbar: Option[Toolbar] = slot[Toolbar]

//   def toolbarLayout(children: Ui[View]*)(implicit ctx: ActivityContextWrapper): Ui[Toolbar] =
//     Ui {
//       var darkToolbar = getToolbarThemeDarkActionBar
//       children foreach (uiView => darkToolbar.addView(uiView.get))
//       toolbar = Some(darkToolbar)
//       darkToolbar
//     }

//   private def getToolbarThemeDarkActionBar(implicit context: ActivityContextWrapper) = {
//     val contextTheme = new ContextThemeWrapper(context.getOriginal, R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
//     val darkToolbar = new Toolbar(contextTheme)
//     darkToolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light)
//     darkToolbar
//   }
// }

// class MainActivity extends Activity with Contexts[Activity] with Styles {

//   var comic: Option[ImageView] = slot[ImageView]

//   var altText: Option[TextView] = slot[TextView]

//   var cap: Option[TextView] = slot[TextView]

//   // The comic's number, will be grabbed from the JSON
//   val comicNumber: Integer = 1312
//   // The comic title, will be grabbed from the JSON
//   val comicTitle: String = "Comic Title Here"

//   override def onCreate(savedInstanceState: Bundle) = {
//     super.onCreate(savedInstanceState)

//     // TODO: abstract gui
//     // TODO: comic - on long click show alt text anchored to bottom of screen
//     // TODO: alt text - on long click hide self
//     // TODO: add toolbar
//     // TODO: add toolbar drawer, for navigating to other pages in app
//     val view = l[CardView](
//       l[LinearLayout](
//         w[ImageView] <~ wire(comic) <~ comicStyle
//       ) <~
//         padding( left = 16 dp, right = 16 dp) <~
//         llGravity(Gravity.CENTER_VERTICAL),
//       w[TextView] <~
//         wire(cap) <~
//         caption(s"$comicNumber: $comicTitle") <~
//         tvGravity(Gravity.TOP)
//     ) <~
//       vElevation( 4.0f )

//     setContentView(view.get)
//   }
// }
