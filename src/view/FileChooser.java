package view;

import utils.ViewUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author Hubbard
 * @date 2020/4/10 9:37
 */
public class FileChooser {
    /**
     * 图形化入口
     * @author Hubbard
     * @date 2020/4/10 9:37
     */
    public static void main(String[] args) {
        ViewUtil inter = new ViewUtil();
        chooser(inter);
    }

    private static void chooser(ViewUtil inter) {
        //设置窗体的位置及大小
        inter.jFrame.setBounds(600, 200, 400, 250);
        //设置一层相当于桌布的东西  布局管理器
        inter.c.setLayout(new BorderLayout());
        //设置按下右上角X号后关闭
        inter.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化--往窗体里放其他控件
        inter.init();
        //设置窗体可见
        inter.jFrame.setVisible(true);
        inter.listerner();
    }
}