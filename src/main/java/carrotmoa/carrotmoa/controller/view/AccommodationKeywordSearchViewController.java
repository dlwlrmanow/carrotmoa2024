package carrotmoa.carrotmoa.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guest/search")
@Slf4j
public class AccommodationKeywordSearchViewController {
    @GetMapping
    public String search(@RequestParam("keyword") String keyword) {
        return "guest/roomResult";
    }
}
