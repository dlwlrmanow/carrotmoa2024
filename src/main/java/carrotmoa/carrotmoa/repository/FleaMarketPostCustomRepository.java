package carrotmoa.carrotmoa.repository;

import carrotmoa.carrotmoa.model.response.FleaMarketPostResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FleaMarketPostCustomRepository {

    Page<FleaMarketPostResponse> findPostListToPage(Pageable pageable);

    List<FleaMarketPostResponse> findPostList();
}
