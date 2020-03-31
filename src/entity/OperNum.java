package entity;

public class OperNum {

    public String type;        //     类型	操作数:num 运算符:oper 空格or括号:blank

    /*type=操作数*/
    public int integer;        //     整数	根据分子分母得出
    public int numerator;      //     分子	自定义
    public int denominator;    //     分母	自定义

    /*type=运算符*/
    public String operator;    //     "+", "-", "×", "÷"

    /*type=空格*/       //     左括号(   右括号 )   空格_
    public String pocket;

    //一键化简函数
    public void simplify(){
        int a               =   this.numerator;
        int b               =   this.denominator;
        int r;

        while(b>0) {    //辗转相除 最后a为公因数
            r = a%b;
            a = b;
            b = r;
        }
        this.numerator      =   this.numerator/a;
        this.denominator    =   this.denominator/a;

        if(this.numerator>this.denominator) {
            this.integer    =   this.numerator/this.denominator;
            this.numerator  =   this.numerator-this.denominator*this.integer;
        }
    }
}
