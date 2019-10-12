package com.paoerful.android.xkcd

import scala.language.postfixOps

import android.app.Activity
import android.os.Bundle
import android.widget.{ LinearLayout, Button, TextView }
import android.support.v7.app.AppCompatActivity
import android.graphics.drawable.Animatable
import macroid._
import macroid.Contexts
import macroid.FullDsl._


class MainActivity
  extends Activity
  with Contexts[Activity]
{
  // allows accessing `.value` on TR.resource.constants
  // implicit val context = this

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    setContentView {
      Ui.get {
        l[LinearLayout](
          w[TextView]
        )
        l[LinearLayout](
          w[Button] <~ text("Hello!") <~ padding(top = 8 dp, bottom = 8 dp),
          w[TextView]
        )
      }
    }

    // val vh: TypedViewHolder.main =
    //   TypedViewHolder.setContentView(this, TR.layout.main)
    // vh.text.setText(s"Hello world, from ${TR.string.app_name.value}")
    // vh.image.getDrawable match {
    //   case a: Animatable => a.start()
    //   case _             => // not animatable
    // }
  }
}
