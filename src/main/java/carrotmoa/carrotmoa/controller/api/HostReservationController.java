package carrotmoa.carrotmoa.controller.api;

import carrotmoa.carrotmoa.config.security.CustomUserDetails;
import carrotmoa.carrotmoa.model.response.HostReservationResponse;
import carrotmoa.carrotmoa.service.HostReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/host/reservation")
public class HostReservationController {
    private final HostReservationService hostReservationService;

    public HostReservationController(HostReservationService hostReservationService) {
        this.hostReservationService = hostReservationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<HostReservationResponse>> getHostReservations(@PathVariable("userId")Long userId) {
        List<HostReservationResponse> hostReservations = hostReservationService.getHostReservations(userId);

        if (hostReservations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(hostReservations);
    }

}