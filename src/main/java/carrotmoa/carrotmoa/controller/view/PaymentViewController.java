package carrotmoa.carrotmoa.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentViewController {

    @GetMapping
    public String Payment() {
        return "/guest/payment";
    }
}
