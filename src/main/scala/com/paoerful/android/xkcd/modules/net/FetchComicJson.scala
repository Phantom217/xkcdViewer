package com.paoerful.android.modules.net

import scala.concurrent._, duration._

import gigahorse._, support.okhttp.Gigahorse
import play.api.libs.json._

/** Utility for fetching JSON object from xkcd */
object FetchComicJson {

  /** Fetches the companion json of a given comic.
    *
    * @param num the comic number
    */
  def fetchJson(num: Int): JsValue =
    Gigahorse.withHttp(Gigahorse.config) { http =>
      val r = Gigahorse.url(s"https://xkcd.com/$num/info.0.json").get
      val f = http.run(r, parse)
      Await.result(f, 120.seconds)
    }

  private def parse = Gigahorse.asString andThen Json.parse
}
