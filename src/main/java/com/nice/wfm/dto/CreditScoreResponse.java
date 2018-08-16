package com.nice.wfm.dto;

/**
 * Created on 8/15/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

public class CreditScoreResponse {

    private CreditScore creditScore;

    public CreditScore getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(CreditScore creditScore) {
        this.creditScore = creditScore;
    }

    private enum CreditScore {
        HIGH, LOW
    }

    public boolean isCreditScoreHigh(){
        return CreditScore.HIGH.equals(this.creditScore);
    }

    public boolean isCreditScoreLow(){
        return CreditScore.LOW.equals(this.creditScore);
    }
}
