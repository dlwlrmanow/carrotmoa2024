package carrotmoa.carrotmoa.controller.api;

import carrotmoa.carrotmoa.model.response.AccommodationDetailResponse;
import carrotmoa.carrotmoa.service.GuestRoomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guest/room/detail")
public class AccommodationDetailApiController {
    private final GuestRoomDetailService guestRoomDetailService;

    @Autowired
    public AccommodationDetailApiController(GuestRoomDetailService guestRoomDetailService) {
        this.guestRoomDetailService = guestRoomDetailService;
    }

    @GetMapping
    public ResponseEntity<AccommodationDetailResponse> showDetailById(@RequestParam("id") Long id) {
        AccommodationDetailResponse roomInfo = guestRoomDetailService.getAccommodationDetail(id);

        if(roomInfo == null){
            return ResponseEntity.ok(new AccommodationDetailResponse());
        }

        return ResponseEntity.ok(roomInfo);
    }
}
