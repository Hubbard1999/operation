package dao.impl;

import dao.FileDao;

import javax.swing.*;
import java.io.*;

/**
 * @author Hubbard
 * @date 2020/4/11 12:42
 */
public class FileDaoImpl implements FileDao {
    /**
     * @Author Hubbard
     * @Description //TODO  读取给定文件路径 若传入参数Q，则去除序号
     * @Date 2020/4/11
     * @Param [file, type]
     * @return java.lang.String[]
     **/
    @Override
    public String[] getFile(String file, String type){
        String model = "Q";
        int position=0;
        String[] bufstring=new String[10000];
        //打开带读取的文件
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line=null;
        while(true) {
            try {
                assert br != null;
                if ((line=br.readLine())==null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            bufstring[position]=line;
            position++;
        }
        try {
            br.close();//关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(model.equals(type)){
            for(int i=0;i<position;i++) {
                String temp = String.valueOf(i+1);
                assert bufstring[i] != null;
                bufstring[i]=bufstring[i].substring(temp.length()+1);
                assert bufstring[i] != null;
            }
        }

        return bufstring;
    }

    /**
     * @Author Hubbard
     * @Description //TODO  将题目和答案写入文件
     * @Date 2020/4/11
     * @Param [formula, answer]
     * @return void
     **/
    @Override
    public void fileWire(String[] formula, String[] answer){
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
        assert os != null;
        PrintWriter pw=new PrintWriter(os);
        assert os2 != null;
        PrintWriter pw2=new PrintWriter(os2);
        for(String s:formula) {
            //每输入一个数据，自动换行，便于我们每一行每一行地进行读取
            pw.println(s);
        }
        for(String s:answer) {
            pw2.println(s);
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

    /**
     * @Author Hubbard
     * @Description //TODO  将得到的正确答案写入文件"TempAnswers.txt"
     * @Date 2020/4/11
     * @Param [answer]
     * @return void
     **/
    @Override
    public void writeAnsFile(String[] answer){
        OutputStream os2 = null;
        try {
            os2 = new FileOutputStream("TempAnswers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert os2 != null;
        PrintWriter pw2=new PrintWriter(os2);
        for(String s:answer) {
            if(s==null) {
                break;
            }
            //每输入一个数据，自动换行，便于我们每一行每一行地进行读取
            pw2.println(s);
        }
        pw2.close();
        try {
            os2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author Hubbard
     * @Description //TODO  GUI得到文件路径
     * @Date 2020/4/11
     * @Param []
     * @return java.lang.String
     **/
    @Override
    public String getFilePath(){
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        return file.getAbsolutePath();
    }

    /**
     * @Author Hubbard
     * @Description //TODO  得到数组长度
     * @Date 2020/4/11
     * @Param [list]
     * @return int
     **/
    @Override
    public int getLen(String[] list){
        int len=0;
        for(String a:list){
            if(a==null) {
                break;
            }
            len++;
        }
        return len;
    }

    /**
     * @Author Hubbard
     * @Description //TODO  将定长len的序号、问题和答案赋值到object数组
     * @Date 2020/4/11
     * @Param [len, question, answer]
     * @return java.lang.Object[][]
     **/
    @Override
    public Object[][] getQuenAns(int len, String[] question, String[] answer){
        Object[][] rowData = new Object[len][3];
        for(int i=0;i<len;i++){
            rowData[i][0] = i+1;
            rowData[i][1] = question[i];
            rowData[i][2] = answer[i];
        }
        return rowData;
    }

    /**
     * @Author Hubbard
     * @Description //TODO  将定长len的序号、问题和测试答案、正确答案赋值到object数组
     * @Date 2020/4/11
     * @Param [len, question, answer, correctAnswer]
     * @return java.lang.Object[][]
     **/
    @Override
    public Object[][] getGrade(int len, String[] question, String[] answer, String[] correctAnswer){
        Object[][] rowData = new Object[len][5];
        for(int i=0;i<len;i++){
            rowData[i][0] = i+1;
            rowData[i][1] = question[i];
            rowData[i][2] = answer[i];
            rowData[i][3] = correctAnswer[i];
            if(answer[i].equals(correctAnswer[i])) {
                rowData[i][4] = "正确";
            } else {
                rowData[i][4] = "错误";
            }
        }
        return rowData;
    }

}
