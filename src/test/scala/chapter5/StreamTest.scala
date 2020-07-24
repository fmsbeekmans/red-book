package chapter5

import utest._

object StreamTest extends TestSuite {
  val tests = Tests {
    test("toList") {
      test("Empty.toList") {
        assert(Empty.toList == Nil)
      }
      test("Singleton stream toList") {
        assert(Stream(1).toList == List(1))
      }
      test("Stream toList") {
        assert(Stream(2, 4, 6, 8).toList == List(2, 4, 6, 8))
      }

      test("toList evaluates a new stream once") {
        var i = 0

        def tick[A](a: A) = {
          i += 1
          a
        }

        val stream = Stream(tick(4), tick(3), tick(2))
        stream.toList
        assert(i == 3)
        stream.toList
        assert(i == 3)
      }
    }
    test("take") {
      test("0") {
        assert(Stream(97f).take(0).toList == Nil)
      }
      test("empty, 0") {
        assert(Stream().take(0).toList == Nil)
      }
      test("empty, n") {
        assert(Stream().take(4).toList == Nil)
      }
      test("some") {
        val from = Stream('a', 'b', 'c', 'd', 'e')

        assert(from.take(3).toList == List('a', 'b', 'c'))
      }
      test("all") {
        val from = Stream('a', 'b', 'c', 'd')

        assert(from.take(4).toList == List('a', 'b', 'c', 'd'))
      }
      test("more") {
        val from = Stream('a')

        assert(from.take(4).toList == List('a'))
      }
      test("less") {
        val from = Stream('a')

        assert(from.take(-3).toList == Nil)
      }

      test("does not evaluate past i") {
        assert(
          Cons[String](
            () => "Tick",
            () =>
              Cons[String](
                () => "Tick",
                () =>
                  Cons[String](
                    () => "Tick",
                    () =>
                      Cons[String](
                        (() => { throw new Exception("Boom") }): () => String,
                        () => Stream.empty[String]
                      )
                  )
              )
          )
            .take(3)
            .toList == List.fill(3)("Tick")
        )
      }
    }
  }

}
