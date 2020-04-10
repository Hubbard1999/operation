package utils;

import entity.OperNum;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static utils.ArithmeticUtil.*;

/**
 * @author Hubbard
 * @date 2020/4/10 22:27
 */
public class GetAnswerUtil {
    public String getAnswer(OperNum[] op,int space){
        String      answer    =   "";
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
                assert reversePolish.peek() != null;
                switch (reversePolish.peek().operator) {
                    case "+":
                        a = add(a, b);
                        break;
                    case "-":
                        a = sub(a, b);
                        break;
                    case "×":
                        a = multi(a, b);
                        break;
                    case "÷":
                        a = div(a, b);
                        break;
                    default:break;
                }
                reversePolish.poll();
                temp.push(a);

            }
        }
        answer = polish.printPolish(a);
        temp.pop();
        return answer;
    }
}
