package Assignment_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Regular_Expression {

    static int expression_number;
    static String [] expression_array;
    static int string_number;
    static String [] string_array;
    static int count=0;

    public static void main (String [] args) throws FileNotFoundException {
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        Scanner sc = new Scanner(file);
        String line;
        expression_number=sc.nextInt();
        expression_array = new String[expression_number];

        for(int i=0;i<expression_array.length;i++){
            line=sc.next();
            expression_array[i]=line;
        }

        string_number=sc.nextInt();
        string_array = new String[string_number];

        for(int i=0;i<string_array.length;i++){
            line=sc.next();
            string_array[i]=line;
        }
        int string_array_counter [] = new int[string_number];
        int expression_array_counter [] = new int[expression_number];

        for(int i=0;i<expression_array.length;i++) {
            char[] ch = expression_array[i].toCharArray();

            for(int m=0;m<string_array.length;m++){
                char ch2 [] = string_array[m].toCharArray();
                int counter=0;
                int string_no=0;
                int y=i+1;

                for (int j = 0; j < ch.length; j++) {

                if (j < ch.length - 1) {
                    if (j > 0 && Character.isLetter(ch[j]) && ch[j - 1] == '*'
                            && ch[j + 1] != '*' && ch[j + 1] != '?'
                            && ch[j + 1] != '+') {
                        if (!star_alpha(ch[j],m)) {
                            counter = 1;
                            break;
                        }
                    }

                    else if (Character.isLetter(ch[j]) && ch[j + 1] != '*'
                            && ch[j + 1] != '+' && ch[j + 1] != '?') {
                        if (!alphabet_checker(ch[j],m)) {
                            counter = 1;
                            break;
                        }
                    }
                    else if (Character.isLetter(ch[j]) && ch[j + 1] == '*') {
                        boolean b = star_checker(ch[j],m);
                    }

                    //for question mark

                    else if (Character.isLetter(ch[j]) && ch[j + 1] == '?') {
                        boolean b = question_mark(ch[j],m);
                        j++;
                    }

                    //for plus

                    else if (Character.isLetter(ch[j]) && ch[j + 1] == '+' && ch[j - 1] == '*') {
                        if (ch2[count - 1] == ch[j]) {
                            continue;
                        }
                        else{
                            counter=1;
                            break;
                        }
                    }
                    else if (Character.isLetter(ch[j]) && ch[j + 1] == '+') {
                        boolean b = plus_check(ch[j],m);
                        if (!b) {
                            counter = 1;
                            break;
                        }
                        j++;
                    }

                    //for bracket
                    else if (ch[j] == '(') {
                        j++;
                        Queue<Character> q = new LinkedList();
                        while (ch[j] != ')') {
                            q.add(ch[j]);
                            j++;
                        }

                        char[] queue_array = new char[q.size()];
                        for (int k = 0; k < queue_array.length; k++) {
                            queue_array[k] = q.poll();
                        }
                        if (ch[j + 1] != '*' && ch[j + 1] != '+' && ch[j + 1] != '?') {
                            if (!bracket_checker(queue_array,m)) {
                                counter = 1;
                                break;
                            }
                        }
                        else if (ch[j + 1] == '*') {
                            if (!bracket_checker_star(queue_array,m)) {
                                counter = 1;
                                break;
                            }
                        }
                        else if (ch[j + 1] == '+') {
                            if (!bracket_checker_plus(queue_array,m)) {
                                counter = 1;
                                break;
                            }
                        }
                        else if (ch[j + 1] == '?') {
                            if (!bracket_checker_question(queue_array,m)) {
                                counter = 1;
                                break;
                            }
                        }
                    }
                    else if (ch[j] == '[' && ch[j + 1] != '^') {
                        j++;
                        String char_class = "";
                        while (ch[j] != ']') {
                            char_class = char_class + ch[j];
                            j++;
                        }

                        String[] splitA;
                        splitA = char_class.split("-");
                        String first_char = splitA[0];
                        String second_char = splitA[1];

                        if (ch[j + 1] != '{') {

                            if (ch[j + 1] == '*') {
                                boolean b = character_class_checker_star(first_char, second_char,m);

                            }
                            else if (ch[j + 1] == '+') {
                                boolean b = character_class_checker_plus(first_char, second_char,m);
                                if (!b) {
                                    counter = 1;
                                    break;
                                }
                            }
                            else if (ch[j + 1] == '?') {
                                boolean b = character_class_checker_question(first_char, second_char,m);
                                if (!b) {
                                    counter = 1;
                                    break;
                                }
                            }
                        }
                        else if (ch[j + 1] == '{') {
                            j = j + 2;
                            String digit = "";
                            while (ch[j] != '}') {
                                digit += ch[j];
                                j++;
                            }
                            int times = Integer.parseInt(digit);
                            boolean b = character_class_checker_times(first_char, second_char, times,m);
                            if (!b) {
                                counter = 1;
                                break;
                            }
                        }

                    }
                    else if (ch[j] == '[' && ch[j + 1] == '^') {
                        j = j + 2;
                        Queue<Character> q = new LinkedList();
                        while (ch[j] != ']') {
                            q.add(ch[j]);
                            j++;
                        }
                        char[] queue_array = new char[q.size()];
                        for (int k = 0; k < queue_array.length; k++) {
                            queue_array[k] = q.poll();
                        }

                        if (j == ch.length - 1) {
                            boolean b = negation_checker(queue_array,m);
                            if (!b) {
                                counter = 1;
                                break;
                            }

                        }
                        else if (ch[j + 1] != '{') {

                            if (ch[j + 1] != '*' && ch[j + 1] != '+' && ch[j + 1] != '?') {
                                boolean b = negation_checker(queue_array,m);
                                if (!b) {
                                    counter = 1;
                                    break;
                                }
                            }

                            else if (ch[j + 1] == '*') {
                                boolean b = negation_checker_star(queue_array);

                            }
                            else if (ch[j + 1] == '+') {
                                boolean b = negation_checker_plus(queue_array);
                                System.out.println("bh  "+b);
                                if (!b) {
                                    counter = 1;
                                    break;
                                }
                            }

                            else if (ch[j + 1] == '?') {
                                boolean b = negation_checker_question(queue_array);
                                if (!b) {
                                    counter = 1;
                                    break;
                                }
                            }
                        }
                        else if (ch[j + 1] == '{') {

                            j = j + 2;
                            String digit = "";
                            while (ch[j] != '}') {
                                digit += ch[j];
                                j++;
                            }
                            int times = Integer.parseInt(digit);
                            boolean b = negation_checker_times(queue_array, times,m);

                        }
                    }
                }
                if (j == ch.length - 1) {
                    if (Character.isLetter(ch[j]) && ch[j - 1] == '*') {
                        if (!star_alpha(ch[j],m)) {
                            counter=1;
                            break;

                        }
                    }
                    else if (Character.isLetter(ch[j]) && ch[j-1]=='+') {
                        if (ch2[count - 1] == ch[j]) {
                            continue;
                        }
                        else{
                            counter=1;
                            break;
                        }
                    }
                    else if (Character.isLetter(ch[j])) {
                        if (!alphabet_checker(ch[j],m)) {
                            counter = 1;
                            break;

                        }
                    }

                }
             }
                if(counter==0 && count==string_array[m].length()){
                    int n=i+1;
                    string_array_counter[m]=n;

                }
                count=0;
            }
        }

//        for(int i=0;i<string_array_counter.length;i++){
//            int k=i+1;
//            if(string_array_counter[i]!=0){
//                System.out.println("String : "+k+" -->  YES Matched Expression No : "+string_array_counter[i]);
//            }
//            else {
//                System.out.println("String : "+k+" -->  NO  Matched Expression No : 0");
//            }
//        }


        for(int i=0;i<string_array_counter.length;i++){
            int k=i+1;
            if(string_array_counter[i]!=0){
                System.out.println("YES, "+string_array_counter[i]);
            }
            else {
                System.out.println("NO,  0");
            }
        }
    }

    private static boolean negation_checker_question(char[] q) {
        char [] ch = string_array[0].toCharArray();
        if(Character.isLetter(ch[count])){
            for (int i = 0, qLength = q.length; i < qLength; i++) {
                char aQ = q[i];
                if (ch[count] == aQ) {
                    break;
                }
            }
        }
        return true;
    }

    private static boolean negation_checker_plus(char[] q) {

        char [] ch = string_array[0].toCharArray();
        int a=0;
        for(int i=count;i<ch.length;i++){
            if(Character.isLetter(ch[i])) {
                for (int j = 0; j < q.length; j++) {
                    if (ch[i] == q[j]) {
                        a=0;
                        break;
                    }
                }

            }
            else {
                break;
            }
            count++;
            a++;
        }
        if(a==0){
            return false;
        }
        return true;

    }

    private static boolean negation_checker_star(char[] q) {
        char ch1 [] = string_array[0].toCharArray();
        for(int i=count;i<ch1.length;i++){
            if(Character.isLetter(ch1[i])) {
                for (int j = 0; j < q.length; j++) {
                    if (ch1[i] == q[j]) {
                        return false;
                    }
                }
            }
            else{
                return false;
            }
            count++;
        }

        return true;
    }

    private static boolean negation_checker_times(char[] q, int t, int m) {
        char [] ch = string_array[m].toCharArray();
        int a=0;
        for(int i=0;i<t;i++){
            if(count<=ch.length-1) {
                if (Character.isLetter(ch[count])) {
                    for (int j = 0; j < q.length; j++) {
                        if (ch[count] == q[j]) {
                            a = -1;
                            break;
                        }
                    }
                    if (a == -1) {
                        break;
                    }
                } else {
                    break;
                }
            }
            a++;
            count++;


        }
        if(a!=t){
            return false;
        }
        return true;

    }

    private static boolean negation_checker(char[] q, int m) {
        char ch1 [] = string_array[m].toCharArray();
        char c=ch1[count];
        if(Character.isLetter(c)) {
            for (int i = 0; i < q.length; i++) {
                if (c == q[i]) {
                    return false;
                }
            }
        }
        else{
            return false;
        }
        count++;
        return true;
    }

    private static boolean character_class_checker_times(String s1, String s2, int t, int m) {
        char [] ch = string_array[m].toCharArray();
        char c1=s1.charAt(0);
        char c2=s2.charAt(0);
        int a=0;
        for(int i=0;i<t;i++){
            if(ch[count]>=c1 && ch[count]<=c2){
                a++;
                count++;
            }
            else {
                break;
            }
        }
        if(a!=t){
            return false;
        }

        return true;

    }

    private static boolean character_class_checker_question(String s1, String s2, int m) {

        char [] ch = string_array[m].toCharArray();
        char c1=s1.charAt(0);
        char c2=s2.charAt(0);
        int a=0;
        for(int i=count;i<ch.length;i++){
            if(ch[i]>=c1 && ch[i]<=c2){
                a++;
                count++;
            }
            else {
                break;
            }
        }
        if(a>1){
            return false;
        }

        return true;

    }

    private static boolean character_class_checker_plus(String s1, String s2, int m) {
        char [] ch = string_array[m].toCharArray();
        char c1=s1.charAt(0);
        char c2=s2.charAt(0);
        int a=0;
        for(int i=count;i<ch.length;i++){
            if(ch[i]>=c1 && ch[i]<=c2){
                a++;
                count++;
            }
            else {
                break;
            }
        }
        if(a==0){
            return false;
        }

        return true;
    }

    private static boolean character_class_checker_star(String s1, String s2, int m) {
        char [] ch = string_array[m].toCharArray();
        char c1=s1.charAt(0);
        char c2=s2.charAt(0);

        for(int i=count;i<ch.length;i++){
            if(ch[i]>=c1 && ch[i]<=c2){
                count++;
            }
            else {
                break;
            }
        }
        return true;

    }

    private static boolean plus_check(char c, int m) {

        char [] ch = string_array[m].toCharArray();
        int a=0;
        for(int i=count;i<ch.length;i++){
            if(ch[i]==c){
                a++;
                count++;
            }
            else if(ch[i]!=c){
                break;
            }
        }
        if(a==0){
            return false;
        }
        return true;


    }

    private static boolean bracket_checker_question(char[] q1, int m) {
        char ch1 [] = string_array[m].toCharArray();

        try {
            if (ch1[count] == q1[0] && ch1[count + q1.length - 1] == q1[q1.length - 1]) {
                if (!bracket_checker_star_return(q1, ch1)) {
                    return false;
                }

            } else {
                return true;
            }
        }
        catch(Exception e){
            return true;
        }
        return true;
    }

    private static boolean bracket_checker_plus(char[] q1, int m) {
        char ch1 [] = string_array[m].toCharArray();
        int a=0;
        try {
            if (ch1[count] == q1[0] && ch1[count + q1.length - 1] == q1[q1.length - 1]) {
                a++;
                if (!bracket_checker_star(q1,m)) {
                    return false;
                }

            } else {
                return false;
            }
        }
        catch(Exception e){
            if(a==0){
                return false;
            }
            else{
                return true;
            }
        }
        return true;
    }

    private static boolean question_mark(char c, int m) {
        char [] ch=string_array[m].toCharArray();
        if(count<=ch.length-1) {
            if (ch[count] == c) {
                count++;
                return true;
            }
        }
        return true;
    }

    private static boolean bracket_checker_star(char [] q, int m) {
        char ch [] = string_array[m].toCharArray();
        try {
            while (ch[count] == q[0] && ch[count + q.length - 1] == q[q.length - 1]) {
                boolean b = bracket_checker_star_return(q, ch);
                if (!b) {
                    return false;
                }
            }
        }
        catch(Exception e){
            return true;
        }

        return true;
    }

    private static boolean bracket_checker_star_return(char[] q, char [] ch) {

        String s = "";
        for (int i = 0; i < q.length; i++) {
            s = s + q[i];
        }
        String s1 = "";
        int a=count;
        for (int i = 0; i < q.length; i++) {

            s1 = s1 + ch[a];
            a++;
        }
        if(s.equals(s1)){
            count=count+q.length;
            return true;
        }

        return false;

    }


    private static boolean bracket_checker(char [] q, int m) {
        char[] ch1=string_array[m].toCharArray();
        for(int i=0;i<q.length;i++){
            if(ch1[count]!=(q[i])){
                return false;
            }
            count++;
        }
        return true;
    }

    private static boolean star_alpha(char c, int m) {
        char [] ch = string_array[m].toCharArray();
        if(count>0 && count<ch.length){
            if(ch[count]==c || ch[count-1]==c){
                count++;
                return true;
            }
        }
        else if(count==0){
            if(ch[count]==c){
                count++;
                return true;
            }
        }
        return false;
    }

    private static boolean alphabet_checker(char c, int m){
        char [] ch = string_array[m].toCharArray();
        if(count<=ch.length-1) {
            if (ch[count] == c) {
                count++;
                return true;
            }
        }
        return false;
    }

    private static boolean star_checker(char c, int m){
        char [] ch = string_array[m].toCharArray();
        for(int i=count;i<ch.length;i++){
            if(ch[i]==c){
                count++;
            }
            else if(ch[i]!=c){
                break;
            }
        }
        return true;
    }
}
