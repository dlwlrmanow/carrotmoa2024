package carrotmoa.carrotmoa.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AmenityImageResponse {
    // amenity table에서 필요한 정보
    private long amenityId;
    private String name;
    private String iconUrl;

    public static AmenityImageResponse fromData(Object[] data) {
        return AmenityImageResponse.builder()
                .amenityId((Long) data[0])
                .name((String) data[1])
                .iconUrl((String) data[2])
                .build();
    }
}