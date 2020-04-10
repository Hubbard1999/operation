package controller;

import dao.FileDao;
import service.CheckService;

import static dao.FileDao.getFile;

/**
 * @author Hubbard
 * @date 2020/4/10 22:12
 */
public class CheckController {
    public void checkqna(String queFile, String ansFile) {
        CheckService check =new CheckService();
        String[] question= getFile(queFile,"Q");
        int len= FileDao.getLen(question);
        System.out.println(len);
        String[] rightAns=new String[len];
        int index;
        for(int i=0;i<len;i++){
            index=i+1;
            rightAns[i]=index+"."+ check.string2Answer(question[i]);
        }
        check.writeAnsFile(rightAns);
        String[] answer= getFile(ansFile,"A");


        String correctAns="Correct: ";
        String wrongAns="Wrong: ";
        StringBuilder cList= new StringBuilder("(");
        StringBuilder wList= new StringBuilder("(");
        int     correctNum=0;
        int     wrongNum=0;
        for(int i=0;i<len;i++){
            index=i+1;
            if(rightAns[i].equals(answer[i])){
                correctNum++;
                if(correctNum==1) {
                    cList.append(index);
                } else {
                    cList.append(", ").append(index);
                }
            }
            else{
                wrongNum++;
                if(wrongNum==1) {
                    wList.append(index);
                } else {
                    wList.append(", ").append(index);
                }
            }
        }
        correctAns += correctNum+ cList.toString() +")";
        wrongAns += wrongNum+ wList.toString() +")";
        System.out.println();
        System.out.println(wrongAns);
        System.out.println();
        System.out.println(correctAns);
    }
}
