package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {
    private int id;
    private String code;
    private String fullName;
    private String sign;
    public Currency(String code, String fullName, String sign) {
        setCode(code);
        setFullName(fullName);
        setSign(sign);
    }
}
