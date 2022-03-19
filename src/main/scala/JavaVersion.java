// https://leetcode.com/submissions/detail/663177256/

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class JavaVersion {
    public static class Bag {
        private int count = 0;
        private boolean inStack = false;

        public void inc() {
            this.count++;
        }

        public void dec() {
            this.count--;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isInStack() {
            return inStack;
        }

        public void setInStack(boolean inStack) {
            this.inStack = inStack;
        }
    }

    public String removeDuplicateLetters(String s) {
        Map<Character, Bag> map = new HashMap<Character, Bag>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, new Bag());
            }
            map.get(c).inc();
        }

        final Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            Bag bag = map.get(c);
            bag.dec();
            if(!bag.inStack){
                if (!stack.isEmpty()) {
                    Character lastStack = stack.peek();
                    Bag lastBag = map.get(lastStack);
                    while (!stack.isEmpty() && c < lastStack && lastBag.count > 0) {
                        lastBag.inStack = false;
                        stack.pop();
                        if (!stack.isEmpty()) {
                            lastStack = stack.peek();
                            lastBag = map.get(lastStack);
                        }
                    }
                }
                stack.push(c);
                map.get(c).inStack = true;
            }
        }
        return stack.stream().map(Object::toString).collect(Collectors.joining());
    }


    public static void main(String[] args) {
        JavaVersion jv = new JavaVersion();
        assert jv.removeDuplicateLetters("zxywooxz").equals( "xywoz");
        assert jv.removeDuplicateLetters("thesqtitxyetpxloeevdeqifkz").equals("hesitxyplovdqfkz");
        assert jv.removeDuplicateLetters("bcabc") .equals( "abc");
        assert jv.removeDuplicateLetters("cbacdcbc") .equals( "acdb");
    }

}
