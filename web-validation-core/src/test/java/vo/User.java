package vo;

import com.web.validation.core.annotation.process.*;
import com.web.validation.core.annotation.valid.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    @TrimTableChar
    @NotEmpty(code = "1001",message = "字段不%s能为空")
    private String user;

    @StringLength(max = 4)
    private String ddd;

    @DateStringFormat(format = "yyyy-MM-dd")
    private String date;

    @AssertTrue
    private String bool;

    @ValidChild
    private List pss =new ArrayList();

    @NotNull
    @ValidChild
    @ProcessChild
    private Age age =new Age();
    @CollectionSize(min = 4,max = 5)
    private List arr;

    @IntNumber(max = 3022,min = 20)
    private int ine;

}
