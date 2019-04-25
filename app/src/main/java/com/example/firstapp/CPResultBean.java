package com.example.firstapp;

import java.util.List;

public class CPResultBean {
    private List<CityBean> results;
    private int nbr;
    private ErrorBean errors;

    public List<CityBean> getResults() {
        return results;
    }

    public void setResults(List<CityBean> results) {
        this.results = results;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public ErrorBean getError() {
        return errors;
    }

    public void setError(ErrorBean error) {
        errors = error;
    }

    @Override
    public String toString() {
        return "CPResultBean{" +
                "results=" + results +
                ", nbr=" + nbr +
                ", errors=" + errors +
                '}';
    }
}
