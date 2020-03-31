import entity.OperNum;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String[]    str     = { "+", "-", "×", "÷", "(" ,")","_"};
        Random      random  = new Random();
        int         count   = random.nextInt(3)+1;       //生成1-3
        OperNum[]   op      = new OperNum[4*count+3];           //表达式共4*count+3个格子

        //赋值空间
        for(int i = 0;i < 4*count+3;i++) {
            op[i]               =   new OperNum();
        }

        //随机生成运算符 并赋值到相应的OperNum[]上
        for(int i = 0;i < count;i++) {
            op[4*i+3].type      =   "oper";
            op[4*i+3].operator  =   str[random.nextInt(4)];
            System.out.println("type:"+op[4*i+3].type+"     operator:"+op[4*i+3].operator);
        }

        //随机生成分子分母 并化简得到真分数 多出的给整数
        for(int i = 0;i < count+1;i++) {
            op[4*i+1].type          =   "num";
            op[4*i+1].numerator     =   random.nextInt(100)+1;
            op[4*i+1].denominator   =   random.nextInt(100)+1;
            op[4*i+1].integer       =   0;
            op[4*i+1].simplify();
            if(op[4*i+1].integer > 0)
                System.out.println("化简真分数："+"a/b="+op[4*i+1].integer+"'"+op[4*i+1].numerator+"/"+op[4*i+1].denominator);
            else
                System.out.println("化简真分数："+"a/b="+op[4*i+1].numerator+"/"+op[4*i+1].denominator);

        }

        //给其他对象赋“_”
        for(int i = 0;i < 4*count+3;i++) {
            if(op[i].type==null) {
                op[i].type  =   "blank";
                op[i].pocket=   str[6];
            }
        }

        //遍历
        for(int i = 0;i < 4*count+3;i++) {
            //System.out.print(op[i].type+" ");
            switch (op[i].type) {
                case "blank" :
                    System.out.print(op[i].pocket);
                    break;
                case "oper" :
                    System.out.print(op[i].operator);
                    break;
                case "num"  :
                    if(op[i].integer > 0)
                        System.out.print(op[i].integer+"'"+op[i].numerator+"/"+op[i].denominator);
                    else
                        System.out.print(op[i].numerator+"/"+op[i].denominator);
            }
        }







    }

}
