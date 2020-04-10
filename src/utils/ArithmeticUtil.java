package utils;

import entity.OperNum;

/**
 * @author Hubbard
 * @date 2020/4/10 14:28
 */
public class ArithmeticUtil {

    /**
     * 比较运算符的优先级
     */
    public static int level(OperNum a){
        String add = "+",sub = "-",multi = "×",div = "÷";
        if(multi.equals(a.operator)||div.equals(a.operator)) {
            return 1;
        }
        if(add.equals(a.operator)||sub.equals(a.operator)) {
            return 0;
        }
        return -1;
    }

    /**
     * 加法运算
     */
    public static OperNum add(OperNum a, OperNum b){
        a.integer       =   a.integer+b.integer;
        a.numerator     =   a.numerator*b.denominator+b.numerator*a.denominator;
        a.denominator   =   a.denominator*b.denominator;
        a.simplify();
        return a;
    }

    /**
     * 减法运算
     */
    public static OperNum sub(OperNum a, OperNum b){
        a.yojan();
        b.yojan();
        a.integer       =   a.integer-b.integer;
        a.numerator     =   a.numerator*b.denominator-b.numerator*a.denominator;
        a.denominator   =   a.denominator*b.denominator;
        a.simplify();
        return a;
    }

    /**
     * 乘法运算
     */
    public static OperNum multi(OperNum a, OperNum b){
        a.yojan();
        b.yojan();

        a.numerator     =   a.numerator*b.numerator;
        a.denominator   =   a.denominator*b.denominator;

        a.simplify();
        return a;
    }

    /**
     * 除法运算
     */
    public static OperNum div(OperNum a, OperNum b){
        a.yojan();
        b.yojan();

        a.numerator     =   a.numerator*b.denominator;
        a.denominator   =   a.denominator*b.numerator;

        a.simplify();
        return a;
    }
}
