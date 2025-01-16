package com.shermatov.project_management_system.controller;

import com.shermatov.project_management_system.model.PlanType;
import com.shermatov.project_management_system.model.User;
import com.shermatov.project_management_system.response.PaymentLinkResponse;
import com.shermatov.project_management_system.service.UserService;
import com.stripe.StripeClient;
import com.stripe.model.PaymentLink;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("$stripe.api.secret-key")
    private String apiKey;

    @Value("$stripe.api.publishable-key")
    private String publishableKey;

    @Autowired
    private UserService userService;


    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @PathVariable PlanType planType,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        int amount = 799 * 100;
        if(planType.equals(PlanType.ANNUALLY)) {
            amount = amount * 12;
            amount = (int) (amount * 0.7);

        }


        StripeClient stripeClient = new StripeClient(apiKey);

        JSONObject paymentLinkRequest = new JSONObject();

        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "USD");

        JSONObject customer = new JSONObject();
        customer.put("user", user.getFullName());
        customer.put("email", user.getEmail());
        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("callback_url", "http://localhost:5173/upgrade_plan/success?planType"+planType);

        PaymentLink payment = PaymentLink.create(paymentLinkRequest .toMap());

        String paymentLinkId = payment.getId();
        String paymentLinkUrl = payment.getUrl();

        PaymentLinkResponse res = new PaymentLinkResponse(paymentLinkId, paymentLinkUrl);

        res.setPayment_link_id(paymentLinkId);
        res.setPayment_link_url(paymentLinkUrl);
        return new ResponseEntity<>(res, HttpStatus.CREATED);


    }

}
