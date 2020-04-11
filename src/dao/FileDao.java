package dao;

/**
 * @author Hubbard
 * @date 2020/4/11 11:36
 */
public interface FileDao {
    /**
     * @Author Hubbard
     * @Description //TODO  读取给定文件路径 若传入参数Q，则去除序号
     * @Date 2020/4/11
     * @Param [file, type]
     * @return java.lang.String[]
     **/
    String[] getFile(String file, String type);

    /**
     * @Author Hubbard
     * @Description //TODO  将题目和答案写入文件
     * @Date 2020/4/11
     * @Param [formula, answer]
     * @return void
     **/
    void fileWire(String[] formula, String[] answer);

    /**
     * @Author Hubbard
     * @Description //TODO  将得到的正确答案写入文件"TempAnswers.txt"
     * @Date 2020/4/11
     * @Param [answer]
     * @return void
     **/
    void writeAnsFile(String[] answer);

    /**
     * @Author Hubbard
     * @Description //TODO  GUI得到文件路径
     * @Date 2020/4/11
     * @Param []
     * @return java.lang.String
     **/
    String getFilePath();

    /**
     * @Author Hubbard
     * @Description //TODO  得到数组长度
     * @Date 2020/4/11
     * @Param [list]
     * @return int
     **/
    int getLen(String[] list);

    /**
     * @Author Hubbard
     * @Description //TODO  将定长len的序号、问题和答案赋值到object数组
     * @Date 2020/4/11
     * @Param [len, question, answer]
     * @return java.lang.Object[][]
     **/
    Object[][] getQuenAns(int len, String[] question, String[] answer);

    /**
     * @Author Hubbard
     * @Description //TODO  将定长len的序号、问题和测试答案、正确答案赋值到object数组
     * @Date 2020/4/11
     * @Param [len, question, answer, correctAnswer]
     * @return java.lang.Object[][]
     **/
    Object[][] getGrade(int len, String[] question, String[] answer, String[] correctAnswer);
}
