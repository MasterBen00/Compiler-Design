package Assignment_2;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Assignment_2 {

    static int identifier;
    static char[] iden_array;
    static int[] iden_value;
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream file = new FileInputStream("input2.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        Scanner sc = new Scanner(file);
        String line;
        String [] splitA;


        identifier=sc.nextInt();
        System.out.println(identifier);
        iden_array=new char[identifier];
        iden_value=new int[identifier];


        for (int i = 0; i < identifier; i++) {
            line=sc.next();
            System.out.println(line);
            splitA=line.split("=");
            iden_array[i] = splitA[0].charAt(0);
            iden_value[i] = Integer.parseInt(splitA[1]);
        }

        int expression = sc.nextInt();
        System.out.println(expression);
        String[] expr_array = new String[expression];

        for (int i = 0; i < expression; i++) {
            expr_array[i] = sc.next();
            System.out.println(expr_array[i]);
        }

        System.out.println("\n");
        System.out.println("OUTPUT  :\n");
        Stack s = new Stack();

        for (int i = 0; i < expr_array.length; i++) {
            char[] c = expr_array[i].toCharArray();
            if(isOperator(c[c.length-1])){
                System.out.println("Compilation error ");
            }

            else if(isValid(c)){

                Queue q = new LinkedList();


                for (int j = 0; j < c.length; j++) {
                    if (operand(c[j], iden_array)) {

                        q.add(c[j]);
                    } else if (c[j] == '(') {
                        s.push('(');
                    } else if (c[j] == ')') {
                        while (!s.isEmpty()) {

                            q.add(s.pop());
                            if (s.peek().equals('(')) {
                                s.pop();
                                break;
                            }
                        }
                    } else if (c[j] == '+' || c[j] == '-' || c[j] == '*' || c[j] == '/') {
                        if (s.isEmpty()) {
                            s.push(c[j]);
                        } else if (s.peek().equals('(')) {
                            s.push(c[j]);
                        } else if (precedence(c[j]) > precedence((Character) s.peek())) {
                            s.push(c[j]);
                        } else {
                            if (c[j] == '+' || c[j] == '-' || c[j] == '*' || c[j] == '/') {
                                char temp = (char) s.peek();
                                if (precedence(c[j]) <= precedence(temp)) {
                                    q.add(s.pop());
                                    j = j - 1;

                                }
                            }
                        }
                    }
                }
                while (!s.isEmpty()) {

                    q.add(s.pop());
                }
                Queue q1 = new LinkedList(q);

                System.out.println("Postfix form  :");

                while (!q.isEmpty()) {
                    System.out.print(q.poll() + " ");

                }
                System.out.println();
                System.out.println("result " + result(q1));
                System.out.println();
                s.removeAllElements();

            }
            else {
                System.out.println("Compilation error ");
                System.out.println();
            }
        }
    }


    public static boolean isValid(char [] c4) {
        int count =0;
        int counter=0;
        for(int i=0;i<c4.length;i++) {
            if (!isOperator(c4[i]) && c4[i]!='(' && c4[i]!=')'){
                count++;

                for (int j = 0; j < iden_array.length; j++) {
                    if (iden_array[j] == c4[i]) {
                        counter++;
                    }
                }
            }
        }
        if(count==counter){
            return true;
        }
        return false;
    }



    public static boolean operand(char n, char[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == n) {
                return true;
            }
        }
        return false;
    }

    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '(':
                return 20;
            default:
                throw new IllegalArgumentException("Operator unknown: " + ch);

        }
    }

    public static int result(Queue queue) {
        Stack<Integer> s1= new Stack();
        char[] ch = new char[queue.size()];
        int k = 0;
        while (!queue.isEmpty()) {
            ch[k] = (char) queue.poll();
            k++;
        }

        for (int i = 0; i < ch.length; i++) {
            char ch1 = ch[i];

            if (isOperator(ch1)) {
                int a= s1.pop();
                int b= s1.pop();

                int ab=0;

                switch (ch1) {
                    case '+':
                        ab=a+b;
                        s1.push(ab);
                        break;
                    case '*':
                        ab=a*b;
                        s1.push(ab);
                        break;
                    case '-':
                        ab=-a+b;
                        s1.push(ab);
                        break;
                    case '/':
                        ab=(b/a);
                        s1.push(ab);
                        break;
                }
            }
            else if(!isOperator(ch1)){
                s1.push(value(ch1));

            }
        }
        return s1.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }

    public static int value(char v){
        for(int i=0;i<iden_array.length;i++){
            if(iden_array[i]==v){
                return iden_value[i];
            }
        }
        return -100;


    }


}

