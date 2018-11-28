package test.validate;

import com.web.validation.core.Validation;
import com.web.validation.core.annotation.valid.ValidChild;
import com.web.validation.core.exception.ValidationFailException;
import com.web.validation.core.valid.ValidChildValidation;
import vo.User;

import java.util.ArrayList;
import java.util.List;

public class TestValidate {

    public static void main(String[] args) throws ValidationFailException {

        Validation.addComponment(ValidChild.class,new ValidChildValidation());
        User user = new User();
        user.setUser(null);
        user.setDdd("1111");
        user.setBool("true");
        user.setDate("2018-12-1 12:12:12");
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        user.setArr(list);
        user.setIne(21);
        Validation.validate(user);
    }
}
