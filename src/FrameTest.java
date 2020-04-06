import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTest {

    private JFrame jFrame = new JFrame("题目生成");
    private Container c = jFrame.getContentPane();
    private JLabel a1 = new JLabel("范围-r");
    private JTextField username = new JTextField();

    private JLabel a2 = new JLabel("道数-n");
    private JTextField username2 = new JTextField();

    private JButton okbtn = new JButton("确定");
    private JButton cancelbtn = new JButton("重置");

    public FrameTest() {
        //设置窗体的位置及大小
        jFrame.setBounds(600, 200, 300, 220);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);
        listerner();
    }
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
        c.add(buttonPanel, "South");
    }

    public void listerner() {
        //确认按下去获取
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = username.getText();
                String uname2 = username2.getText();
                int r = Integer.parseInt(uname);
                int n = Integer.parseInt(uname2);
                View test = new View();
                test.view(r,n);
            }
        });
        //取消按下去清空
        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                username2.setText("");
                for(int i=0;i<50;i++)
                    System.out.println(" ");
            }
        });
    }

    //测试
    public static void main(String[] args) {
        new FrameTest();
    }
}