package site.dolshei.jeonju.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDTO {
    private String memberEmail;
    private String memberPassword;
}
