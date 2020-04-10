package dao;

import javax.swing.*;
import java.io.*;

/**
 * @author Hubbard
 * @date 2020/4/10 20:49
 */
public class FileDao {
    public static String[] getFile(String file, String type){
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

    public  static String getFilePath(){
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        return file.getAbsolutePath();
    }

    public static int getLen(String[] list){
        int len=0;
        for(String a:list){
            if(a==null) {
                break;
            }
            len++;
        }
        return len;
    }

    public static Object[][] getQuenAns(int len, String[] question, String[] answer){
        Object[][] rowData = new Object[len][3];
        for(int i=0;i<len;i++){
            rowData[i][0] = i+1;
            rowData[i][1] = question[i];
            rowData[i][2] = answer[i];
        }
        return rowData;
    }

    public static Object[][] getGrade(int len, String[] question, String[] answer,String[] correctAnswer){
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
