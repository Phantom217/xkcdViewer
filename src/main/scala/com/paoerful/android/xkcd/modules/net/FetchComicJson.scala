package com.paoerful.android.modules.net

import gigahorse._, support.okhttp.Gigahorse
import scala.concurrent._

object FetchComicJson {

  lazy val http = Gigahorse.http(Gigahorse.config)

  /** Fetches the companion json of a given comic.
    *
    * @param num the comic number
    */
  def fetchJson(num: Int): Future[String] = {
    val comicJson: String = s"https://www.xkcd.com/$num/info.0.json"

    val response = Gigahorse.url(comicJson).get
    val fetch = http.run(response, Gigahorse.asString)
    http.close() // close http connection so connection doesn't hog resources
    fetch
  }
}
