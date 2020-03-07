package com.zjnu.dormitory.dormitory.form;

import com.zjnu.dormitory.dormitory.entity.User;
import lombok.Data;
import org.apache.shiro.SecurityUtils;

@Data
public class QueryRoom {
    private String rprice;
    private String rstatus;
    private String rno;
    private String rtype;
    User user=(User) SecurityUtils.getSubject().getPrincipal();
    private String roleName=user.getRoleName();

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
