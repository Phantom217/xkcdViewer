package com.paoerful.android.xkcd.modules.net

import java.io.File
import scala.concurrent._, duration._, ExecutionContext.Implicits._

import gigahorse._, support.okhttp.Gigahorse

object DownloadComic {

  /** Downloads the target comic image
    *
    * @param imgUrl     url to get the image
    * @param num        number of the comic, need for file name
    * @return           returns an image obtained from imgUrl
    */
  def getComic(imgUrl: String, num: Int): File =
    Gigahorse.withHttp(Gigahorse.config) { http =>
      // TODO: use android directory path
      // TODO: Generalize file name in order to overwrite on new file if
      //       user hasn't opted into offline storage (also add said option)
      val file = new File(new File("target"), s"$num.png")
      val r = Gigahorse.url(imgUrl)
      val f = http.download(r, file)
      Await.result(f, 120.seconds)
    }

}
