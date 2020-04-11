package service;

import dao.FileDao;
import dao.impl.FileDaoImpl;
import entity.OperNum;
import utils.GetAnswerUtil;

/**
 * @author Hubbard
 * @date 2020/4/10 22:22
 */
public class CheckService {
    public String string2Answer(String ex) {
        String[] temp = ex.substring(1).split("[\\s]");

        OperNum[]   op        =   new OperNum[temp.length];
        for(int i = 0;i < temp.length;i++) {
            op[i]               =   new OperNum();
        }

        for(int i = 0;i < temp.length;i++) {

            //括号
            if(temp[i].matches("^[()]$")){

                op[i].type="bracket";
                op[i].pocket=temp[i];
            }
            //运算符
            else if(temp[i].matches("^[+\\-×÷]$")){
                op[i].type="oper";
                op[i].operator=temp[i];
            }
            //整数
            else if(temp[i].matches("^[0-9]+$")){
                op[i].type="num";
                op[i].integer=0;
                op[i].numerator=Integer.parseInt(temp[i]);
                op[i].denominator=1;
            }
            //真分数
            else if(temp[i].matches("^[0-9]?[0-9]+/[0-9]+$")){
                String[] tempNum = temp[i].split("/");
                op[i].type="num";
                op[i].integer=0;
                op[i].numerator=Integer.parseInt(tempNum[0]);
                op[i].denominator=Integer.parseInt(tempNum[1]);
            }
            //带分数
            else {
                String[] tempNum = temp[i].split("['/]");
                op[i].type="num";
                op[i].integer=Integer.parseInt(tempNum[0]);
                op[i].numerator=Integer.parseInt(tempNum[1]);
                op[i].denominator=Integer.parseInt(tempNum[2]);
            }
        }
        GetAnswerUtil getAns =new GetAnswerUtil();
        return getAns.getAnswer(op,temp.length);

    }

    public void writeAnsFile(String[] answer){
        FileDaoImpl fileWrite = new FileDaoImpl();
        fileWrite.writeAnsFile(answer);
    }

}
