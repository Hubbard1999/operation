import entity.OperNum;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String[]    str     = { "+", "-", "×", "÷", "(" ,")","_"};
        Random      random  = new Random();
        int         count   = random.nextInt(3)+1;       //生成1-3 对应运算符数量
        OperNum[]   op      = new OperNum[4*count+3];           //表达式共4*count+3个格子
        int[][] count_2 = {{0,6},{4,10}};                                   //当count=2
        int[][] count_3 = {{0,6},{4,10},{8,14},{0,10},{4,14},{0,6,8,14}};   //当count=3

        System.out.println("count_2.length:"+count_2.length);
        for(int i = 0;i < count_3.length;i++){
            for(int j = 0;j < count_3[i].length;j++){
                System.out.print(count_3[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("count_3.length:"+count_3.length);

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
            op[4*i+1].numerator     =   random.nextInt(10)+1;
            op[4*i+1].denominator   =   random.nextInt(10)+1;
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
                op[i].type      =   "blank";
                op[i].pocket    =   str[6];
            }
        }

        //空格基础上赋值括号
        int index;
        switch (count){
            case 2 :
                index = random.nextInt(count_2.length);
                System.out.println("index:"+index);
                op[count_2[index][0]].type      =   "bracket";
                op[count_2[index][0]].pocket    =   str[4];

                op[count_2[index][1]].type      =   "bracket";
                op[count_2[index][1]].pocket    =   str[5];
                break;
            case 3 :
                index = random.nextInt(count_3.length);
                System.out.println("index:"+index);
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
        for(int i = 0;i < 4*count+3;i++) {
            //System.out.print(op[i].type+" ");
            switch (op[i].type) {
                case "blank"    :
                    System.out.print(op[i].pocket);
                    break;
                case "bracket" :
                    System.out.print(op[i].pocket);
                    break;
                case "oper"    :
                    System.out.print(op[i].operator);
                    break;
                case "num"     :
                    if(op[i].integer > 0)
                        System.out.print(op[i].integer+"'"+op[i].numerator+"/"+op[i].denominator);
                    else if(op[i].denominator==1)
                        System.out.print(op[i].numerator);
                     else
                        System.out.print(op[i].numerator+"/"+op[i].denominator);
            }
        }







    }

}
