import com.github.xiaozhong.component.MyComponent;
import com.github.xiaozhong.service.MyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhougaolei
 * @date At 2019/3/6
 */
public class Entrance {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyComponent.class);
        context.register(MyService.class);
        context.refresh();

        MyService myService = context.getBean(MyService.class);
        myService.sayHello();
    }
}
