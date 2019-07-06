import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Assignment_1 {
    public static void main(String [] args) throws FileNotFoundException {
        FileInputStream file = new FileInputStream("E:\\a\\input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        Scanner sc = new Scanner(file);
        String line;
        String [] splitA;

        Set keywords = new TreeSet();
        Set identifier = new TreeSet();
        Set moperators = new TreeSet();
        Set loperators = new TreeSet();
        Set numericalv = new TreeSet();
        Set others = new TreeSet();

        while(sc.hasNext()) {
            line = sc.nextLine();
            if (!line.isEmpty()) {
                splitA = line.split("\\W+");
                //splitA = line.split("\\[|,|\\]");
                for(int i=0;i<splitA.length;i++){

                    String temp = splitA[i];
                    if(temp.equals("int") || temp.equals("float") || temp.equals("double") ||
                            temp.equals("long") || temp.equals("short") ||
                            temp.equals("boolean") || temp.equals("char") ||
                            temp.equals("byte") || temp.equals("Boolean") ||
                            temp.equals("for") || temp.equals("if") ||
                            temp.equals("else")){
                        keywords.add(temp);
                    }
                    else if(isNumeric(temp)){
                        numericalv.add(temp);
                    }
                    else{
                        identifier.add(temp);
                    }
                }
                String s = line.replaceAll(" ","");
                splitA = s.split("\\w+");
                for(int i=0;i<splitA.length;i++){
                    String temp = splitA[i];
                    if(temp.equals("+")){
                        moperators.add("+");
                    }
                    else if(temp.equals("-")){
                        moperators.add("-");
                    }
                    else if(temp.equals("*")){
                        moperators.add("*");
                    }
                    else if(temp.equals("/")){
                        moperators.add("/");
                    }
                    else if(temp.equals("%")){
                        moperators.add("%");
                    }
                    else if(temp.equals("=")){
                        moperators.add("=");
                    }
                    else if(temp.equals(">")){
                        loperators.add(">");
                    }
                    else if(temp.equals(">=")){
                        loperators.add(">=");
                    }
                    else if(temp.equals("<")){
                        loperators.add("<");
                    }
                    else if(temp.equals("<=")){
                        loperators.add("<=");
                    }
                    else if(temp.equals(",")){
                        others.add(",");
                    }
                    else if(temp.equals(";")){
                        others.add(";");
                    }
                    else if(temp.equals("[")){
                        others.add("[");
                    }
                    else if(temp.equals("]")){
                        others.add("]");
                    }
                    else if(temp.equals("{")){
                        others.add("{");
                    }
                    else if(temp.equals("}")){
                        others.add("}");
                    }
                    else if(temp.equals("(")){
                        others.add("(");
                    }
                    else if(temp.equals(")")){
                        others.add(")");
                    }
                }
            }
        }
        System.out.println("OUTPUT  : "+"\n"+"KeyWords          :"+keywords+"\n"+"Identifier        :"+
                identifier+"\n"+"Math Operators    :"+moperators+"\n"+"Logical Operators :"+loperators+
                "\n"+"Numerical Values  :"+numericalv+"\n"+"Others            :"+others);


//        Queue q = new LinkedList(identifier);
//        System.out.println(q);
//        while(!q.isEmpty()){
//            System.out.println("lol  " +q.poll());
//        }
    }
    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}

