package com.travel.itinerary.service.strategy;

import com.travel.itinerary.dto.PaymentRequest;
import com.travel.itinerary.dto.PaymentResponse;

public interface IPaymentStrategy {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
}