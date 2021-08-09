package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //롬복사용을위해 추가
public class HelloData {
    private String username;
    private int age;

    //롬복사용을 위해 주석
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
}
