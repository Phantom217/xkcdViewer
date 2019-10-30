package com.paoerful.android.modules.net

import gigahorse._, support.okhttp.Gigahorse
import scala.concurrent._

object FetchComicJson {

  /** Fetches the companion json of a given comic.
    *
    * @param num the comic number
    */
  def fetchJson(num: Int): String =
    Gigahorse.withHttp(Gigahorse.config) { http =>
      val r = Gigahorse.url(s"https://xkcd.com/$num/info.0.json").get
      val f = http.run(r, Gigahorse.asString)
      Await.result(f, 120.seconds)
    }
}
