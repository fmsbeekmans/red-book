package chapter5

sealed trait Stream[+A] {
  import Stream._

  def headOption: Option[A] =
    this match {
      case Empty => None
      case Cons(h, _) => Some(h())
    }

  def toList: List[A] =
    this match {
      case Empty => Nil
      case Cons(h, t) => h() :: t().toList
    }

  def take(i: Int): Stream[A] =
    this match {
      case Empty => empty[A]
      case Cons(h, t) =>
        if (i == 1) cons(h(), empty)
        else if (i > 1) {
          cons(h(), t().take(i - 1))
        } else empty[A]
    }

}

case object Empty extends Stream[Nothing]
case class Cons[+A](
  h: () => A,
  t: () => Stream[A]
) extends Stream[A]

object Stream {
  def cons[A](
    hd: => A,
    tl: => Stream[A]
  ): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))

}
