import entity.OperNum;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class CheckQA {
     static void main(String QueFile,String AnsFile) {

        String[] Question=GetFile(QueFile,"Q");
        int len=0;
        for(String a:Question){
            if(a==null) break;
            len++;
        }
        System.out.println(len);
        String[] RightAns=new String[len];
        int index;
        for(int i=0;i<len;i++){
            index=i+1;
            RightAns[i]=index+"."+String2Answer(Question[i]);
        }
        FileWire(RightAns);
        String[] Answer= GetFile(AnsFile,"A");
//        for(String a:Answer){
//            if(a==null) break;
//            System.out.println(a);
//        }

        String CorrectAns="Correct: ";
        String WrongAns="Wrong: ";
        StringBuilder CList= new StringBuilder("(");
        StringBuilder WList= new StringBuilder("(");
        int     CorrectNum=0;
        int     WrongNum=0;
        for(int i=0;i<len;i++){
            index=i+1;
            if(RightAns[i].equals(Answer[i])){
                CorrectNum++;
                if(CorrectNum==1)
                    CList.append(index);
                else
                    CList.append(", ").append(index);
            }
            else{
                WrongNum++;
                if(WrongNum==1)
                    WList.append(index);
                else
                    WList.append(", ").append(index);
            }
        }
        CorrectAns += CorrectNum+ CList.toString() +")";
        WrongAns += WrongNum+ WList.toString() +")";
        System.out.println(WrongAns);
        System.out.println();
        System.out.println(CorrectAns);
    }

     static String[] GetFile(String File,String type){
        int position=0;
        String[] bufstring=new String[10000];
        //打开带读取的文件
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(File));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line=null;
        while(true) {
            try {
                assert br != null;
                if ((line=br.readLine())==null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            bufstring[position]=line;
            position++;
        }
        try {
            br.close();//关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        if("Q".equals(type)){
            for(int i=0;i<position;i++) {
                String temp = String.valueOf(i+1);
                assert bufstring[i] != null;
                bufstring[i]=bufstring[i].substring(temp.length()+1);
                assert bufstring[i] != null;
            }
        }

        return bufstring;
    }

    private static void FileWire(String[] answer){
        OutputStream os2 = null;
        try {
            os2 = new FileOutputStream("TempAnswers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert os2 != null;
        PrintWriter pw2=new PrintWriter(os2);
        for(String s:answer) {
            if(s==null) break;
            pw2.println(s);//每输入一个数据，自动换行，便于我们每一行每一行地进行读取
        }
        pw2.close();
        try {
            os2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String String2Answer(String ex) {
//        System.out.println(ex);

//        System.out.println(ex.substring(1));
        String[] temp = ex.substring(1).split("[\\s]");
//        System.out.println("fenge");
//        for(String a:temp){
//            System.out.println(a);
//        }

        OperNum[]   op        =   new OperNum[temp.length];
        for(int i = 0;i < temp.length;i++) {
            op[i]               =   new OperNum();
        }

        for(int i = 0;i < temp.length;i++) {

            //括号
            if(temp[i].matches("^[()]$")){
//                System.out.println("括号temp["+i+"]:"+temp[i]);
                op[i].type="bracket";
                op[i].pocket=temp[i];
            }
            //运算符
            else if(temp[i].matches("^[+\\-×÷]$")){
 //               System.out.println("运算符temp["+i+"]:"+temp[i]);
                op[i].type="oper";
                op[i].operator=temp[i];
            }
            //整数
            else if(temp[i].matches("^[0-9]+$")){
//                System.out.println("整数temp["+i+"]:"+temp[i]);
                op[i].type="num";
                op[i].integer=0;
                op[i].numerator=Integer.parseInt(temp[i]);
                op[i].denominator=1;
            }
            //真分数
            else if(temp[i].matches("^[0-9]?[0-9]+/[0-9]+$")){
//                System.out.println("真分数temp["+i+"]:"+temp[i]);
                String[] TempNum = temp[i].split("/");
//                for(String a:TempNum){
//                    System.out.println("真分数："+a);
//                }
                op[i].type="num";
                op[i].integer=0;
                op[i].numerator=Integer.parseInt(TempNum[0]);
                op[i].denominator=Integer.parseInt(TempNum[1]);
            }
            //带分数
            else {
//                System.out.println("带分数temp["+i+"]:"+temp[i]);
                String[] TempNum = temp[i].split("['/]");
//                for(String a:TempNum){
//                    System.out.println("带分数："+a);
//                }
                op[i].type="num";
                op[i].integer=Integer.parseInt(TempNum[0]);
                op[i].numerator=Integer.parseInt(TempNum[1]);
                op[i].denominator=Integer.parseInt(TempNum[2]);
            }
        }
//        System.out.println("len"+temp.length);
//        print(op);
        return GetAnswer(op);


    }

//    private static void print(OperNum[] op){
//        System.out.print("表达式：");
//        for (OperNum operNum : op) {
//            switch (operNum.type) {
//                case "blank":
//                    System.out.print(operNum.pocket);
//                    break;
//                case "bracket":
//                    System.out.print(operNum.pocket);
//                    break;
//                case "oper":
//                    System.out.print(operNum.operator);
//                    break;
//                case "num":
//                    operNum.print();
//                    break;
//            }
//        }
//    }

   private static String GetAnswer(OperNum[] op){
       String      Answer    =   "";
        // 存储操作数的栈
        Stack<OperNum> OpStack          = new Stack<>();
        // 存储转换后的逆波兰式的队列
        Queue<OperNum> reversePolish    = new LinkedList<>();
        for (OperNum operNum : op) {

            switch (operNum.type) {
                case "oper":
                    if (OpStack.isEmpty() || "(".equals(OpStack.peek().pocket) || level(operNum) > level(OpStack.peek())) {
                        OpStack.push(operNum);
                    } else {
                        while (!OpStack.isEmpty() && !"(".equals(OpStack.peek().pocket)) {
                            if (level(operNum) <= level(OpStack.peek())) {
                                reversePolish.offer(OpStack.pop());
                            } else break;

                        }

                        OpStack.push(operNum);
                    }
                    break;
                case "num":
                    reversePolish.offer(operNum);
                    break;
                case "bracket":
                    if (")".equals(operNum.pocket)) {
                        while (!OpStack.isEmpty()) {
                            if ("(".equals(OpStack.peek().pocket)) {
                                OpStack.pop();
                                break;
                            } else {
                                reversePolish.offer(OpStack.pop());
                            }
                        }
                    }
                    else if ("(".equals(operNum.pocket)) {
                        OpStack.push(operNum);
                    }
                    break;
            }
        }
        while(!OpStack.isEmpty()){
            reversePolish.offer(OpStack.pop());
        }
//        System.out.println();
//        System.out.print("逆波兰：");
//        for(OperNum a:reversePolish){
//            a.print();
//            System.out.print(" ");
//        }
//        System.out.println();

        //根据逆波兰式计算
        Stack<OperNum> temp          = new Stack<>();
        OperNum a = new OperNum();
        OperNum b;
        //如果取到的元素是操作数，直接入栈
        while(!reversePolish.isEmpty()) if (reversePolish.peek().type.equals("num")) {
            temp.push(reversePolish.poll());
        }
        //如果是运算符，从栈中弹出2个数进行运算，然后把运算结果入栈
        else {
            b = temp.pop();
            a = temp.pop();
            assert reversePolish.peek() != null;
            switch (reversePolish.peek().operator) {
                case "+":
                    a = add(a, b);
                    break;
                case "-":
                    a = sub(a, b);
                    break;
                case "×":
                    a = multi(a, b);
                    break;
                case "÷":
                    a = div(a, b);
                    break;
            }
            reversePolish.poll();
            temp.push(a);

        }

//        System.out.println("逆波兰式结果:");
//        a.print();
       if(a.integer > 0)
           Answer  += a.integer+"'"+a.numerator+"/"+a.denominator;
       else if(a.denominator==1)
           Answer  += a.numerator;
       else
           Answer  += a.numerator+"/"+a.denominator;
        temp.pop();

        return Answer;
    }

    private static int level(OperNum a){
        if("×".equals(a.operator)||"÷".equals(a.operator))
            return 1;
        if("+".equals(a.operator)||"-".equals(a.operator))
            return 0;
        return -1;
    }

    private static OperNum add(OperNum a, OperNum b){
        a.integer       =   a.integer+b.integer;
        a.numerator     =   a.numerator*b.denominator+b.numerator*a.denominator;
        a.denominator   =   a.denominator*b.denominator;
        //  System.out.println("化简前a：");a.print();
        a.simplify();
        //   System.out.println("化简后a：");a.print();
        return a;
    }

    private static OperNum sub(OperNum a, OperNum b){
        a.yojan();
        b.yojan();
        a.integer       =   a.integer-b.integer;
        a.numerator     =   a.numerator*b.denominator-b.numerator*a.denominator;
        a.denominator   =   a.denominator*b.denominator;
        a.simplify();
        return a;
    }



    private static OperNum multi(OperNum a, OperNum b){
        a.yojan();
        b.yojan();

        a.numerator     =   a.numerator*b.numerator;
        a.denominator   =   a.denominator*b.denominator;

        a.simplify();
        return a;
    }

    private static OperNum div(OperNum a, OperNum b){
        a.yojan();
        b.yojan();

        a.numerator     =   a.numerator*b.denominator;
        a.denominator   =   a.denominator*b.numerator;

        a.simplify();
        return a;
    }



}
