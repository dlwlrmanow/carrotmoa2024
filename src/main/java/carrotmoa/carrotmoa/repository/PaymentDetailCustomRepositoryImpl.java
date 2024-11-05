package carrotmoa.carrotmoa.repository;

import carrotmoa.carrotmoa.entity.*;
import carrotmoa.carrotmoa.model.response.PaymentDetailResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PaymentDetailCustomRepositoryImpl implements PaymentDetailCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public PaymentDetailCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<PaymentDetailResponse> getSettlementList(Long hostId, Long accommodationId, LocalDate startDate, LocalDate endDate) {
        QPayment payment = QPayment.payment;
        QReservation reservation = QReservation.reservation;
        QUser user = QUser.user;
        QAccommodation accommodation = QAccommodation.accommodation;
        QPost post = QPost.post;

        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.and(reservation.checkOutDate.between(startDate, endDate))
                .and(post.isDeleted.eq(false))
                .and(payment.status.eq("paid"))
                .and(post.userId.eq(hostId));

        // accommodationId가 null이 아닐 경우에만 조건 추가
        if (accommodationId != -1) {
            whereClause.and(reservation.accommodationId.eq(accommodationId));
        }

        List<PaymentDetailResponse> results = jpaQueryFactory
                .select(Projections.fields(PaymentDetailResponse.class,
                        reservation.checkOutDate.as("settlementDate"), // 정산 일자
                        post.title,
                        user.name,
                        reservation.checkInDate,
                        payment.paymentAmount
                ))
                .from(payment)
                .join(reservation).on(payment.reservationId.eq(reservation.id))
                .join(user).on(reservation.userId.eq(user.id))
                .join(accommodation).on(reservation.accommodationId.eq(accommodation.id))
                .join(post).on(accommodation.postId.eq(post.id))
                .where(whereClause)
                .fetch();
        // 만료된 계약으로 해주어야 하나...

        // 결과에 1일 추가
//        results.forEach(result -> result.setSettlementDate(result.getSettlementDate().plusDays(1)));

        return results;

    }


}
