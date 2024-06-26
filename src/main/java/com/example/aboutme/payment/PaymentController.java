package com.example.aboutme.payment;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.payment.PaymentRequestRecord.PaymentPortOneReqDTO;
import com.example.aboutme.payment.PaymentResponseRecord.PaymentDetailsDTO;
import com.example.aboutme.payment.PaymentResponseRecord.PaymentPortOneRespDTO;
import com.example.aboutme.reservation.ReservationService;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final ReservationService reservationService;
    private final RedisTemplate<String, Object> redisTemp;
    private final RedisUtil redisUtil;

    //전문가 칮기 - 결제하기
    @GetMapping("/client/findExpert/payment/{reservationId}")
    public String findExpertPayment(@PathVariable("reservationId") Integer reservationId, Model model) {
        PaymentDetailsDTO paymentDetailsDTO = reservationService.getTempReservation(reservationId);
        model.addAttribute("model", paymentDetailsDTO);
        return "client/findExpert/payment";
    }


    @PostMapping("/payments/request")
    @ResponseBody
    public PaymentPortOneRespDTO requestPayment(@RequestBody PaymentPortOneReqDTO paymentRequestDTO) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        log.info("Requesting payment" + paymentRequestDTO);
        return paymentService.requestPayment(paymentRequestDTO, sessionUser);
    }


    @PostMapping("/payments/complete")
    public ResponseEntity<String> completePayment(@RequestParam String impUid, @RequestParam String merchantUid, @RequestParam Integer reservationId) {
        paymentService.completePayment(impUid, merchantUid, reservationId);
        return ResponseEntity.ok("/");
    }


    @GetMapping("/list")
    public String getAllPayments(Model model) {
        log.info("Getting all payments", model);
        List<PaymentPortOneRespDTO> respDTOS = paymentService.getAllPayments();
        model.addAttribute("model", respDTOS);
        return null;
    }

}
