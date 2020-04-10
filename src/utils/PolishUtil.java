package utils;

import entity.OperNum;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static utils.ArithmeticUtil.level;

/**
 * @author Hubbard
 * @date 2020/4/10 16:24
 * 返回波兰式
 */
public class PolishUtil {
    public Queue<OperNum> polish(OperNum[] op, int space){
        // 存储操作数的栈
        Stack<OperNum> opStack          = new Stack<>();
        // 存储转换后的逆波兰式的队列
        Queue<OperNum> reversePolish    = new LinkedList<>();
        //计算
        for(int i = 0;i < space;i++){
            //是操作符 判断操作符栈是否为空
            //为空或者栈顶元素为左括号或者当前操作符优先级大于栈顶操作符直接压栈
            switch (op[i].type) {
                case "oper":
                    if (opStack.isEmpty() || "(".equals(opStack.peek().pocket) || level(op[i]) > level(opStack.peek())) {
                        opStack.push(op[i]);
                    } else {
                        //否则将栈中元素出栈入队，直到遇到大于当前操作符或者遇到左括号时
                        while (!opStack.isEmpty() && !"(".equals(opStack.peek().pocket)) {
                            if (level(op[i]) <= level(opStack.peek())) {
                                reversePolish.offer(opStack.pop());
                            } else {
                                break;
                            }

                        }
                        //当前运算符压栈
                        opStack.push(op[i]);
                    }
                    break;
                //当前op为操作数  直接入队
                case "num":
                    reversePolish.offer(op[i]);
                    break;
                case "bracket":
                    //是右括号 ，将栈中元素弹出入队，直到遇到左括号，左括号出栈，但不入队
                    if (")".equals(op[i].pocket)) {
                        while (!opStack.isEmpty()) {
                            if ("(".equals(opStack.peek().pocket)) {
                                opStack.pop();
                                break;
                            } else {
                                reversePolish.offer(opStack.pop());
                            }
                        }
                    }
                    //是左括号，压栈
                    else if ("(".equals(op[i].pocket)) {
                        opStack.push(op[i]);
                    }
                    break;
                default:break;
            }
        }

        while(!opStack.isEmpty()){
            reversePolish.offer(opStack.pop());
        }
        System.out.println();
        System.out.print("逆波兰：");
        for(OperNum a:reversePolish){
            a.print();
            System.out.print(" ");
        }
        System.out.println();
        return reversePolish;
    }

    public String printPolish(OperNum a){
        String answer="";
        System.out.println("逆波兰式结果:");
        a.print();
        if(a.integer > 0) {
            answer += a.integer+"'"+a.numerator+"/"+a.denominator;
        } else if(a.denominator==1) {
            answer += a.numerator;
        } else {
            answer += a.numerator+"/"+a.denominator;
        }
        return answer;
    }

}
