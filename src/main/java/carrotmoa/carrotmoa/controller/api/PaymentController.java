package carrotmoa.carrotmoa.controller.api;

import carrotmoa.carrotmoa.entity.Payment;
import carrotmoa.carrotmoa.model.request.PaymentRequest;
import carrotmoa.carrotmoa.model.response.ErrorResponse;
import carrotmoa.carrotmoa.model.response.SuccessResponse;
import carrotmoa.carrotmoa.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/portone")
    public ResponseEntity<?> savePortone(@RequestBody PaymentRequest paymentRequest) {
        try {
            paymentService.savePayment(Payment.of(paymentRequest));
            return ResponseEntity.ok(new SuccessResponse("Payment processed successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to process payment.", HttpStatus.INTERNAL_SERVER_ERROR.value()));        }
    }


    /**
     * 모든 결제 내역 조회
     *
     * @return 모든 Payment 엔티티 리스트
     */
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
}