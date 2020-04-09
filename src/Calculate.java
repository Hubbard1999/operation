import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import entity.OperNum;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

class Calculate {
    private String[]    str       =   { "+", "-", "×", "÷", "(" ,")"," "};
    private Random      random    =   new Random();
    private int         count     =   random.nextInt(3)+1;       //生成1-3 对应运算符数量
    private OperNum[]   op        =   new OperNum[4*count+3];           //表达式共4*count+3个格子
    private int[][]     count_2   =   {{0,6},{4,10}};                                   //当count=2
    private int[][]     count_3   =   {{0,6},{4,10},{8,14},{0,10},{4,14},{0,6,8,14}};   //当count=3
            String      Expression=   "";
            String      Answer    =   "";

    void Random_Create(int r){
        //赋值空间
        for(int i = 0;i < 4*count+3;i++) {
            op[i]               =   new OperNum();
        }

        //随机生成运算符 并赋值到相应的OperNum[]上
        for(int i = 0;i < count;i++) {
            op[4*i+3].type      =   "oper";
            op[4*i+3].operator  =   str[random.nextInt(4)];
            //System.out.println("type:"+op[4*i+3].type+"     operator:"+op[4*i+3].operator);
        }

        //随机生成分子分母 并化简得到真分数 多出的给整数
        for(int i = 0;i < count+1;i++) {
            op[4*i+1].type          =   "num";
            op[4*i+1].numerator     =   random.nextInt(r)+1;
            op[4*i+1].denominator   =   random.nextInt(r)+1;
            op[4*i+1].integer       =   0;
            op[4*i+1].simplify();
//            if(op[4*i+1].integer > 0)
//            System.out.println("化简真分数："+"a/b="+op[4*i+1].integer+"'"+op[4*i+1].numerator+"/"+op[4*i+1].denominator);
//            else
//                System.out.println("化简真分数："+"a/b="+op[4*i+1].numerator+"/"+op[4*i+1].denominator);

        }

        //给其他对象赋“_”
        for(int i = 0;i < 4*count+3;i++) {
            if(op[i].type==null) {
                op[i].type      =   "blank";
                op[i].pocket    =   str[6];
            }
        }

        //空格基础上赋值括号
        int index;
        switch (count){
            case 2 :
                index = random.nextInt(count_2.length);
                System.out.println("括号生成模式:"+index);
                op[count_2[index][0]].type      =   "bracket";
                op[count_2[index][0]].pocket    =   str[4];

                op[count_2[index][1]].type      =   "bracket";
                op[count_2[index][1]].pocket    =   str[5];
                break;
            case 3 :
                index = random.nextInt(count_3.length);
                System.out.println("括号生成模式:"+index);
                op[count_3[index][0]].type      =   "bracket";
                op[count_3[index][0]].pocket    =   str[4];

                op[count_3[index][1]].type      =   "bracket";
                op[count_3[index][1]].pocket    =   str[5];

                if(index==5){
                    op[count_3[index][2]].type      =   "bracket";
                    op[count_3[index][2]].pocket    =   str[4];

                    op[count_3[index][3]].type      =   "bracket";
                    op[count_3[index][3]].pocket    =   str[5];
                }
                break;
        }

        //遍历
        System.out.print("表达式：");
        for(int i = 0;i < 4*count+3;i++) {
            //System.out.print(op[i].type+" ");
            switch (op[i].type) {
                case "blank"    :
                    System.out.print(op[i].pocket);
                    Expression  += op[i].pocket;
                    break;
                case "bracket" :
                    System.out.print(op[i].pocket);
                    Expression  += " "+op[i].pocket+" ";
                    break;
                case "oper"    :
                    System.out.print(op[i].operator);
                    Expression  += op[i].operator;
                    break;
                case "num"     :
                    op[i].print();
                    if(op[i].integer > 0)
                        Expression  += op[i].integer+"'"+op[i].numerator+"/"+op[i].denominator;
                    else if(op[i].denominator==1)
                        Expression  += op[i].numerator;
                    else
                        Expression  += op[i].numerator+"/"+op[i].denominator;
                    break;
            }
        }

    }

    boolean Compute(){

        // 存储操作数的栈
        Stack<OperNum> OpStack          = new Stack<>();
        // 存储转换后的逆波兰式的队列
        Queue<OperNum> reversePolish    = new LinkedList<>();
        //计算
        for(int i = 0;i < 4*count+3;i++){
            //是操作符 判断操作符栈是否为空
            //为空或者栈顶元素为左括号或者当前操作符优先级大于栈顶操作符直接压栈
            switch (op[i].type) {
                case "oper":
                    if (OpStack.isEmpty() || "(".equals(OpStack.peek().pocket) || level(op[i]) > level(OpStack.peek())) {
                        OpStack.push(op[i]);
                    } else {
                        //否则将栈中元素出栈入队，直到遇到大于当前操作符或者遇到左括号时
                        while (!OpStack.isEmpty() && !"(".equals(OpStack.peek().pocket)) {
                            if (level(op[i]) <= level(OpStack.peek())) {
                                reversePolish.offer(OpStack.pop());
                            } else break;

                        }
                        //当前运算符压栈
                        OpStack.push(op[i]);
                    }
                    break;
                //当前op为操作数  直接入队
                case "num":
                    reversePolish.offer(op[i]);
//                System.out.println();
//                System.out.print("当前数字num进列:");
//                op[i].print();
                    break;
                case "bracket":
                    //是右括号 ，将栈中元素弹出入队，直到遇到左括号，左括号出栈，但不入队
                    if (")".equals(op[i].pocket)) {
                        while (!OpStack.isEmpty()) {
                            if ("(".equals(OpStack.peek().pocket)) {
                                OpStack.pop();
                                break;
                            } else {
                                reversePolish.offer(OpStack.pop());
                            }
                        }
                    }
                    //是左括号，压栈
                    else if ("(".equals(op[i].pocket)) {
                        OpStack.push(op[i]);
                    }
                    break;
            }
        }

        while(!OpStack.isEmpty()){
            reversePolish.offer(OpStack.pop());
        }
        System.out.println();
        System.out.print("逆波兰：");
        for(OperNum a:reversePolish){
            a.print();
            System.out.print(" ");
        }
        System.out.println();

        //根据逆波兰式计算
        Stack<OperNum> temp          = new Stack<>();
        OperNum a = new OperNum();
        OperNum b;
        //如果取到的元素是操作数，直接入栈
        while(!reversePolish.isEmpty()) if (reversePolish.peek().type.equals("num")) {
//                System.out.print("出队进栈：");
//                reversePolish.peek().print();
//                System.out.println();
            temp.push(reversePolish.poll());
        }
        //如果是运算符，从栈中弹出2个数进行运算，然后把运算结果入栈
        else {
//                System.out.println();
//                System.out.println("当前运算符："+reversePolish.peek().operator);
            b = temp.pop();
//                System.out.print("b:");
//                b.print();
//                System.out.println();
            a = temp.pop();
//                System.out.print("a:");
//                a.print();System.out.println();
            switch (reversePolish.peek().operator) {
                case "+":
                    //System.out.println("+++++");
                    a = add(a, b);
                    break;
                case "-":
                    //System.out.println("----");
                    a = sub(a, b);
                    if(a.numerator<0){
                        System.out.println("遇到e1<e2");
                        a.print();
                        return false;
                    }
                    break;
                case "×":
                    //System.out.println("****");
                    a = multi(a, b);
                    break;
                case "÷":
                    //System.out.println("////");
                    if (b.numerator == 0 || b.denominator == 0) {
                        System.out.println("遇到除数0");
                        b.print();
                        return false;
                    }
                    a = div(a, b);
                    break;
            }
            reversePolish.poll();
            temp.push(a);
            //System.out.println("计算完后进栈：");a.print();
        }

//        System.out.println();
//        System.out.println("type:"+temp.peek().type);
        System.out.println("逆波兰式结果:");
        a.print();
        if(a.integer > 0)
            Answer  += a.integer+"'"+a.numerator+"/"+a.denominator;
        else if(a.denominator==1)
            Answer  += a.numerator;
        else
            Answer  += a.numerator+"/"+a.denominator;
        temp.pop();
        return true;
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
