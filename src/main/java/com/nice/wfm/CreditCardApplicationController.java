package com.nice.wfm;

import com.nice.wfm.dto.CreditCardApplicationResponse;
import com.nice.wfm.dto.CreditCardApplicationRequest;
import com.nice.wfm.dto.CreditScoreRequest;
import com.nice.wfm.dto.CreditScoreResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Created on 8/15/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

@RestController
public class CreditCardApplicationController {

    private final RestTemplate restTemplate;

    public CreditCardApplicationController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        Objects.requireNonNull(this.restTemplate);
    }

    @PostMapping(value = "/credit-card-applications", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreditCardApplicationResponse applyForCreditCard(final @RequestBody CreditCardApplicationRequest creditCardApplicationRequest){
        String ssn = creditCardApplicationRequest.getSsn();
        CreditScoreResponse creditScoreResponse = restTemplate.postForObject("http://localhost:8080/credit-scores", new CreditScoreRequest(ssn), CreditScoreResponse.class);

        if(creditScoreResponse.isCreditScoreHigh() && CreditCardApplicationRequest.CardType.GOLD == creditCardApplicationRequest.getCardType()){
            return new CreditCardApplicationResponse(CreditCardApplicationResponse.Status.GRANTED);
        }

        throw new RuntimeException("Rest of logic not implemented");
    }
}
