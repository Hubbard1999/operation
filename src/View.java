import java.io.*;

class View {

    static void view(int r, int n) {
        String[] formula = new String[n];
        String[] answer  = new String[n];
        int index;
        for (int i = 0; i < n; i++) {
            Calculate test = new Calculate();
            System.out.println();
            System.out.printf("第%d道", i + 1);
            test.Random_Create(r);
            if (!test.Compute()) i = i - 1;
            else{
                formula[i] = test.Expression;
                answer[i]  = test.Answer;
                for(int j=0;j<i;j++){
                    if(answer[j].equals(test.Answer)){
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
            formula[i] = index+"."+formula[i];
            answer[i]  = index+"."+answer[i];
        }
        System.out.println("表达式遍历：");
        for(String temp:formula){
            System.out.println(temp);
        }
        System.out.println("答案：");
        for(String temp:answer){
            System.out.println(temp);
        }
        FileWire(formula,answer);

    }

    private static void FileWire(String[] formula, String[] answer){
        OutputStream os = null;
        OutputStream os2 = null;
        try {
            os = new FileOutputStream("Exercises.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            os2 = new FileOutputStream("Answers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw=new PrintWriter(os);
        PrintWriter pw2=new PrintWriter(os2);
        for(String s:formula) {
            pw.println(s);//每输入一个数据，自动换行，便于我们每一行每一行地进行读取
        }
        for(String s:answer) {
            pw2.println(s);//每输入一个数据，自动换行，便于我们每一行每一行地进行读取
        }
        pw.close();
        pw2.close();
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
