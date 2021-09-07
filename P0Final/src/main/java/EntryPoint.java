import controllers.TestController;
import io.javalin.Javalin;

public class EntryPoint {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7000);
        TestController.init(app);


    }
}
