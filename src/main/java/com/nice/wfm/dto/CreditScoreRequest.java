package com.nice.wfm.dto;

import java.util.Objects;

/**
 * Created on 8/15/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

public class CreditScoreRequest {
    private final String ssn;

    public CreditScoreRequest(String ssn) {
        this.ssn = ssn;
        Objects.requireNonNull(this.ssn);
    }

    public String getSsn() {
        return ssn;
    }
}
