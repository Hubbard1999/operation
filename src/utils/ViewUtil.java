package utils;


import controller.CheckController;
import controller.WriteController;
import dao.FileDao;
import dao.impl.FileDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.io.File;


import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 * @author Hubbard
 * @date 2020/4/10 12:44
 */
public class ViewUtil {
    public JFrame jFrame = new JFrame("题目生成");
    public Container c = jFrame.getContentPane();
    private JLabel a1 = new JLabel("范围-r");
    private JTextField username = new JTextField();

    private JLabel a2 = new JLabel("道数-n");
    private JTextField username2 = new JTextField();

    private JButton okbtn = new JButton("确定");
    private JButton cancelbtn = new JButton("重置");
    private JButton table = new JButton("显示列表");
    private JButton eee = new JButton("问题");
    private JButton aaa = new JButton("答案");
    private String queFile;
    private String ansFile;

    public void init() {
        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("四则运算题目生成"));
        c.add(titlePanel, "North");

        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        a1.setBounds(50, 20, 50, 20);
        a2.setBounds(50, 60, 50, 20);
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        username.setBounds(110, 20, 120, 20);
        username2.setBounds(110, 60, 120, 20);
        fieldPanel.add(username);
        fieldPanel.add(username2);
        c.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        buttonPanel.add(cancelbtn);
        buttonPanel.add(table);
        buttonPanel.add(eee);
        buttonPanel.add(aaa);
        c.add(buttonPanel, "South");
    }

    public void listerner() {
        //确认按下去获取
        okbtn.addActionListener(e -> {
            String uname = username.getText();
            String uname2 = username2.getText();
            int r = Integer.parseInt(uname);
            int n = Integer.parseInt(uname2);
            WriteController wc = new WriteController();
            wc.write(r,n);
        });
        //取消按下去清空
        cancelbtn.addActionListener(e -> {
            username.setText("");
            username2.setText("");
            int line = 50;
            for(int i=0;i<line;i++) {
                System.out.println(" ");
            }
        });
       table.addActionListener(e -> {
           FileDaoImpl fileDao = new FileDaoImpl();
            JFrame jf = new JFrame("参考答案");
            jf.setDefaultCloseOperation(HIDE_ON_CLOSE);
            // 表头（列名）
            String[] columnNames = {"序号","题目", "答案"};
            // 表格所有行数据
            String[] question= fileDao.getFile("Exercises.txt","Q");
            String[] answer= fileDao.getFile("Answers.txt","Q");
            int len=fileDao.getLen(question);
            Object[][] rowData = fileDao.getQuenAns(len,question,answer);
            // 创建一个表格，指定 表头 和 所有行数据
            JTable table = tableSet(rowData,columnNames);
            // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
            JScrollPane scrollPane = new JScrollPane(table);
            // 创建内容面板
           JPanel panel = new JPanel();
            // 添加 滚动面板 到 内容面板
            panel.add(scrollPane);
            // 设置 内容面板 到 窗口
            jf.setContentPane(panel);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
        });
        eee.addActionListener(e -> {
            JFileChooser que=new JFileChooser();
            que.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            que.showDialog(new JLabel(), "选择");
            File file=que.getSelectedFile();
            queFile = file.getAbsolutePath();
            System.out.println("已获取题目路径：");
            System.out.println(queFile);
        });
        aaa.addActionListener(e -> {
            FileDaoImpl fileDao = new FileDaoImpl();
            ansFile = fileDao.getFilePath();
            System.out.println("已获取答案路径：");
            System.out.println(ansFile);
            System.out.println("-------------------------------分割线----------------------------------");
            CheckController judge = new CheckController();
            judge.checkqna(queFile, ansFile);
            JFrame jf = new JFrame("判断答案");
            jf.setDefaultCloseOperation(HIDE_ON_CLOSE);
            // 创建内容面板
            JPanel panel = new JPanel();
            // 表头（列名）
            String[] columnNames = {"序号","题目", "答案","参考答案","结果"};
            // 表格所有行数据
            String[] question= fileDao.getFile(queFile,"Q");
            String[] answer= fileDao.getFile(ansFile,"Q");
            String[] correctAnswer= fileDao.getFile("TempAnswers.txt","Q");
            int len=fileDao.getLen(question);
            Object[][] rowData = fileDao.getGrade(len,question,answer,correctAnswer);
            // 创建一个表格，指定 表头 和 所有行数据
            JTable table = tableSet(rowData,columnNames);
            // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
            JScrollPane scrollPane = new JScrollPane(table);
            // 添加 滚动面板 到 内容面板
            panel.add(scrollPane);
            // 设置 内容面板 到 窗口
            jf.setContentPane(panel);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
        });
    }

    private JTable tableSet(Object[][] rowData, String[] columnNames){
        JTable table = new JTable(rowData, columnNames);

        // 设置表格内容颜色
        // 字体颜色
        table.setForeground(Color.BLACK);
        // 字体样式
        table.setFont(new Font(null, Font.PLAIN, 14));
        // 选中后字体颜色
        table.setSelectionForeground(Color.DARK_GRAY);
        // 选中后字体背景
        table.setSelectionBackground(Color.LIGHT_GRAY);
        // 网格颜色
        table.setGridColor(Color.GRAY);

        // 设置表头// 设置表头名称字体样式
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        // 设置表头名称字体颜色
        table.getTableHeader().setForeground(Color.RED);
        // 设置不允许拖动重新排序各列
        table.getTableHeader().setReorderingAllowed(false);

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 600));
        return table;
    }
}
