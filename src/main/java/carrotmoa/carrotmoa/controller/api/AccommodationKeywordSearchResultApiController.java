package carrotmoa.carrotmoa.controller.api;

import carrotmoa.carrotmoa.model.response.AccommodationResultResponse;
import carrotmoa.carrotmoa.service.AccommodationSearchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/guest")
public class AccommodationKeywordSearchResultApiController {
    private final AccommodationSearchResultService accommodationSearchResultService;

    @Autowired
    public AccommodationKeywordSearchResultApiController(AccommodationSearchResultService accommodationSearchResultService) {
        this.accommodationSearchResultService = accommodationSearchResultService;
    }
    @GetMapping("/search")
    public List<AccommodationResultResponse> searchByKeyword(@RequestParam("keyword") String keyword) {
        List<AccommodationResultResponse> rooms = accommodationSearchResultService.searchAccommodations(keyword);
        return rooms;
    }
}