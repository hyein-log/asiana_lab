package com.example.Asiana_lab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //user 객체를 생성할 때 가독성이 좋게 코드를 짤 수 있다.
public class User {
    private int user_no; //회원번호
    private String userid; //아이디
    private String password; //비밀번호
    private String email; //이메일
    private String passport; //여권번호
    private String birthday; //생일
    private int mileage; //마일리지
    private boolean isAdmin; //관리자 여부 (true: admin, false: 일반사용자)
}
