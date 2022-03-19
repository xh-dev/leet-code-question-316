# [Question - Remove duplicated letters](https://leetcode.com/problems/remove-duplicate-letters/)

[Reference URL](https://www.w3resource.com/java-exercises/basic/java-basic-exercise-200.php)

## Performance
Java version has much better performance than scala version.

JavaVersion2 used the string buffer to reduce time for creating immutable string each time. 

And the result is quite good, the time usage decreased from 14ms to 6ms.

In the other hand scala's version contains minimum of 8xx ms in time usage, even though i'm a expert in the scala, so that the code should be fine-tuned for better performance.

But there is clear fact that the scala version run slower than java version.