package com.nice.wfm;

import com.nice.wfm.dto.CreditCardApplicationResponse;
import com.nice.wfm.dto.CreditCardApplicationRequest;
import com.nice.wfm.dto.CreditScoreRequest;
import com.nice.wfm.dto.CreditScoreResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

import static com.nice.wfm.dto.CreditCardApplicationRequest.CardType.*;
import static com.nice.wfm.dto.CreditCardApplicationResponse.Status.*;

/**
 * Created on 8/15/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

@RestController
public class CreditCardApplicationController {

    private final RestTemplate restTemplate;
    private final String providerServiceBaseUrl;

    public CreditCardApplicationController(RestTemplate restTemplate, @Value("${provider-service.baseurl}") String providerServiceBaseUrl){
        this.restTemplate = restTemplate;
        this.providerServiceBaseUrl = providerServiceBaseUrl;
        Objects.requireNonNull(this.restTemplate);
        Objects.requireNonNull(this.providerServiceBaseUrl);
    }

    @PostMapping(value = "/credit-card-applications", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreditCardApplicationResponse applyForCreditCard(final @RequestBody CreditCardApplicationRequest creditCardApplicationRequest){
        String ssn = creditCardApplicationRequest.getSsn();

        String url = UriComponentsBuilder.fromHttpUrl(providerServiceBaseUrl)
                .path("credit-scores").toUriString();
        CreditScoreResponse creditScoreResponse = restTemplate.postForObject(url, new CreditScoreRequest(ssn), CreditScoreResponse.class);

        if(creditCardApplicationRequest.getCardType() == GOLD){
            if(creditScoreResponse.isCreditScoreHigh()){
                return new CreditCardApplicationResponse(GRANTED);
            }

            if(creditScoreResponse.isCreditScoreLow()){
                return new CreditCardApplicationResponse(DENIED);
            }
        }



        throw new RuntimeException("Rest of logic not implemented");
    }
}
