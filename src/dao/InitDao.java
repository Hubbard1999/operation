package dao;

import entity.OperNum;


/**
 * @author Hubbard
 * @date 2020/4/10 13:30
 */
public interface InitDao {

    String[]    str       =   { "+", "-", "×", "÷", "(" ,")"," "};

    OperNum[] mallocSpace(OperNum[] op,int space);

    OperNum[] mallocOper(OperNum[] op, int count);

    OperNum[] mallocNum(OperNum[] op,int count,int r);

    OperNum[] mallocBlank(OperNum[] op,int space);

    OperNum[] mallocBracket(OperNum[] op,int count);

    String getExpress(OperNum[] op,int space);
}
