package entity;

/**
 * @author Hubbard
 * 实体类
 * @date 2020/4/10 20:49
 */
public class OperNum {
    /**类型	操作数:num 运算符:oper 空格:blank 括号:bracket*/
    public String type;

    /**type=操作数*/
    /**     整数	根据分子分母得出*/
    public int integer;
    /**     分子	自定义*/
    public int numerator;
    /**    分母	自定义*/
    public int denominator;

    /**type=运算符 "+", "-", "×", "÷"*/
    public String operator;

    /**type=空格   左括号(   右括号 )   空格_*/
    public String pocket;

    /**一键初始化简函数*/
    public void simplify(){
        int a               =   this.numerator;
        int b               =   this.denominator;
        int r;
        if(a<0) {
            a           =   -a;
        }
        if(b<0) {
            b           =   -b;
        }
        //辗转相除 最后a为公因数
        while(b>0) {
            r = a%b;
            a = b;
            b = r;
        }
        this.numerator      =   this.numerator/a;
        this.denominator    =   this.denominator/a;

        if(this.numerator>this.denominator && this.denominator!=1) {
            int temp;   //保留余数
            this.integer    =   this.integer+this.numerator/this.denominator;
            temp            =   this.numerator/this.denominator;
            this.numerator  =   this.numerator-this.denominator*temp;
        }

    }

    /**带分数化假分数*/
    public void yojan(){
        this.numerator     =   this.numerator+this.integer*this.denominator;
        this.integer       =   0;
    }

    /**打印当前类型下的数据*/
    public void print(){
        String typeNum = "num";
        String typeOper = "oper";
        String typeBracket = "bracket";
        //操作数类型
        if(typeNum.equals(this.type)){
            if(this.integer > 0) {
                System.out.print(this.integer+"'"+this.numerator+"/"+this.denominator);
            } else if(this.denominator==1) {
                System.out.print(this.numerator);
            } else {
                System.out.print(this.numerator+"/"+this.denominator);
            }
        }
        if(typeOper.equals(this.type)) {
            System.out.print(this.operator);
        }
        if(typeBracket.equals(this.type)) {
            System.out.print(this.pocket);
        }

    }
}
