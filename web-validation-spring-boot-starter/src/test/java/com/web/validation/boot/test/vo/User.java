package com.web.validation.boot.test.vo;

import com.web.validation.core.annotation.valid.DateStringFormat;
import com.web.validation.core.annotation.valid.IntNumber;
import com.web.validation.core.annotation.valid.NotNull;
import com.web.validation.core.annotation.valid.StringLength;
import lombok.Data;

@Data
public class User {

    @NotNull
    private String userId;

    @IntNumber(min = 6,max = 12)
    private int age;
    @DateStringFormat(format = "yyyy-MM-dd")
    @StringLength(min = 10,max = 10)
    private String timeStemp;
}
