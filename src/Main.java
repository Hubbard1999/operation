import entity.OperNum;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in_r    = new Scanner(System.in);
        Scanner in_n    = new Scanner(System.in);
        System.out.println("输入范围：");
        int r           = in_r.nextInt();
        System.out.println("输入生成个数：");
        int n           = in_n.nextInt();
        Calculate test  = new Calculate();
        for(int i = 0;i<n;i++){
            System.out.println();
            System.out.printf("第%d道",i+1);
            test.Random_Create(r);
            if(!test.Compute()) i=i-1;
            System.out.println();
        }

//        String[]    str     = { "+", "-", "×", "÷", "(" ,")"," "};
//        Random      random  = new Random();
//        int         count   = random.nextInt(3)+1;       //生成1-3 对应运算符数量
//        OperNum[]   op      = new OperNum[4*count+3];           //表达式共4*count+3个格子
//        int[][] count_2 = {{0,6},{4,10}};                                   //当count=2
//        int[][] count_3 = {{0,6},{4,10},{8,14},{0,10},{4,14},{0,6,8,14}};   //当count=3

//        System.out.println("count_2.length:"+count_2.length);
//        count_2.length:2
//
//        for(int i = 0;i < count_3.length;i++){
//            for(int j = 0;j < count_3[i].length;j++){
//                System.out.print(count_3[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println("count_3.length:"+count_3.length);

//        //赋值空间
//        for(int i = 0;i < 4*count+3;i++) {
//            op[i]               =   new OperNum();
//        }
//
//        //随机生成运算符 并赋值到相应的OperNum[]上
//        for(int i = 0;i < count;i++) {
//            op[4*i+3].type      =   "oper";
//            op[4*i+3].operator  =   str[random.nextInt(4)];
//            System.out.println("type:"+op[4*i+3].type+"     operator:"+op[4*i+3].operator);
//        }
//
//        //随机生成分子分母 并化简得到真分数 多出的给整数
//        for(int i = 0;i < count+1;i++) {
//            op[4*i+1].type          =   "num";
//            op[4*i+1].numerator     =   random.nextInt(10)+1;
//            op[4*i+1].denominator   =   random.nextInt(10)+1;
//            op[4*i+1].integer       =   0;
//            op[4*i+1].simplify();
////            if(op[4*i+1].integer > 0)
//                System.out.println("化简真分数："+"a/b="+op[4*i+1].integer+"'"+op[4*i+1].numerator+"/"+op[4*i+1].denominator);
////            else
////                System.out.println("化简真分数："+"a/b="+op[4*i+1].numerator+"/"+op[4*i+1].denominator);
//
//        }
//
//        //给其他对象赋“_”
//        for(int i = 0;i < 4*count+3;i++) {
//            if(op[i].type==null) {
//                op[i].type      =   "blank";
//                op[i].pocket    =   str[6];
//            }
//        }
//
//        //空格基础上赋值括号
//        int index;
//        switch (count){
//            case 2 :
//                index = random.nextInt(count_2.length);
//                System.out.println("index:"+index);
//                op[count_2[index][0]].type      =   "bracket";
//                op[count_2[index][0]].pocket    =   str[4];
//
//                op[count_2[index][1]].type      =   "bracket";
//                op[count_2[index][1]].pocket    =   str[5];
//                break;
//            case 3 :
//                index = random.nextInt(count_3.length);
//                System.out.println("index:"+index);
//                op[count_3[index][0]].type      =   "bracket";
//                op[count_3[index][0]].pocket    =   str[4];
//
//                op[count_3[index][1]].type      =   "bracket";
//                op[count_3[index][1]].pocket    =   str[5];
//
//                if(index==5){
//                    op[count_3[index][2]].type      =   "bracket";
//                    op[count_3[index][2]].pocket    =   str[4];
//
//                    op[count_3[index][3]].type      =   "bracket";
//                    op[count_3[index][3]].pocket    =   str[5];
//                }
//                break;
//        }
//
//        //遍历
//        for(int i = 0;i < 4*count+3;i++) {
//            //System.out.print(op[i].type+" ");
//            switch (op[i].type) {
//                case "blank"    :
//                    System.out.print(op[i].pocket);
//                    break;
//                case "bracket" :
//                    System.out.print(op[i].pocket);
//                    break;
//                case "oper"    :
//                    System.out.print(op[i].operator);
//                    break;
//                case "num"     :
//                    op[i].print();
//                    break;
//            }
//        }

        //op长度
        //System.out.println("op length:"+op.length);



//        // 存储操作数的栈
//        Stack<OperNum> OpStack          =  new Stack<OperNum>();
//        // 存储转换后的逆波兰式的队列
//        Queue<OperNum> reversePolish    =  new LinkedList<OperNum>();
//        //计算
//        for(int i = 0;i < 4*count+3;i++){
//            //是操作符 判断操作符栈是否为空
//            //为空或者栈顶元素为左括号或者当前操作符优先级大于栈顶操作符直接压栈
//            if(op[i].type.equals("oper")){
//                if(OpStack.isEmpty()||"(".equals(OpStack.peek().pocket)||level(op[i])>level(OpStack.peek())){
//                    OpStack.push(op[i]);
//                }
//                else{
//                    //否则将栈中元素出栈入队，直到遇到大于当前操作符或者遇到左括号时
//                    while(!OpStack.isEmpty() && !"(".equals(OpStack.peek().pocket)){
//                        if(level(op[i]) <= level(OpStack.peek())){
//                            reversePolish.offer(OpStack.pop());
//                        }else break;
//
//                    }
//                    //当前运算符压栈
//                    OpStack.push(op[i]);
//                }
//            }
//            //当前op为操作数  直接入队
//            else if(op[i].type.equals("num")){
//                reversePolish.offer(op[i]);
////                System.out.println();
////                System.out.print("当前数字num进列:");
////                op[i].print();
//            }
//            else if(op[i].type.equals("bracket")){
//                //是右括号 ，将栈中元素弹出入队，直到遇到左括号，左括号出栈，但不入队
//                if(")".equals(op[i].pocket)){
//                    while(!OpStack.isEmpty()){
//                        if("(".equals(OpStack.peek().pocket)){
//                            OpStack.pop();
//                            break;
//                        }
//                        else{
//                            reversePolish.offer(OpStack.pop());
//                        }
//                    }
//                }
//                //是左括号，压栈
//                else if("(".equals(op[i].pocket)){
//                    OpStack.push(op[i]);
//                }
//            }
//        }
//
//        while(!OpStack.isEmpty()){
//            reversePolish.offer(OpStack.pop());
//        }
//        System.out.println();
//        System.out.print("逆波兰：");
//        for(OperNum a:reversePolish){
//            a.print();
//            System.out.print(" ");
//        }
//        System.out.println();
//
//        //根据逆波兰式计算
//        Stack<OperNum> temp          =  new Stack<OperNum>();
//        OperNum a = new OperNum();
//        OperNum b = new OperNum();
//        while(!reversePolish.isEmpty()){
//            //如果取到的元素是操作数，直接入栈
//            if(reversePolish.peek().type.equals("num")){
//                System.out.print("出队进栈：");
//                reversePolish.peek().print();
//                System.out.println();
//                temp.push(reversePolish.poll());
//            }
//            //如果是运算符，从栈中弹出2个数进行运算，然后把运算结果入栈
//            else{
//                System.out.println();
//                System.out.println("当前运算符："+reversePolish.peek().operator);
//                b = temp.pop();
//                System.out.print("b:");
//                b.print();
//                System.out.println();
//                a = temp.pop();
//                System.out.print("a:");
//                a.print();
//                System.out.println();
//                switch (reversePolish.peek().operator){
//                    case "+" :
//                        System.out.println("+++++");
//                        a = add(a,b);
//                        break;
//                    case "-" :
//                        System.out.println("----");
//                        a = sub(a,b);
//                        break;
//                    case "×" :
//                        System.out.println("****");
//                        a = multi(a,b);
//                        break;
//                    case "÷" :
//                        System.out.println("////");
//                        a = div(a,b);
//                        break;
//                }
//                reversePolish.poll();
//                temp.push(a);
//                System.out.println("计算完后进栈：");a.print();
//            }
//        }
//
//        System.out.println();
//        System.out.println("type:"+temp.peek().type);
//        System.out.println("逆波兰式结果:");
//        a.print();
//        temp.pop();

   }


//
//    public static int level(OperNum a){
//        if("×".equals(a.operator)||"÷".equals(a.operator))
//            return 1;
//        if("+".equals(a.operator)||"-".equals(a.operator))
//            return 0;
//        return -1;
//    }
//
//    public static OperNum add(OperNum a,OperNum b){
//        a.integer       =   a.integer+b.integer;
//        a.numerator     =   a.numerator*b.denominator+b.numerator*a.denominator;
//        a.denominator   =   a.denominator*b.denominator;
//      //  System.out.println("化简前a：");a.print();
//        a.simplify();
//     //   System.out.println("化简后a：");a.print();
//        return a;
//    }
//
//    public static OperNum sub(OperNum a,OperNum b){
//        a.integer       =   a.integer-b.integer;
//        a.numerator     =   a.numerator*b.denominator-b.numerator*a.denominator;
//        a.denominator   =   a.denominator*b.denominator;
//        a.simplify();
//        return a;
//    }
//
//
//
//    public static OperNum multi(OperNum a,OperNum b){
//        a.numerator     =   a.numerator+a.integer*a.denominator;
//        a.integer       =   0;
//        b.numerator     =   b.numerator+b.integer*b.denominator;
//        b.integer       =   0;
//
//        a.numerator     =   a.numerator*b.numerator;
//        a.denominator   =   a.denominator*b.denominator;
//
//        a.simplify();
//        return a;
//    }
//
//    public static OperNum div(OperNum a,OperNum b){
//        a.numerator     =   a.numerator+a.integer*a.denominator;
//        a.integer       =   0;
//        b.numerator     =   b.numerator+b.integer*b.denominator;
//        b.integer       =   0;
//
//        a.numerator     =   a.numerator*b.denominator;
//        a.denominator   =   a.denominator*b.numerator;
//
//        a.simplify();
//        return a;
//    }
}
