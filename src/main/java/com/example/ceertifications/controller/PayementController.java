package com.example.ceertifications.controller;

import com.example.ceertifications.dto.CheckoutPayment;
import com.example.ceertifications.service.PayementService;
import com.google.gson.Gson;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PayementController {
    private static Gson gson = new Gson();
    PayementService payementService;



    @PostMapping("/payement/paye")
    public String paymentWithChechoutPage(@RequestBody CheckoutPayment payment) throws StripeException {

        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();
        Session session = Session.create(params);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());
        payementService.savePayement(payment);
        return gson.toJson(responseData);
    }

}
