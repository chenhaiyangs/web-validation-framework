package test.process;

import com.web.validation.core.Procession;
import com.web.validation.core.process.ToLowerProcess;
import vo.User;

public class TestProcess {

    public static void main(String[] args) {

        Procession.addComponment(ToLowerProcess.class,new ToLowerProcess());

        User user = new User();
        user.setUser("  sdsd\r\nxd\rdd\nss\\rdd\\r\\n最后的");


        Procession.process(user);

        System.out.println(user.getUser());
        System.out.println(user.getAge().getClassA());
    }
}
