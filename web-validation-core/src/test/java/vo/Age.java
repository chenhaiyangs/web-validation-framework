package vo;

import com.web.validation.core.annotation.process.ToLower;
import com.web.validation.core.annotation.valid.NotNull;
import lombok.Data;

@Data
public class Age {

    @ToLower
    @NotNull
    private String classA="ddddfdfdfdfdfdfdfdfdfAAA";
}
