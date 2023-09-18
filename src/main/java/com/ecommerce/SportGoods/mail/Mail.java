package com.ecommerce.SportGoods.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    private String email;
    private String subject;
    private String body;
    private String fName;

}
