package com.example.ceertifications.controller;

import com.example.ceertifications.dto.CertificationDto;
import com.example.ceertifications.dto.CheckoutPayment;
import com.example.ceertifications.dto.ExamenDto;
import com.example.ceertifications.dto.ExamenGroupDto;
import com.example.ceertifications.dto.UserExamenDto;
import com.example.ceertifications.entities.Certification;
import com.example.ceertifications.entities.Examen;
import com.example.ceertifications.entities.ReponseQuestionsEntity;
import com.example.ceertifications.entities.UserExamen;
import com.example.ceertifications.exception.GlobalException;
import com.example.ceertifications.service.CertificationService;
import com.example.ceertifications.service.ExamenService;

import com.example.ceertifications.service.PayementService;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ExamenController {

    CertificationService certificationService;
    ExamenService examenService;
    PayementService payementService;
    private static Gson gson = new Gson();

    @GetMapping("/certifications/{id}")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public CertificationDto getCertifications(@PathVariable Long id) {
        return certificationService.getCertificationById(id);
    }

    @GetMapping("/certification/{libelle}")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public CertificationDto getCertificationByLibelle(@PathVariable String libelle) {
        return certificationService.getCertificationByLibelle(libelle);
    }

    @GetMapping("/certifications")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<CertificationDto> getCertification() {
        return certificationService.getCertificationByUser();
    }

    @GetMapping("/examens/{id}")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<ExamenDto> getExamenByCertificationId(@PathVariable Long id) {
        return examenService.getExamenByCertificationId(id);
    }

    @GetMapping("/userExamen/{id}")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public UserExamenDto getUserExamenById(@PathVariable Long id) {
        return examenService.getUserExamen(id);
    }

    @GetMapping("/examen/{id}")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ExamenDto getExamenById(@PathVariable Long id) {
        return examenService.getExamenById(id);
    }

    @GetMapping("/examengroups/{id}")
    public List<ExamenGroupDto> getExamenGroupByCertificationId(@PathVariable Long id) {
        return examenService.getExamenGroupByCertificationId(id);
    }

    @GetMapping("/examens/create-user-examen/{id}")
    public UserExamenDto createUserExamen(@PathVariable Long id) throws GlobalException {
        return examenService.createUserExamen(id);
    }

    @PostMapping("/examens/saveExamen")
    public UserExamenDto saveUserExamen(@RequestBody UserExamenDto userExamenDto) {
        return examenService.saveUserExamen(userExamenDto);
    }

    @GetMapping("/examens/get-user-examen/{id}")
    public UserExamenDto getUserExamen(@PathVariable Long id) throws GlobalException {
        return examenService.getUserExamen(id);
    }

    @PostMapping("/payement/register")
    public CertificationDto registerPayement(@RequestBody CheckoutPayment payment) {
        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", payment.getCertificationId().toString());
        payementService.savePayement(payment);
        return certificationService.getCertificationById(payment.getCertificationId());
    }

    @PostMapping("/payement")
    public String pay(@RequestBody CheckoutPayment payment) throws StripeException {
        init();
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
        Gson gson = new Gson();
        return gson.toJson(responseData);
    }

    private static void init() {
        Stripe.apiKey = "sk_test_51OW90nCxzoszcrHkUWaiq2zIYB7qISvNMESSNLNzDu9UoUK1HUvboT0ZUQgEeRvizRw1JEpiOMdO7kbDmfHoFzB6003XSL8okb";
    }
}
