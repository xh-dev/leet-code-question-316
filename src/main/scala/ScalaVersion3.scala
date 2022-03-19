// https://leetcode.com/submissions/detail/663054772/

object ScalaVersion3 extends App {
  case class Bag(var count: Int, var inStack: Boolean)

  def removeDuplicateLetters(s: String): String = {
    var map: Map[Char, Bag] = Map.empty[Char, Bag]
    for (char <- s) {
      if (!map.contains(char)) {
        map += char -> Bag(0, inStack = false)
      }
      map(char).count += 1
    }

    var len = 0
    var st_char = s.toList

    for (char <- s) {
      val value = map(char)
      value.count -= 1

      if (!value.inStack) {
        if(len > 0){
          var st_char_value = st_char(len-1)
          while (len > 0 && char < st_char_value && map(st_char_value).count > 0) {
            map(st_char_value).inStack=false
            len -= 1
            if(len > 0){
              st_char_value = st_char(len -1)
            }
          }
        }
        st_char = st_char.updated(len, char)
        map(char).inStack = true
        len += 1
      }
    }

    st_char.take(len).mkString
  }

  println(removeDuplicateLetters("zxywooxz"))
  assert(removeDuplicateLetters("zxywooxz") == "xywoz")
  assert(removeDuplicateLetters("thesqtitxyetpxloeevdeqifkz") == "hesitxyplovdqfkz")
  assert(removeDuplicateLetters("bcabc") == "abc")
  assert(removeDuplicateLetters("cbacdcbc") == "acdb")
}
