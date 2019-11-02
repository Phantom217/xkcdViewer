package com.paoerful.android.xkcd.commons

/** Module adding pipe functionality to Scala 2.11.8 */
object BirdOperator extends Serializable {

  /** Adds chaining method pipe `|>` to every type. */
  implicit class Pipe[A](self: A) {
    /** Converts the value by applying the function `f`.
      *
      * @param f      the function to apply to the value.
      * @tparam Z     the result type of the function `f`.
      * @return       a new value resulting from applying the given function
      *               `f` to this value.
      */
    def |>[Z](f: A => Z): Z = f(self)
  }

  /** Adds chaining method pipe `|>` to tuples with two elements. */
  implicit class Pipe2[A, B](self: (A, B)) {
    /** Converts the value by applying the function `f`.
      *
      * @param f      the function to apply to the value.
      * @tparam Z     the result type of the function `f`.
      * @return       a new value resulting from applying the given function
      *               `f` to this value.
      */
    def |>[Z](f: (A, B) => Z): Z = f.tupled(self)
  }
}
