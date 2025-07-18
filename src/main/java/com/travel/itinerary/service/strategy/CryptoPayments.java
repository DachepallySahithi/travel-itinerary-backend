package com.travel.itinerary.service.strategy;

import com.travel.itinerary.dto.PaymentRequest;
import com.travel.itinerary.dto.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CryptoPayments implements IPaymentStrategy{

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        log.info("Crypto payment processing not yet implemented for amount: {}", paymentRequest.getAmount());

        return PaymentResponse.failed("Cryptocurrency payments are not yet supported");
    }
}
