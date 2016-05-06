package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Description: User
 * Author: silence
 * Update: silence(2016-05-01 09:32)
 */
@Entity
@Table(name = "user")
public class User {
    private String userId;  //用户编号
    private String passWord;  //用户密码
    private String userName;  //用户姓名
    private String position;  //用户职位
    private String tele;  //用户电话
    private String email;  //用户邮箱

    @Id
    @Column(name = "userID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "passWord")
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "tele")
    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(passWord, user.passWord) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(position, user.position) &&
                Objects.equals(tele, user.tele) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, passWord, userName, position, tele, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userName='" + userName + '\'' +
                ", position='" + position + '\'' +
                ", tele='" + tele + '\'' +
                '}';
    }
}
