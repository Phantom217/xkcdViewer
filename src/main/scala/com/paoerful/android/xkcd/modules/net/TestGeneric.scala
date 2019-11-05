// package com.paoerful.android.xkcd.modules.net

// import java.io.File
// import scala.concurrent._, duration._, ExecutionContext.Implicits._

// import gigahorse._, support.okhttp.Gigahorse
// import play.api.libs.json._

// object ComicHandler {

  def webRequest[A](num: Int = -1, maybeDl: Boolean = false)(url: String): A =
    Gigahorse.withHttp(Gigahorse.config) { http =>
      val r = Gigahorse.url(url).get
      Await.response(f, 120.seconds)
      val jsonOrDl = if (maybeDl) new File(new File("target"), s"$num.png")
                     else if (num != -1) s"https://www.xkcd.com/$num/info.0.json"
                     else "https://www.xkcd.com/info.0.json"
      val r = Gigahorse.url()
      val f = http.download(r, s"${if (!maybeDl) parse else js})
    }

}
