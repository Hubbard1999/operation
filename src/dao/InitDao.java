package dao;

import entity.OperNum;

import java.util.Random;

/**
 * @author Hubbard
 * @date 2020/4/10 13:30
 */
public class InitDao {
    String[]    str       =   { "+", "-", "×", "÷", "(" ,")"," "};

    public OperNum[] mallocSpace(OperNum[] op,int space){
        //赋值空间
        for(int i = 0;i < space;i++) {
            op[i]               =   new OperNum();
        }
        return op;
    }

    public OperNum[] mallocOper(OperNum[] op,int count){
        //随机生成运算符 并赋值到相应的OperNum[]上
        Random random   =   new Random();
        for(int i = 0;i < count;i++) {
            op[4*i+3].type      =   "oper";
            op[4*i+3].operator  =   str[random.nextInt(4)];
        }
        return op;
    }

    public OperNum[] mallocNum(OperNum[] op,int count,int r){
        Random random   =   new Random();
        for(int i = 0;i < count+1;i++) {
            //随机生成分子分母 并化简得到真分数 多出的给整数
            op[4*i+1].type          =   "num";
            op[4*i+1].numerator     =   random.nextInt(r)+1;
            op[4*i+1].denominator   =   random.nextInt(r)+1;
            op[4*i+1].integer       =   0;
            op[4*i+1].simplify();
        }
        return op;
    }

    public OperNum[] mallocBlank(OperNum[] op,int space){
        for(int i = 0;i < space;i++) {
            if(op[i].type==null) {
                op[i].type      =   "blank";
                op[i].pocket    =   str[6];
            }
        }
        return op;
    }

    public OperNum[] mallocBracket(OperNum[] op,int count){
        Random random   =   new Random();
        int[][]     count2    =   {{0,6},{4,10}};
        int[][]     count3    =   {{0,6},{4,10},{8,14},{0,10},{4,14},{0,6,8,14}};
        //空格基础上赋值括号
        int index;
        int model5 = 5;
        switch (count){
            case 2 :
                index = random.nextInt(count2.length);
                System.out.println("括号生成模式:"+index);
                op[count2[index][0]].type      =   "bracket";
                op[count2[index][0]].pocket    =   str[4];

                op[count2[index][1]].type      =   "bracket";
                op[count2[index][1]].pocket    =   str[5];
                break;
            case 3 :
                index = random.nextInt(count3.length);
                System.out.println("括号生成模式:"+index);
                op[count3[index][0]].type      =   "bracket";
                op[count3[index][0]].pocket    =   str[4];

                op[count3[index][1]].type      =   "bracket";
                op[count3[index][1]].pocket    =   str[5];

                if(index==model5){
                    op[count3[index][2]].type      =   "bracket";
                    op[count3[index][2]].pocket    =   str[4];

                    op[count3[index][3]].type      =   "bracket";
                    op[count3[index][3]].pocket    =   str[5];
                }
                break;
            default:break;
        }
        return op;
    }

    public String getExpress(OperNum[] op,int space){
        String expression = "";
        System.out.print("表达式：");
        for(int i = 0;i < space;i++) {
            switch (op[i].type) {
                case "blank"    :
                    System.out.print(op[i].pocket);
                    expression += op[i].pocket;
                    break;
                case "bracket" :
                    System.out.print(op[i].pocket);
                    expression += " "+op[i].pocket+" ";
                    break;
                case "oper"    :
                    System.out.print(op[i].operator);
                    expression += op[i].operator;
                    break;
                case "num"     :
                    op[i].print();
                    if(op[i].integer > 0) {
                        expression += op[i].integer+"'"+op[i].numerator+"/"+op[i].denominator;
                    } else if(op[i].denominator==1) {
                        expression += op[i].numerator;
                    } else {
                        expression += op[i].numerator+"/"+op[i].denominator;
                    }
                    break;
                default:break;
            }
        }
        return expression;
    }
}
