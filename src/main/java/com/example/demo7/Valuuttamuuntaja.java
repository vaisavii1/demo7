package com.example.demo7;

import java.io.*;
import java.text.DecimalFormat;

/**
 * Valuuttamuuntaja- luokka asettaa valuutta-olioille
 * valuutan nimen, valuuttakoodin sekä myynti- ja ostokurssin.
 * Luokassa asetetaan myos muunnettava rahamaara seka lasketaan muunnos
 * @author vaisavii
 * @version 1.0
 * @since 2023-03-24
 */


public class Valuuttamuuntaja implements Serializable {

    /**
     * Muunnettava rahamaara desimaalilukuna
     */
    public double maara;
    /**
     * myyntikurssi desimaalilukuna
     */
    double myyntiKurssi;
    /**
     * ostokurssi desimaalilukuna
     */
    double ostoKurssi;
    /**
     * Valuutan nimi merkkijonona
     */
    public String maan_valuutta;
    /**
     * Valuutan koodi merkkijonona
     */
    public String valuuttakoodi;


    /**
     * Valuutta-olio perustiedoilla
     * @param valuutta String valuutan nimi
     * @param koodi String valuutan koodi
     * @param myyntikurssi double valuutan myyntikurssi
     * @param ostokurssi double valuutan ostokurssi
     */
    public Valuuttamuuntaja(String valuutta, String koodi, double myyntikurssi, double ostokurssi) {
        myyntiKurssi = myyntikurssi;
        ostoKurssi = ostokurssi;
        maan_valuutta = valuutta;
        valuuttakoodi = koodi;
    }

    /**
     * Parametriton alustaja valuutalle
     */
    public Valuuttamuuntaja() {
    }


    /**
     * Palauttaa olion tiedot merkkijonona
     * @return String olion tiedot
     */
    @Override
    public String toString() {
        return "MyyntiKkurssi: " + myyntiKurssi +
                ", ostokurssi: " + ostoKurssi +
                ", valuutta: " + maan_valuutta +
                ", valuuttakoodi: " + valuuttakoodi ;
    }

    /**
     * Palauttaa muunnettavan rahamaaran
     * @return double määrä
     */
    public double getMaara() {
        return maara;
    }

    /**
     * Asettaa muunnettavan rahamaaran
     * @param maara double
     */
    public void setMaara(double maara) {
        this.maara = maara;
    }

    /**
     * Palauttaa myyntikurssin
     * @return double myyntikurssi
     */
    public double getMyyntiKurssi() {
        return myyntiKurssi;
    }

    /**
     * Asettaa myyntikurssin
     * @param myynti double
     */
    public void setMyyntiKurssi(double myynti) {
        this.myyntiKurssi = myynti;
    }

    /**
     * Palauttaa ostokurssin
     * @return double ostokurssi
     */
    public double getOstoKurssi() {
        return ostoKurssi;
    }

    /**
     * Asettaa ostokurssin
     * @param osto double
     */
    public void setOstoKurssi(double osto) {
        this.ostoKurssi = osto;
    }

    /**
     * Palauttaa valuutan nimen
     * @return String valuutan nimi
     */
    public String getMaan_valuutta() {
        return maan_valuutta;
    }

    /**
     * Asettaa valuutan nimen
     * @param maan_valuutta String
     */
    public void setMaan_valuutta(String maan_valuutta) {
        this.maan_valuutta = maan_valuutta;
    }

    /**
     * Asettaa valuuttakoodin
     * @param valuuttakoodi String
     */
    public void setValuuttakoodi(String valuuttakoodi) {
        this.valuuttakoodi = valuuttakoodi;
    }

    /**
     * Palauttaa valuuttakoodin
     * @return String valuuttakoodi
     */
    public String getValuuttakoodi() {
        return valuuttakoodi;
    }

    /**
     * Metodi laskee muunnoksen euroista ulkomaan valuuttaan
     * Valuuttamuuntaja-olion seka asetetun maaran avulla.
     * Lasku pyoristetaan DecimalFormat-olion avulla
     * @param olio Valuuttamuuntaja
     * @return double laskun tulos
     */
    public String LaskeMuunnosEuro(Valuuttamuuntaja olio) {
        DecimalFormat df = new DecimalFormat("#.##");
        double lasku2 = olio.myyntiKurssi * maara;
        return df.format(lasku2);
    }

    /**
     * Metodi laskee muunnoksen ulkomaan valuutasta euroiksi
     * Valuuttamuuntaja-olion seka asetetun maaran avulla.
     * Lasku pyoristetaan DecimalFormat-olion avulla
     * @param olio Valuuttamuuntaja
     * @return double laskun tulos
     */
    public String LaskeMuunnosUlkomaa(Valuuttamuuntaja olio) {
        DecimalFormat d = new DecimalFormat("#.##");
        double lasku1 = maara / olio.ostoKurssi;
        return d.format(lasku1);
    }


}