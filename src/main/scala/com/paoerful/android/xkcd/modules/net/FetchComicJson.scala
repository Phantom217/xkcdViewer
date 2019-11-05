package com.paoerful.android.xkcd.modules.net

import scala.concurrent._, duration._

import gigahorse._, support.okhttp.Gigahorse
import play.api.libs.json._

/** Utility for fetching JSON object from xkcd */
object FetchComicJson {

  /** Fetches the companion json of a given comic.
    *
    * @param num    the comic number
    *               if no number passed, -1 set to default, meaning latest comic
    * @return       returned json object
    */
  def fetchJson(num: Int = -1): JsValue =
    Gigahorse.withHttp(Gigahorse.config) { http =>
      val url =
        if (num == -1) "https://www.xkcd.com/info.0.json"
        else s"https://www.xkcd.com/$num/info.0.json"
      val r = Gigahorse.url(url).get
      val f = http.run(r, parse)
      Await.result(f, 120.seconds)
    }

  def fetchTest(num: Int = -1) =
    Gigahorse.withHttp(Gigahorse.config) { http =>
      val url = s"https://www.xkcd.com/$num/info.0.json"
      val r = Gigahorse.url(url).get
      val f = http.run(r, parse)

      // import scala.concurrent.ExecutionContext.Implicits.global
      // for {
      //   f <- http.run(r, parse)
      //   title = (f \ "title").get
      //   num = (f \ "num").get
      //   img = (f \ "img").get
      //   alt = (f \ "alt").get
      // } yield ( title, num, img, alt )

      Await.result(f, 120.seconds)

    }

  /** Aliasing function to `parse` */
  private def parse = Gigahorse.asString andThen Json.parse
}
