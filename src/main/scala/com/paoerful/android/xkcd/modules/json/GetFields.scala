package com.paoerful.android.xkcd.modules.json

import play.api.libs.json._

import com.paoerful.android.xkcd.modules.net.FetchComicJson._

/** Utility for extracting contents of specific keys within JSON objects */
object GetKeys {

  val json = fetchJson(1312)
  /** Get specified key from a json object
    *
    * @param key the target key in a json object
    *
    * pattern matching to parse the JsResult returned by `validate[String]`
    * and return a `String` without the trailing `,`
    * If the key does not exist in the json object, an error is thrown by
    * `validate[String]`, handles error in the pattern matching and returns
    * "Key not found", this should never happen unless key is mistyped.
    */
  def getKey(json: JsValue, key: String): String =
    (json \ s"$key").validate[String] match {
      case JsSuccess(k, _) => k
      case e: JsError      => "Key not found"
    }

}
