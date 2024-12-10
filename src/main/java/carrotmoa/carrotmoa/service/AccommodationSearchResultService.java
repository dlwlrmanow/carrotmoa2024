package carrotmoa.carrotmoa.service;

import carrotmoa.carrotmoa.model.response.AccommodationResultResponse;
import carrotmoa.carrotmoa.repository.AccommodationRepository;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AccommodationSearchResultService {
    // 생성자 주입방식
    private final AccommodationRepository accommodationRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public AccommodationSearchResultService(AccommodationRepository accommodationRepository, RedisTemplate<String, Object> redisTemplate) {
        this.accommodationRepository = accommodationRepository;
        this.redisTemplate = redisTemplate;
    }

    @Transactional(readOnly = true)
    public List<AccommodationResultResponse> searchAccommodations(String keyword, Long lastId, int limit) {
        // redis에 데이터가 있나 확인
        String redisKey = "accommodations::" + keyword + "::" + lastId;
        List<AccommodationResultResponse> cachedRedistData = (List<AccommodationResultResponse>) redisTemplate.opsForValue().get(redisKey);

        if(cachedRedistData != null) {
            // redis에 데이터 있으면 있는 거 주기
            return cachedRedistData;
        }

        List<Object[]> results = accommodationRepository.searchAccommodationByKeyword(keyword, lastId, limit);
        List<AccommodationResultResponse> accommodations = results.stream()
                        .map(AccommodationResultResponse::fromData)
                        .collect(Collectors.toList());

        redisTemplate.opsForValue().set(redisKey, accommodations, Duration.ofMinutes(30));
//        return results.stream()
//            .map(AccommodationResultResponse::fromData)
//            .collect(Collectors.toList());
        return accommodations;
    }


}