package com.ilham.covid19.model;


import com.google.gson.annotations.SerializedName;

import java.util.jar.Attributes;

public class MainModel {
    private Attributes attributes;
    public Attributes getAttributes(){
        return attributes;
    }
    public static class Attributes{
        @SerializedName("Provinsi")
        private String provinsi;
        @SerializedName("Kasus_Posi")
        private String positif;
        @SerializedName("Kasus_Semb")
        private String sembuh;
        @SerializedName("Kasus_Meni")
        private String meninggal;
        public String getProvinsi() {
            return provinsi;
        }
        public void setProvinsi(String provinsi) {
            this.provinsi = provinsi;
        }
        public String getPositif() {
            return positif;
        }
        public void setPositif(String positif) {
            this.positif = positif;
        }
        public String getSembuh() {
            return sembuh;
        }
        public void setSembuh(String sembuh) {
            this.sembuh = sembuh;
        }
        public String getMeninggal() {
            return meninggal;
        }
        public void setMeninggal(String meninggal) {
            this.meninggal = meninggal;
        }
    }
    private String name;
    private String positif;
    private String sembuh;
    private String meninggal;
    private String dirawat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositif() {
        return positif;
    }

    public void setPositif(String positif) {
        this.positif = positif;
    }

    public String getSembuh() {
        return sembuh;
    }

    public void setSembuh(String sembuh) {
        this.sembuh = sembuh;
    }

    public String getMeninggal() {
        return meninggal;
    }

    public void setMeninggal(String meninggal) {
        this.meninggal = meninggal;
    }

    public String getDirawat() {
        return dirawat;
    }

    public void setDirawat(String dirawat) {
        this.dirawat = dirawat;
    }
}
