package controller;

import service.WriteService;

/**
 * @author Hubbard
 * 题目生成相关
 * @date 2020/4/10 20:49
 */
public class WriteController {
    /**
     * @Author Hubbard
     * @Description //TODO 对给定范围r和道数n 进行生成题目和对应答案操作
     * @Date 2020/4/11
     * @Param [r, n]
     * @return void
     **/
    public void write(int r, int n) {
        String[] formula = new String[n];
        String[] answer  = new String[n];
        int index;
        for (int i = 0; i < n; i++) {
            WriteService test = new WriteService();
            System.out.println();
            System.out.printf("第%d道", i + 1);
            test.randomCreate(r);
            if (!test.compute()) {
                i = i - 1;
            } else{
                formula[i] = test.expression;
                answer[i]  = test.answer;
                for(int j=0;j<i;j++){
                    if(answer[j].equals(test.answer)){
                        i = i - 1;
                        System.out.println("答案重复 去除此次生成");
                        break;
                    }
                }
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            index      = i+1;
            formula[i] = new StringBuilder().append(index).append(".").append(formula[i]).toString();
            answer[i]  = new StringBuilder().append(index).append(".").append(answer[i]).toString();
        }
        System.out.println("表达式遍历：");
        for(String temp:formula){
            System.out.println(temp);
        }
        System.out.println("答案：");
        for(String temp:answer){
            System.out.println(temp);
        }
        WriteService test = new WriteService();
        test.fileWire(formula,answer);
    }

}
