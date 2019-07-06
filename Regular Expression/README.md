## Problem Description:
In this assignment, you will work on regular expression. For simplicity, we will assume that there is a fixed set of regular expressions. We will not consider out of these. But you must not use any built-in method or package in your implementation. If you need any method, you will write that.In Regular Expression (RE), '*' means occurrence of zero of more characters, '+' indicates happening of one or more characters, '?'  means only once or not at all occurrence, '[ ]' indicates happening of inclusive characters, '^' indicates that next characters will not be used in the pattern, '[a-d]{3}' indicates that valid string will be exactly of  length 3 inclusively using a, b, c, d. The following table contains a fixed set of RE that will be used in our assignment.

| Description  | RE  | Valid | Invalid |
| :---------------- |:---------------:| ------------:|------------:|
| Zero or more |a(bc)*de| ade  abcbcde |abde  abcbde|
| One or more |	a(bc)+de |	abcde  abcbcde |	ade  abc |
| Once or not at all | a(bc)?de | ade  abcde  | abc   abcbcde |
| Character classes | [a-m]* | blackmail  imbecile | above  below|
| Negation of character classes | [^aeiou] | b  c | a e |
| Exactly N times | [^aeiou]{6} | rhythm  syzygy | rhythms  allowed |

User will be asked first to input an integer value n followed by n lines of regular expressions. Then user will be asked to input another integer value m followed by m lines of text string. Your job is to say 'YES' with index of RE if the text string is valid according to any of given regular expressions. Otherwise, say 'NO' with zero index. I repeat, you must not use any built-in regular expression method or package.But you can take help from Cloud.

### Input:
```
2
ab*c*d
a*b(cd)+e?f
3
acccd
abbbbbcccc
bcdcdef


```
### Output:
```
YES, 1
NO, 0
YES, 2

```
### Input:
```
3
[a-c]{3}cab+(da)*f
db*a[^def]{2}gh
def[k-p]*p+
5
defkmnpmpp
acbcabbf
pqrstdd
dbaabggh
dbbbbamkgh

```
### Output:
```
YES, 3
YES, 1 
NO, 0
NO, 0
YES, 2
```
