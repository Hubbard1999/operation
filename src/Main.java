import java.util.Random;

class  OperNum {
    //a为分子 b为分母 c为整数
    static int a;
    static int b;
    static int c;
}


public class Main {

    public static void main(String[] args) {
        String[] str = { "+", "-", "×", "÷", "(" ,")","_"};
        Random random = new Random();
        int count = random.nextInt(3)+1;    //生成1-3
        String[] operator = new String[count];       //1-3个运算符
        OperNum[] op = new OperNum[count+1];       //2-4个操作数
        Object[] object = new Object[4*count+3];    //object类对象 表达式共4*count+3个格子

        for(int i=0;i<4*count+3;i++) {
            object[i] = str[6];                     //给4*count+3个object对象赋“_”
        }

        for(int i=0;i<count;i++) {                  //随机生成运算符 并赋值到相应的object上
            operator[i] = str[random.nextInt(4)];
            object[4*i+3] = operator[i];
            System.out.println(operator[i]);
        }


        for(int i=0;i<count+1;i++) {                //随机生成分子分母ab 并化简得到真分数 多出的给c
            op[i] = new OperNum();                  //需要赋空间
            op[i].a = random.nextInt(100)+1;
            op[i].b = random.nextInt(100)+1; //分子分母缺一个化简函数
            op[i].c = 0;
            System.out.println(gcd(op[i].a,op[i].b));
            System.out.println("a/b="+op[i].a+"/"+op[i].b);
            int gcd = gcd(op[i].a,op[i].b);
            op[i].a = op[i].a/gcd;
            op[i].b = op[i].b/gcd;
            System.out.println("化简后："+"a/b="+op[i].a+"/"+op[i].b);
            if(op[i].a>op[i].b) {
                op[i].c = op[i].a/op[i].b;
                op[i].a = op[i].a-op[i].b*op[i].c;
            }
            if(op[i].c>0) System.out.println("化简真分数："+"a/b="+op[i].c+"'"+op[i].a+"/"+op[i].b);
            else    System.out.println("化简真分数："+"a/b="+op[i].a+"/"+op[i].b);

//              object[4*i+1] = op[i];      //将操作数对象赋值到对应的object位置
//            System.out.println(op[i].getClass().toString());
//            System.out.println(object[4*i+1].getClass().toString());
//            System.out.println("比较:"+object[4*i+1].equals(op[i]));

        }

//        for(int i=0;i<4*count+3;i++) {
//            if(i==1||i==5||i==9||i==13) {
//                if(object[i]>0)
//            }
//            else System.out.print(object[i]);
//        }
    }



    public static int  gcd(int a,int b) {
        int r;
        while(b>0)
        {
            r=a%b;
            a=b;
            b=r;
        }
        return a;
    }
}
