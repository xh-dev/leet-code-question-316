// https://leetcode.com/submissions/detail/663011896/

object ScalaVersion2 extends App {
  case class Bag(count: Int, inStack: Boolean) {
    def inc(): Bag = this.copy(count = count + 1)

    def dec(): Bag = this.copy(count = count - 1)
  }

  def removeDuplicateLetters(s: String): String = {
    var map: Map[Char, Bag] = Map.empty[Char, Bag]
    for (char <- s) {
      if (map.contains(char)) {
        map += char -> map(char).inc()
      } else {
        map += char -> Bag(1, inStack = false)
      }
    }

    var len = 0
    var st_char = s.toList

    for (char <- s) {
      map += char -> map(char).dec()

      if (!map(char).inStack) {
        while (len > 0 && char < st_char(len - 1) && map(st_char(len - 1)).count > 0) {
          len -= 1
          val tempChar = st_char(len)
          map += (tempChar -> map(tempChar).copy(inStack = false))
        }
        st_char = st_char.updated(len, char)
        map += (char -> map(char).copy(inStack = true))
        len += 1
      }
    }

    st_char.take(len).mkString
  }

  assert(removeDuplicateLetters("zxywooxz") == "xywoz")
  assert(removeDuplicateLetters("thesqtitxyetpxloeevdeqifkz") == "hesitxyplovdqfkz")
  assert(removeDuplicateLetters("bcabc") == "abc")
  assert(removeDuplicateLetters("cbacdcbc") == "acdb")
}
