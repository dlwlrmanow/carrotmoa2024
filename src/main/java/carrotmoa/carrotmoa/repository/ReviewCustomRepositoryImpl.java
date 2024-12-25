//package carrotmoa.carrotmoa.repository;
//
//import carrotmoa.carrotmoa.entity.QAccommodation;
//import carrotmoa.carrotmoa.entity.QPost;
//import carrotmoa.carrotmoa.entity.QReview;
//import carrotmoa.carrotmoa.entity.QUserProfile;
//import carrotmoa.carrotmoa.model.response.AccommodationReviewResponse;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
//    private final JPAQueryFactory jpaQueryFactory;
//
//    private final QReview review = QReview.review;
//    private final QUserProfile profile = QUserProfile.userProfile;
//    private final QAccommodation accommodation = QAccommodation.accommodation;
//    private final QPost post = QPost.post;
//
//    public ReviewCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
//        this.jpaQueryFactory = jpaQueryFactory;
//    }
//
//    @Override
//    public List<AccommodationReviewResponse> getReviewListById(Long id){
//        List<AccommodationReviewResponse> reviews = jpaQueryFactory
//                .select(Projections.fields(AccommodationReviewResponse.class,
//                        review.comment,
//                        review.createdAt,
//                        profile.nickname
//                ))
//                .from(review)
//                .join(accommodation).on(review.postId.eq(accommodation.postId))
//                .join(profile).on(profile.userId.eq(review.userId))
//                .where(accommodation.id.eq(accommodationId))
//                .fetchOne();
//    }
//}
