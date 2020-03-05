package com.zjnu.dormitory.dormitory.form;

import lombok.Data;

@Data
public class QueryRoom {
    private String rprice;
    private String rstatus;
    private String rno;
    private String rtype;

    @Override
    public String toString() {
        return "QueryRoom{" +
                "rtype='" + rtype + '\'' +
                ", rprice='" + rprice + '\'' +
                ", rno='" + rno + '\'' +
                ", rstatus='" + rstatus + '\'' +
                '}';
    }
}
