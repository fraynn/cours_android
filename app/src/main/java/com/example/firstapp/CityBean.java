package com.example.firstapp;

class CityBean {
    private String ville;
    private String cp;

    public CityBean(String ville, String cp) {
        this.ville = ville;
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
