package carrotmoa.carrotmoa.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class AccommodationReviewResponse {
    private String nickname;
    private String comment;
    private LocalDateTime createdAt;
}
