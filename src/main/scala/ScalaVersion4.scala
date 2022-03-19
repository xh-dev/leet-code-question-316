import java.util
// https://leetcode.com/submissions/detail/663195757/

object ScalaVersion4 extends App {
  case class Bag(var count: Int, var inStack: Boolean)

  def removeDuplicateLetters(s: String): String = {
    var map: Map[Char, Bag] = Map.empty[Char, Bag]
    for (char <- s) {
      if (!map.contains(char)) {
        map += char -> Bag(0, inStack = false)
      }
      map(char).count += 1
    }

    val stack = new util.Stack[Char]

    for (char <- s) {
      val value = map(char)
      value.count -= 1

      if (!value.inStack) {
        if(!stack.isEmpty){
          var st_char_value = stack.peek()
          while (!stack.isEmpty && char < st_char_value && map(st_char_value).count > 0) {
            map(st_char_value).inStack=false
            stack.pop()
            if(!stack.isEmpty){
              st_char_value = stack.peek()
            }
          }
        }
        stack.push(char)
        map(char).inStack = true
      }
    }

    val sb = new StringBuilder(stack.size())
    stack.forEach(it=>sb.append(it))

    sb.toString()
  }

  println(removeDuplicateLetters("zxywooxz"))
  assert(removeDuplicateLetters("zxywooxz") == "xywoz")
  assert(removeDuplicateLetters("thesqtitxyetpxloeevdeqifkz") == "hesitxyplovdqfkz")
  assert(removeDuplicateLetters("bcabc") == "abc")
  assert(removeDuplicateLetters("cbacdcbc") == "acdb")
}
