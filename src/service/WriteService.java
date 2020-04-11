package service;

import dao.FileDao;
import dao.InitDao;
import dao.impl.FileDaoImpl;
import dao.impl.InitDaoImpl;
import entity.OperNum;
import utils.PolishUtil;

import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import static utils.ArithmeticUtil.*;

/**
 * @author Hubbard
 * 生成1-3 对应运算符数量
 * 表达式共4*count+3个格子
 * @date 2020/4/10 13:18
 */
public class WriteService {
    private Random random    =   new Random();
    private int         count     =   random.nextInt(3)+1;
    private int         space     =   4*count+3;
    private OperNum[]   op        =   new OperNum[4*count+3];
    public String     expression =   "";
    public String       answer   =   "";

    public void randomCreate(int r){
        InitDaoImpl initDao = new InitDaoImpl();
        //赋值空间
        op = initDao.mallocSpace(op,space);

        //随机生成运算符 并赋值到相应的OperNum[]上
        op = initDao.mallocOper(op,count);

        //随机生成分子分母 并化简得到真分数 多出的给整数
        op = initDao.mallocNum(op,count,r);

        //赋值“ ”
        op = initDao.mallocBlank(op,space);

        //空格基础上赋值括号
        op = initDao.mallocBracket(op,count);

        //遍历
        expression = initDao.getExpress(op,space);

    }

    public boolean compute(){
        PolishUtil polish = new PolishUtil();
        // 存储转换后的逆波兰式的队列
        Queue<OperNum> reversePolish    = polish.polish(op,space);

        //根据逆波兰式计算
        Stack<OperNum> temp          = new Stack<>();
        OperNum a = new OperNum();
        OperNum b;
        //如果取到的元素是操作数，直接入栈
        while(!reversePolish.isEmpty()) {
            if ("num".equals(reversePolish.peek().type)) {
                temp.push(reversePolish.poll());
            }
            //如果是运算符，从栈中弹出2个数进行运算，然后把运算结果入栈
            else {
                b = temp.pop();
                a = temp.pop();
                switch (reversePolish.peek().operator) {
                    case "+":
                        a = add(a, b);
                        break;
                    case "-":
                        a = sub(a, b);
                        if(a.numerator<0){
                            System.out.println("遇到e1<e2");
                            a.print();
                            return false;
                        }
                        break;
                    case "×":
                        a = multi(a, b);
                        break;
                    case "÷":
                        if (b.numerator == 0 || b.denominator == 0) {
                            System.out.println("遇到除数0");
                            b.print();
                            return false;
                        }
                        a = div(a, b);
                        break;
                    default:break;
                }
                reversePolish.poll();
                temp.push(a);
            }
        }
        //获取答案
        answer = polish.printPolish(a);
        temp.pop();
        return true;
    }

    public void fileWire(String[] formula, String[] answer){
        FileDaoImpl fileWrite = new FileDaoImpl();
        fileWrite.fileWire(formula,answer);
    }

}
