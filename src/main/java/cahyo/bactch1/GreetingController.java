package cahyo.bactch1;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
    * The @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the greeting() method.
    * request param untuk memetakan param name yg dikirim dari user agar di petakan di kelas greeting
    * greeting object disini harus di konversikan ke json dengan menggunakan spring http message converter support
    * */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "word") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
