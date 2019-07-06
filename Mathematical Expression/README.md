## Problem Description:
In this assignment, you will implement how to evaluate a mathematical expression. The program will ask the user to input a value(say n). Then user will input n lines of input each of which contains an identifier and its corresponding value. Then program will ask the user again to input a value(say m). Then user will input m lines of expressions. Your job is to calculate the final value for each of the given expression using first n lines of input. If you can't evaluate any expression from given numbers of identifiers then output 'Compilation Error'. Allowed mathematical operators are +(add), -(subtract), x(multiply), /(divide).
### Input:
```
3
a = 1
b = 2
c = 2
2
a * b + a * c + b * c
a * c - b / c + c * c

```
### Output:
```
8
5
```
### Input:
```
4
g = 2
p = 3
t = 1
w = 2
3
g + p * t - w * p
t - g + t - w
e + t * t - m
```
### Output:
```
-1
-2
Compilation Error
```
