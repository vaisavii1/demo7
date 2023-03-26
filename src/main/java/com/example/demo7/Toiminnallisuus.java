package com.example.demo7;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;


/**
 * Valuuttamuuntajaa kasittelevan luokan kayttoliittyma, jolla voidaan asettaa
 * muunnettava rahamaara, ja muuntaa rahamaara euroista ulkomaan valuutaksi tai
 * ulkomaan valuutasta euroiksi
 * @author vaisavii
 * @version 1.0
 */
public class Toiminnallisuus extends Application {

    /**
     * ComboBox valuutan valitsemista varten
     */
    public ComboBox<String> valintaBox = new ComboBox<>();
    /**
     * Lista valuuttakoodeista ComboBoxia varten
     */
    public String[] valintaOtsikot = {"USD", "GBP", "SEK", "NOK", "DKK", "CHF", "ISK", "AUD", "HKD", "KRW", "JPY",
                                         "CNY", "CAD", "PLN"};

    /**
     * Nappi jolla saa etusivun nakyville
     */
    private Button buttonEtusivu = new Button("Etusivu");
    /**
     * Nappi, jolla saa sivun nakyville, jossa muunnetaan euroista ulkomaan valuutaksi
     */
    private Button buttonEuro = new Button("Euroista ulkomaan valuutaksi");
    /**
     * Nappi, jolla saa sivun nakyville, jossa muunnetaan ulkomaan valuutoista euroiksi
     */
    private Button buttonUlkomaa = new Button("Ulkomaan valuutasta euroiksi");
    /**
     * Nappi, jolla saa sivun nakyville, jossa on tietoa valuutoista
     */
    private Button buttonTietoa = new Button("Tietoa valuutoista");

    /**
     * Etusivulle tekstit
     */
    private Label otsikko = new Label("Tervetuloa valuuttamuuntajaan!");
    private Label otsikko1 = new Label("Täällä voi muuntaa eurot ulkomaan valuutaksi,");
    private Label otsikko2 = new Label("ja ulkomaan valuutat euroiksi.");


    /**
     * Euroista ulkomaan valuutaksi-sivun otsikko
     */
    private Label esittely = new Label("Muunna eurot ulkomaan valuutaksi");
    /**
     * Euroista ulkomaan valuutaksi-sivun teksti, joka pyytaa syottamaan rahamaaran
     */
    private Label label1 = new Label("Anna rahasumma euroina: ");
    /**
     * Euroista ulkomaan valuutaksi-sivulle tekstikentta rahamaaran syottamiselle
     */
    private TextField rahasumma = new TextField();
    /**
     * Euroista ulkomaan valuutaksi-sivulle teksti ComboBoxin ylapuolelle
     */
    private Label label2 = new Label("Valuutta: ");
    /**
     * Euroista ulkomaan valuutaksi-sivulle teksti tuloksen ylapuolelle
     */
    private Label tulos1 = new Label("Tulos: ");
    /**
     * Euroista ulkomaan valuutaksi-sivulle tekstikentta, johon paivittyy muunnoksen tulos
     */
    private TextField tulos2 = new TextField();
    /**
     * Euroista ulkomaan valuutaksi-sivulle nappi, jota painamalla ohjelma laskee muunnoksen euroista
     * ulkomaan valuutaan
     */
    private Button muunna = new Button("Muunna");
    /**
     * Nappi, jota painamalla tekstikentat tyhjentyvat
     */
    private Button tyhjenna = new Button("Tyhjennä");


    /**
     * Ulkomaan valuutasta euroiksi- sivun otsikko
     */
    private Label esittely2 = new Label("Muunna ulkomaan valuutat euroiksi");
    /**
     * Ulkomaan valuutasta euroiksi- sivulle teksti ComcoBoxin ylapuolelle, joka pyytaa valitsemaan valuutan
     */
    private Label annaValuutta = new Label("Anna valuutta: ");
    /**
     * Ulkomaan valuutasta euroiksi- sivulle nappi, jota painamalla ohjelma laskee
     * muunnoksen ulkomaan valuutasta euroiksi
     */
    private Button muunnaEuroiksi = new Button("Muunna");

    /**
     * Tietoa valuutoista-sivulle selittavaa tekstia
     */
    private Label otsikkoTiedot1 = new Label("Alapuolella on listattu tässä valuuttamuuntimessa käytetyt valuutat, ja niiden kurssit.");
    /**
     * Tietoa valuutoista-sivulle selittavaa tekstia, jossa kerrotaan vastavaluutta ja kurssien paivamaara
     */
    private Label otsikkoTiedot3 = new Label("Vastavaluutta on euro. Päivämäärä: 24.3.2023");
    /**
     * Tietoa valuutoista-sivulle taulukko
     */
    private TableView taulukko = new TableView();
    /**
     * Tietoa valuutoista-sivun taulukkoon sarake valuutan nimelle
     */
    private TableColumn nimi = new TableColumn("Valuutan nimi");
    /**
     * Tietoa valuutoista-sivun taulukkoon sarake valuuttakoodille
     */
    private TableColumn koodi = new TableColumn("Valuuttakoodi");
    /**
     * Tietoa valuutoista-sivun taulukkoon sarake myyntikurssille
     */
    private TableColumn myyntikurssi = new TableColumn("Myyntikurssi");
    /**
     * Tietoa valuutoista-sivun taulukkoon sarake ostokurssille
     */
    private TableColumn ostokurssi = new TableColumn("Ostokurssi");


    /**
     * Asetteluun tyhjia Label-olioita
     */
    private Label empty = new Label("");
    private Label empty2 = new Label("");
    private Label empty3 = new Label("");
    private Label empty5 = new Label("");

    /**
     * Valuuttamuuntaja-oliot jokaiselle valuutalle, asetetaan perustiedot
     */
    Valuuttamuuntaja usd_olio = new Valuuttamuuntaja("Yhdysvaltain dollari", "USD", 1.0596,1.0925);
    Valuuttamuuntaja gbp_olio = new Valuuttamuuntaja("Englannin punta", "GBP", 0.8656,0.8940);
    Valuuttamuuntaja sek_olio = new Valuuttamuuntaja("Ruotsin kruunu", "SEK",11.0338,11.4063);
    Valuuttamuuntaja nok_olio = new Valuuttamuuntaja("Norjan kruunu", "NOK",11.1024,11.4671);
    Valuuttamuuntaja dkk_olio = new Valuuttamuuntaja("Tanskan kruunu", "DKK",7.3401,7.5661);
    Valuuttamuuntaja chf_olio = new Valuuttamuuntaja("Sveitsin frangi", "CHF",0.9453,1.0346);
    Valuuttamuuntaja isk_olio = new Valuuttamuuntaja("Islannin kruunu", "ISK", 146.9510, 153.2550);
    Valuuttamuuntaja aud_olio = new Valuuttamuuntaja("Australian dollari", "AUD", 1.5536, 1.6855);
    Valuuttamuuntaja hkd_olio = new Valuuttamuuntaja("Hong Kongin dollari", "HKD",8.2963,8.5964 );
    Valuuttamuuntaja krw_olio = new Valuuttamuuntaja("Etelä-Korean won", "KRW", 1364.8885,1423.9524);
    Valuuttamuuntaja jpy_olio = new Valuuttamuuntaja("Japanin jeni", "JPY",137.8144,143.5210);
    Valuuttamuuntaja cny_olio = new Valuuttamuuntaja("Kiinan yuan", "CNY", 7.2405, 7.5396);
    Valuuttamuuntaja cad_olio = new Valuuttamuuntaja("Kanadan dollari", "CAD", 1.4097,1.5496);
    Valuuttamuuntaja pln_olio = new Valuuttamuuntaja("Puolan zloty", "PLN", 4.6014,5.0301);

    /**
     * Ohjelmaikkunan kaynnistyksen ja toiminnallisuuden maarittely
     * @param alkuIkkuna Stage
     * @throws Exception
     */
    public void start(Stage alkuIkkuna) throws Exception {

        /**
         * Luodaan lista Valuuttamuuntaja-olioista
         */
        Valuuttamuuntaja [] oliot = {usd_olio, gbp_olio, sek_olio, nok_olio, dkk_olio, chf_olio, isk_olio, aud_olio,
                hkd_olio, krw_olio, jpy_olio, cny_olio, cad_olio, pln_olio};

        /**
         * Luodaan binaaritiedosto objektit.dat
         */
        FileOutputStream objektit = new FileOutputStream("objektit.dat");
        ObjectOutputStream tiedosto = new ObjectOutputStream(objektit);

        /**
         * kaydaan oliot-lista lapi ja tallennetaan listassa olevat oliot yksitellen
         * tiedostoon
         */
        for (int i = 0; i < oliot.length; i++) {
            tiedosto.writeObject(oliot[i]);
        }
        /**
         * Suljetaan OutputStream
         */
        tiedosto.close();

        /**
         * Muotoillaan tekstien ulkoasuja
         */
        label1.setFont(new Font(15));
        label2.setFont(new Font(15));
        tulos1.setFont(new Font(15));
        annaValuutta.setFont(new Font(15));
        otsikko.setFont(new Font("Elephant", 30));
        esittely.setFont(new Font(25));
        esittely2.setFont(new Font(25));
        otsikko1.setFont(new Font(15));
        otsikko2.setFont(new Font(15));

        /**
         * Muotoillaan tekstikenttien kokoa
         */
        tulos2.setMaxWidth(100);
        rahasumma.setMaxWidth(90);


        /**
         * Asetetaan ComboBoxiin koko ja lisataan siihen lista valuuttakoodeista
         */
        valintaBox.setPrefWidth(80);
        ObservableList<String> alkiot = FXCollections.observableArrayList(valintaOtsikot);
        valintaBox.getItems().addAll(alkiot);

        /**
         * luodaan ObservableList, ja lisataan siihen while-loopilla Valuuttamuuntaja-oliot aiemmin
         * luodusta tiedostosta
         */
        ObservableList<Valuuttamuuntaja> tiedot =  FXCollections.observableArrayList();

        boolean tiedostoloppu = true;
        FileInputStream fstream = new FileInputStream("objektit.dat");
        ObjectInputStream inputFile = new ObjectInputStream(fstream);
        Valuuttamuuntaja luku;

        while (tiedostoloppu) {
            try {
                luku = (Valuuttamuuntaja) inputFile.readObject();
                tiedot.add(luku);
            }
            catch (EOFException e) {
                tiedostoloppu = false;
            }
        }

        /**
         * Suljetaan InputStream
         */
        inputFile.close();


        /**
         * Lisataan taulukon sarakkeille tiedot
         */
        nimi.setCellValueFactory(new PropertyValueFactory<Valuuttamuuntaja, String>("maan_valuutta"));
        koodi.setCellValueFactory(new PropertyValueFactory<Valuuttamuuntaja, String>("valuuttakoodi"));
        myyntikurssi.setCellValueFactory(new PropertyValueFactory<Valuuttamuuntaja, String>("myyntiKurssi"));
        ostokurssi.setCellValueFactory(new PropertyValueFactory<Valuuttamuuntaja, String>("ostoKurssi"));

        /**
         * Asetetaan taulukko niin, ettei sitä voi muokata
         */
        taulukko.setEditable(false);

        /**
         * Lisataan taulukkoon sarakkeet ja lista
         */
        taulukko.setItems(tiedot);
        taulukko.getColumns().addAll(nimi, koodi, myyntikurssi, ostokurssi);



        /**
         * Luodaan BorderPane- paneeli
         */
        BorderPane borderPane = new BorderPane();
        /**
         * Piirretaan ylapalkki ja asetetaan se sivun ylaosaan
         */
        HBox topBar = addTopBar();
        borderPane.setTop(topBar);
        /**
         * Piirretaan etusivu naytolle
         */
        borderPane.setCenter(addEtusivu());


        /**
         * Lisataan toiminnallisuus ylapalkin Etusivu-napille. Kun nappia painetaan,
         * etusivu piirretaan naytolle. Tekstikentat myos tyhjentyvat, kun nappia painetaan
         */
        buttonEtusivu.setOnAction (e -> {
            rahasumma.setText("");
            tulos2.setText("");
            borderPane.setCenter(addEtusivu());
        });

        /**
         * Lisataan toiminnallisuus ylapalkin Euroista ulkomaan valuutaksi- napille. Kun nappia painetaan,
         * oikea sivu piirtyy naytolle. Tekstikentat myos tyhjentyvat, kun nappia painetaan
         */
        buttonEuro.setOnAction (e -> {
            rahasumma.setText("");
            tulos2.setText("");
            valintaBox.setValue(null);
            borderPane.setCenter(addEurot());
        });
        /**
         * Lisataan toiminnallisuus ylapalkin Euroista ulkomaan valuutaksi- napille. Kun nappia painetaan,
         * oikea sivu piirtyy naytolle. Tekstikentat myos tyhjentyvat, kun nappia painetaan
         */
        buttonUlkomaa.setOnAction (e -> {
            rahasumma.setText("");
            tulos2.setText("");
            valintaBox.setValue(null);
            borderPane.setCenter(addUlkomaa());

        });
        /**
         * Lisataan toiminnallsuus ylapalkin Tietoa valuutoista- napille. Kun nappia painetaan,
         * oikea sivu piirtyy naytolle. Tekstikentat myos tyhjentyvat, kun nappia painetaan
         */
        buttonTietoa.setOnAction (e -> {
            rahasumma.setText("");
            tulos2.setText("");
            valintaBox.setValue(null);
            borderPane.setCenter(addTietoa());
        });


        /**
         * Lisataan toiminnallisuus Euroista ulkomaan valuutaksi-sivun muunna-napille.
         * Jos kayttaja ei ole valinnut valuuttaa tai syottanyt rahasummaa, mitaan ei tapahdu.
         * Nappia painamalla tulos paivittyy teksikenttaan
         */
        muunna.setOnAction (e -> {

            if (onkoNumero(rahasumma.getText())) {
                double arvo = Double.parseDouble(rahasumma.getText());

                if (valintaBox.getValue() == null) {
                }
                if (valintaBox.getValue() == "USD") {
                    usd_olio.setMaara(arvo);
                    String usd_tulos = usd_olio.LaskeMuunnosEuro(usd_olio);
                    tulos2.setText("$" + usd_tulos);
                } else if (valintaBox.getValue() == "GBP") {
                    gbp_olio.setMaara(arvo);
                    String gbp_tulos = gbp_olio.LaskeMuunnosEuro(gbp_olio);
                    tulos2.setText("£" + gbp_tulos);
                } else if (valintaBox.getValue() == "SEK") {
                    sek_olio.setMaara(arvo);
                    String sek_tulos = sek_olio.LaskeMuunnosEuro(sek_olio);
                    tulos2.setText("kr " + sek_tulos);
                } else if (valintaBox.getValue() == "NOK") {
                    nok_olio.setMaara(arvo);
                    String nok_tulos = nok_olio.LaskeMuunnosEuro(nok_olio);
                    tulos2.setText("kr " + nok_tulos);
                } else if (valintaBox.getValue() == "DKK") {
                    dkk_olio.setMaara(arvo);
                    String dkk_tulos = dkk_olio.LaskeMuunnosEuro(dkk_olio);
                    tulos2.setText("kr " + dkk_tulos);
                } else if (valintaBox.getValue() == "CHF") {
                    chf_olio.setMaara(arvo);
                    String chf_tulos = chf_olio.LaskeMuunnosEuro(chf_olio);
                    tulos2.setText(chf_tulos + " Fr");
                } else if (valintaBox.getValue() == "ISK") {
                    isk_olio.setMaara(arvo);
                    String isk_tulos = isk_olio.LaskeMuunnosEuro(isk_olio);
                    tulos2.setText("kr " + isk_tulos);
                } else if (valintaBox.getValue() == "AUD") {
                    aud_olio.setMaara(arvo);
                    String aud_tulos = aud_olio.LaskeMuunnosEuro(aud_olio);
                    tulos2.setText("$" + aud_tulos);
                } else if (valintaBox.getValue() == "HKD") {
                    hkd_olio.setMaara(arvo);
                    String hkd_tulos = hkd_olio.LaskeMuunnosEuro(hkd_olio);
                    tulos2.setText("HK$" + hkd_tulos);
                } else if (valintaBox.getValue() == "KRW") {
                    krw_olio.setMaara(arvo);
                    String krw_tulos = krw_olio.LaskeMuunnosEuro(krw_olio);
                    tulos2.setText(krw_tulos + "₩");
                } else if (valintaBox.getValue() == "JPY") {
                    jpy_olio.setMaara(arvo);
                    String jpy_tulos = jpy_olio.LaskeMuunnosEuro(jpy_olio);
                    tulos2.setText(jpy_tulos + "¥");
                } else if (valintaBox.getValue() == "CNY") {
                    cny_olio.setMaara(arvo);
                    String cny_tulos = cny_olio.LaskeMuunnosEuro(cny_olio);
                    tulos2.setText(cny_tulos + "¥");
                } else if (valintaBox.getValue() == "CAD") {
                    cad_olio.setMaara(arvo);
                    String cad_tulos = cad_olio.LaskeMuunnosEuro(cad_olio);
                    tulos2.setText("$" + cad_tulos);
                } else if (valintaBox.getValue() == "PLN") {
                    pln_olio.setMaara(arvo);
                    String pln_tulos = pln_olio.LaskeMuunnosEuro(pln_olio);
                    tulos2.setText(pln_tulos + " zł");
                }

            }
        });


        /**
         * Lisataan toiminnallisuus Ulkomaan valuutasta euroiksi-sivun muunna-napille.
         * Jos kayttaja ei ole valinnut valuuttaa tai syottanyt rahasummaa, mitaan ei tapahdu.
         * Nappia painamalla tulos paivittyy teksikenttaan
         */
        muunnaEuroiksi.setOnAction( e -> {
             if (onkoNumero(rahasumma.getText())) {
                 double arvo = Double.parseDouble(rahasumma.getText());

                 if (valintaBox.getValue() == null) {
                 }
                 else if (valintaBox.getValue() == "USD") {
                     usd_olio.setMaara(arvo);
                     String usd_tulos = usd_olio.LaskeMuunnosUlkomaa(usd_olio);
                     tulos2.setText(usd_tulos + "€");
                 } else if (valintaBox.getValue() == "GBP") {
                     gbp_olio.setMaara(arvo);
                     String gbp_tulos = gbp_olio.LaskeMuunnosUlkomaa(gbp_olio);
                     tulos2.setText(gbp_tulos + "€");
                 } else if (valintaBox.getValue() == "SEK") {
                     sek_olio.setMaara(arvo);
                     String sek_tulos = sek_olio.LaskeMuunnosUlkomaa(sek_olio);
                     tulos2.setText(sek_tulos + "€");
                 } else if (valintaBox.getValue() == "NOK") {
                     nok_olio.setMaara(arvo);
                     String nok_tulos = nok_olio.LaskeMuunnosUlkomaa(nok_olio);
                     tulos2.setText(nok_tulos + "€");
                 } else if (valintaBox.getValue() == "DKK") {
                     dkk_olio.setMaara(arvo);
                     String dkk_tulos = dkk_olio.LaskeMuunnosUlkomaa(dkk_olio);
                     tulos2.setText(dkk_tulos + "€");
                 } else if (valintaBox.getValue() == "CHF") {
                     chf_olio.setMaara(arvo);
                     String chf_tulos = chf_olio.LaskeMuunnosUlkomaa(chf_olio);
                     tulos2.setText(chf_tulos + "€");
                 } else if (valintaBox.getValue() == "ISK") {
                     isk_olio.setMaara(arvo);
                     String isk_tulos = isk_olio.LaskeMuunnosEuro(isk_olio);
                     tulos2.setText(isk_tulos + "€");
                 } else if (valintaBox.getValue() == "AUD") {
                     aud_olio.setMaara(arvo);
                     String aud_tulos = aud_olio.LaskeMuunnosEuro(aud_olio);
                     tulos2.setText(aud_tulos + "€");
                 } else if (valintaBox.getValue() == "HKD") {
                     hkd_olio.setMaara(arvo);
                     String hkd_tulos = hkd_olio.LaskeMuunnosEuro(hkd_olio);
                     tulos2.setText(hkd_tulos + "€");
                 } else if (valintaBox.getValue() == "KRW") {
                     krw_olio.setMaara(arvo);
                     String krw_tulos = krw_olio.LaskeMuunnosEuro(krw_olio);
                     tulos2.setText(krw_tulos + "€");
                 } else if (valintaBox.getValue() == "JPY") {
                     jpy_olio.setMaara(arvo);
                     String jpy_tulos = jpy_olio.LaskeMuunnosEuro(jpy_olio);
                     tulos2.setText(jpy_tulos + "€");
                 } else if (valintaBox.getValue() == "CNY") {
                     cny_olio.setMaara(arvo);
                     String cny_tulos = cny_olio.LaskeMuunnosEuro(cny_olio);
                     tulos2.setText(cny_tulos + "€");
                 } else if (valintaBox.getValue() == "CAD") {
                     cad_olio.setMaara(arvo);
                     String cad_tulos = cad_olio.LaskeMuunnosEuro(cad_olio);
                     tulos2.setText(cad_tulos + "€");
                 } else if (valintaBox.getValue() == "PLN") {
                     pln_olio.setMaara(arvo);
                     String pln_tulos = pln_olio.LaskeMuunnosEuro(pln_olio);
                     tulos2.setText(pln_tulos + "€");
                 }
             }
        });


        /**
         * Tyhjenna-nappi tyhjentaa kayttajan syotteet
         */
        tyhjenna.setOnAction(x -> {
            rahasumma.setText("");
            tulos2.setText("");
            valintaBox.setValue(null);
        });

        /**
         * Luodaan Scene, johon sijoitetaan paneeli ja asetetaan sivun koko
         */
        Scene pohja = new Scene(borderPane, 780, 550);
        /**
         * Asetetaan sivulle otsikko ja naytetaan ikkuna
         */
        alkuIkkuna.setTitle("Valuuttamuuntaja");
        alkuIkkuna.setScene(pohja);
        alkuIkkuna.show();
    }

    /**
     * Metodi, joka piirtaa ylapalkin sivulle
     * @return HBox paneeli
     */
    public HBox addTopBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        buttonEtusivu.setPrefSize(100, 20);
        buttonEuro.setPrefSize(180, 20);
        buttonUlkomaa.setPrefSize(180, 20);
        buttonTietoa.setPrefSize(140, 20);

        hbox.getChildren().addAll(buttonEtusivu, buttonEuro, buttonUlkomaa, buttonTietoa);

        return hbox;
    }


    /**
     * Metodi, joka piirtaa etusivun
     * @return VBox paneeli
     */
    public VBox addEtusivu() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.setAlignment(Pos.CENTER);

        otsikko.setFont(new Font("Veranda", 25));
        vbox.getChildren().add(otsikko);
        vbox.getChildren().add(otsikko1);
        vbox.getChildren().add(otsikko2);

        return vbox;
    }

    /**
     * Metodi, joka tarkistaa, sisaltaako merkkijono numeroita.
     * Jos merkkijono sisaltyy vain numeroista, palautetaan true, muuten false.
     * @param str String
     * @return boolean totuusarvo
     */
    public boolean onkoNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Metodi, joka piirtaa naytolle sivun, jossa voi muuntaa eurot ulkomaan valuutaksi
     * @return VBox paneeli
     */
    public VBox addEurot() {
        VBox paneeli = new VBox();
        paneeli.setAlignment(Pos.CENTER);
        paneeli.setSpacing(10);

        paneeli.getChildren().addAll(esittely, empty2, empty3, label1, rahasumma, label2, valintaBox);
        paneeli.getChildren().addAll(empty5, muunna, tyhjenna, empty, tulos1, tulos2);

        return paneeli;
    }


    /**
     * Metodi joka piirtaa naytolle sivun, jossa voi muuntaa ulkomaan valuutat euroiksi
     * @return VBox paneeli
     */
    public VBox addUlkomaa() {
        VBox paneeli = new VBox();
        paneeli.setAlignment(Pos.CENTER);
        paneeli.setSpacing(10);

        paneeli.getChildren().addAll(esittely2, empty2, empty3, annaValuutta, valintaBox, label1, rahasumma);
        paneeli.getChildren().addAll(empty5, muunnaEuroiksi, tyhjenna, empty, tulos1, tulos2 );

        return paneeli;
    }

    /**
     * Metodi, joka piirtaa naytolle sivun, jossa voi tarkastella valuuttojen tietoja taulukosta
     * @return VBox paneeli
     */
    public VBox addTietoa() {
        VBox paneeli = new VBox();
        paneeli.setSpacing(5);
        paneeli.setPadding(new Insets(10, 0, 0, 10));
        paneeli.getChildren().addAll(otsikkoTiedot1, otsikkoTiedot3, taulukko);

        return paneeli;

    }


    /**
     * ohjelman kaynnistava metodi
     * @param args kutsuparametreja ei kayteta
     */
    public static void main(String args[]) {

        Application.launch(args);
    }
}
