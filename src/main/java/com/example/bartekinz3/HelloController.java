package com.example.bartekinz3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class HelloController {
    @FXML
    private ComboBox<String> mapa;
    @FXML
    private ComboBox<String> gora;
    @FXML
    private ComboBox<String> las;
    @FXML
    private ComboBox<String> jezioro;
    @FXML
    private ComboBox<String> rzeka;
    @FXML
    private ComboBox<String> wioska;
    @FXML
    private ComboBox<String> droga;
    @FXML
    private Button GenerujBtn;

    @FXML
    protected void onGenerujButtonClick() {
        ustawProporcjeMapy(mapa.getValue(), gora.getValue(), las.getValue(), jezioro.getValue(), rzeka.getValue(), wioska.getValue(), droga.getValue());
    }

    public static int iloscSasiadow = 0;
    public void ustawProporcjeMapy(String mapa, String gora, String las, String jezioro, String rzeka, String wioska, String droga){

        int wielkoscMapy = 0;
        int iloscGor = 0;
        int iloscLasow = 0;
        int iloscJezior = 0;
        int iloscRzek = 0;
        int iloscWiosek = 0;
        int iloscDrog = 0;

        if(mapa.contains("Small")){
            wielkoscMapy = 80;
        } else if (mapa.contains("Medium")) {
            wielkoscMapy = 120;
        } else if (mapa.contains("Large")) {
            wielkoscMapy = 160;
        } else if (mapa.contains("Huge")) {
            wielkoscMapy = 200;
        }


        if (gora.contains("Small")) {
            iloscGor = (int) (Math.pow(wielkoscMapy, 2) * 0.1);
        } else if (gora.contains("Medium")) {
            iloscGor = (int) (Math.pow(wielkoscMapy, 2) * 0.25);
        } else if (gora.contains("Large")) {
            iloscGor = (int) (Math.pow(wielkoscMapy, 2) * 0.40);
        }

        if (las.contains("Small")) {
            iloscLasow = (int) (Math.pow(wielkoscMapy, 2) * 0.1);
        } else if (las.contains("Medium")) {
            iloscLasow = (int) (Math.pow(wielkoscMapy, 2) * 0.25);
        } else if (las.contains("Large")) {
            iloscLasow = (int) (Math.pow(wielkoscMapy, 2) * 0.40);
        }

        if (jezioro.contains("Small")) {
            iloscJezior = (int) (Math.pow(wielkoscMapy, 2) * 0.1);
        } else if (jezioro.contains("Medium")) {
            iloscJezior = (int) (Math.pow(wielkoscMapy, 2) * 0.25);
        } else if (jezioro.contains("Large")) {
            iloscJezior = (int) (Math.pow(wielkoscMapy, 2) * 0.40);
        }

        if (rzeka.contains("Small")) {
            iloscRzek = (int) (Math.pow(wielkoscMapy, 2) * 0.1);
        } else if (rzeka.contains("Medium")) {
            iloscRzek = (int) (Math.pow(wielkoscMapy, 2) * 0.20);
        } else if (rzeka.contains("Large")) {
            iloscRzek = (int) (Math.pow(wielkoscMapy, 2) * 0.30);
        }

        if (wioska.contains("Small")) {
            iloscWiosek = (int) (Math.pow(wielkoscMapy, 2) * 0.1);
        } else if (wioska.contains("Medium")) {
            iloscWiosek = (int) (Math.pow(wielkoscMapy, 2) * 0.25);
        } else if (wioska.contains("Large")) {
            iloscWiosek = (int) (Math.pow(wielkoscMapy, 2) * 0.40);
        }

        if (droga.contains("Small")) {
            iloscDrog = (int) (Math.pow(wielkoscMapy, 2) * 0.1);
        } else if (droga.contains("Medium")) {
            iloscDrog = (int) (Math.pow(wielkoscMapy, 2) * 0.20);
        } else if (droga.contains("Large")) {
            iloscDrog = (int) (Math.pow(wielkoscMapy, 2) * 0.30);
        }

        double udzialProcentowy = Math.pow(wielkoscMapy, 2)/((double) iloscGor + (double)iloscLasow + (double)iloscJezior + (double)iloscWiosek);

        if(udzialProcentowy < 1){
            iloscGor = (int) (udzialProcentowy * iloscGor);
            iloscLasow = (int) (udzialProcentowy * iloscLasow);
            iloscJezior = (int) (udzialProcentowy * iloscJezior);
            iloscWiosek = (int) (udzialProcentowy * iloscWiosek);
        }

        System.out.println("iloscGor: " + iloscGor);
        System.out.println("iloscLasow: " + iloscLasow);
        System.out.println("iloscJezior: " + iloscJezior);
        System.out.println("iloscWiosek: " + iloscWiosek);

        generowanieMapy(wielkoscMapy, iloscGor, iloscLasow, iloscJezior, iloscWiosek, iloscDrog, iloscRzek);
    }

    private void generowanieMapy(int wielkoscMapy,int iloscGor, int iloscLasow, int iloscJezior, int iloscWiosek, int iloscDrog, int iloscRzek){


        int[][] grid = new int[wielkoscMapy][wielkoscMapy];

        int[] miejsceStartoweGor = new int[2];
        int[] miejsceStartoweLas = new int[2];
        int[] miejsceStartoweJezioro = new int[2];
        int[] miejsceStartoweWiosek = new int[2];

        int[] miejsceStartoweDrog = new int[2];
        int[] miejsceStartoweRzek = new int[2];

        miejsceStartoweGor[0] = (int) (Math.random() * (wielkoscMapy-1));
        miejsceStartoweGor[1] = (int) (Math.random() * (wielkoscMapy-1));

        miejsceStartoweLas[0] = (int) (Math.random() * (wielkoscMapy-1));
        miejsceStartoweLas[1] = (int) (Math.random() * (wielkoscMapy-1));

        miejsceStartoweJezioro[0] = (int) (Math.random() * (wielkoscMapy-1));
        miejsceStartoweJezioro[1] = (int) (Math.random() * (wielkoscMapy-1));

        miejsceStartoweWiosek[0] = (int) (Math.random() * (wielkoscMapy-1));
        miejsceStartoweWiosek[1] = (int) (Math.random() * (wielkoscMapy-1));


        /*
        0 - łąka
        1 - góry
        2 - las
        3 - jezioro
        4 - wioska
        5 - rzeka
        6 - droga
        */

        // ZAPELNIANIE MACIERZY
        for (int[] pole:grid) {
            for(int i = 0; i < wielkoscMapy; i++){
                pole[i] = 0;
            }
        }

        // GENEROWANIE POSZCZEGOLNYCH MIEJSC

        /*
        1 - lewo
        2 - prawo
        3 - gora
        4 - dol
        */
        // GENEROWANIE GOR

        grid[miejsceStartoweGor[0]][miejsceStartoweGor[1]] = 1;
        int pozostalePolaGor = iloscGor;
        int aktualnaPozycjaGor_X = miejsceStartoweGor[0];
        int aktualnaPozycjaGor_Y = miejsceStartoweGor[1];

        while(pozostalePolaGor > 0){
            int czyMaSieWygenerowac = (int) (Math.random() * (100));
            if(czyMaSieWygenerowac > 10){
                int wKtoraStroneIdzie = (int) (Math.random() * (4) + 1);


                switch(wKtoraStroneIdzie){
                    case 1:
                        aktualnaPozycjaGor_X -= 1;
                        break;
                    case 2:
                        aktualnaPozycjaGor_X += 1;
                        break;
                    case 3:
                        aktualnaPozycjaGor_Y -= 1;
                        break;
                    case 4:
                        aktualnaPozycjaGor_Y += 1;
                        break;
                }

                // SPRAWDZANIE ILOSCI SASIADOW
                try{
                    if(grid[miejsceStartoweGor[aktualnaPozycjaGor_X - 1]][miejsceStartoweGor[aktualnaPozycjaGor_Y - 1]] == 1){
                        iloscSasiadow += 1;
                    }
                }
                catch (Exception ex){

                }
                try{
                    if(grid[miejsceStartoweGor[aktualnaPozycjaGor_X]][miejsceStartoweGor[aktualnaPozycjaGor_Y - 1]] == 1){
                        iloscSasiadow += 1;
                    }
                }
                catch (Exception ex){

                }

                try{
                    if(grid[miejsceStartoweGor[aktualnaPozycjaGor_X + 1]][miejsceStartoweGor[aktualnaPozycjaGor_Y - 1]] == 1){
                        iloscSasiadow += 1;
                    }
                }
                catch (Exception ex){

                }

                try{
                    if(grid[miejsceStartoweGor[aktualnaPozycjaGor_X + 1]][miejsceStartoweGor[aktualnaPozycjaGor_Y]] == 1){
                        iloscSasiadow += 1;
                    }
                }
                catch (Exception ex){

                }
                try{
                    if(grid[miejsceStartoweGor[aktualnaPozycjaGor_X + 1]][miejsceStartoweGor[aktualnaPozycjaGor_Y + 1]] == 1){
                        iloscSasiadow += 1;
                    }
                }
                catch (Exception ex){

                }
                try{
                    if(grid[miejsceStartoweGor[aktualnaPozycjaGor_X]][miejsceStartoweGor[aktualnaPozycjaGor_Y + 1]] == 1){
                        iloscSasiadow += 1;
                    }
                }
                catch (Exception ex){

                }
                try{
                    if(grid[miejsceStartoweGor[aktualnaPozycjaGor_X - 1]][miejsceStartoweGor[aktualnaPozycjaGor_Y + 1]] == 1){
                        iloscSasiadow += 1;
                    }
                }
                catch (Exception ex){

                }
                try{
                    if(grid[miejsceStartoweGor[aktualnaPozycjaGor_X - 1]][miejsceStartoweGor[aktualnaPozycjaGor_Y]] == 1){
                        iloscSasiadow += 1;
                    }
                }
                catch (Exception ex){

                }

                if(iloscSasiadow < 4){
                    try{
                        assert grid[aktualnaPozycjaGor_X] != null;
                        grid[aktualnaPozycjaGor_X][aktualnaPozycjaGor_Y] = 1;

                        pozostalePolaGor -=1;
                    }
                    catch (Exception ex){

                    }
                }
            }
        }
        // GENEROWANIE LASOW

        grid[miejsceStartoweLas[0]][miejsceStartoweLas[1]] = 2;

        int pozostalePolaLasow = iloscLasow;
        int aktualnaPozycjaLasow_X = miejsceStartoweLas[0];
        int aktualnaPozycjaLasow_Y = miejsceStartoweLas[1];

        while(pozostalePolaLasow > 0){
            int czyMaSieWygenerowac = (int) (Math.random() * (100));
            if(czyMaSieWygenerowac > 10){
                int wKtoraStroneIdzie = (int) (Math.random() * (4) + 1);


                switch(wKtoraStroneIdzie){
                    case 1:
                        aktualnaPozycjaLasow_X -= 1;
                        break;
                    case 2:
                        aktualnaPozycjaLasow_X += 1;
                        break;
                    case 3:
                        aktualnaPozycjaLasow_Y -= 1;
                        break;
                    case 4:
                        aktualnaPozycjaLasow_Y += 1;
                        break;
                }

                try {
                    if (grid[aktualnaPozycjaLasow_X][aktualnaPozycjaLasow_Y] == 0) {

                        // SPRAWDZANIE ILOSCI SASIADOW
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaLasow_X - 1]][miejsceStartoweGor[aktualnaPozycjaLasow_Y - 1]] == 2) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaLasow_X]][miejsceStartoweGor[aktualnaPozycjaLasow_Y - 1]] == 2) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaLasow_X + 1]][miejsceStartoweGor[aktualnaPozycjaLasow_Y - 1]] == 2) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaLasow_X + 1]][miejsceStartoweGor[aktualnaPozycjaLasow_Y]] == 2) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaLasow_X + 1]][miejsceStartoweGor[aktualnaPozycjaLasow_Y + 1]] == 2) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaLasow_X]][miejsceStartoweGor[aktualnaPozycjaLasow_Y + 1]] == 2) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaLasow_X - 1]][miejsceStartoweGor[aktualnaPozycjaLasow_Y + 1]] == 2) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaLasow_X - 1]][miejsceStartoweGor[aktualnaPozycjaLasow_Y]] == 2) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        if (iloscSasiadow < 4) {
                            try {
                                assert grid[aktualnaPozycjaLasow_X] != null;
                                grid[aktualnaPozycjaLasow_X][aktualnaPozycjaLasow_Y] = 2;

                                pozostalePolaLasow -= 1;
                            } catch (Exception ex) {

                            }
                        }
                    }
                }
                catch (Exception ex){

                }
            }
        }

        // GENEROWANIE JEZIOR

        grid[miejsceStartoweJezioro[0]][miejsceStartoweJezioro[1]] = 3;

        int pozostalePolaJezior= iloscJezior;
        int aktualnaPozycjaJezior_X = miejsceStartoweJezioro[0];
        int aktualnaPozycjaJezior_Y = miejsceStartoweJezioro[1];

        while(pozostalePolaJezior > 0){
            int czyMaSieWygenerowac = (int) (Math.random() * (100));
            if(czyMaSieWygenerowac > 10){
                int wKtoraStroneIdzie = (int) (Math.random() * (4) + 1);


                switch(wKtoraStroneIdzie){
                    case 1:
                        aktualnaPozycjaJezior_X -= 1;
                        break;
                    case 2:
                        aktualnaPozycjaJezior_X += 1;
                        break;
                    case 3:
                        aktualnaPozycjaJezior_Y -= 1;
                        break;
                    case 4:
                        aktualnaPozycjaJezior_Y += 1;
                        break;
                }

                try {
                    if (grid[aktualnaPozycjaJezior_X][aktualnaPozycjaJezior_Y] == 0) {

                        // SPRAWDZANIE ILOSCI SASIADOW
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaJezior_X - 1]][miejsceStartoweGor[aktualnaPozycjaJezior_Y - 1]] == 3) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaJezior_X]][miejsceStartoweGor[aktualnaPozycjaJezior_Y - 1]] == 3) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaJezior_X + 1]][miejsceStartoweGor[aktualnaPozycjaJezior_Y - 1]] == 3) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaJezior_X + 1]][miejsceStartoweGor[aktualnaPozycjaJezior_Y]] == 3) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaJezior_X + 1]][miejsceStartoweGor[aktualnaPozycjaJezior_Y + 1]] == 3) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaJezior_X]][miejsceStartoweGor[aktualnaPozycjaJezior_Y + 1]] == 3) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaJezior_X - 1]][miejsceStartoweGor[aktualnaPozycjaJezior_Y + 1]] == 3) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaJezior_X - 1]][miejsceStartoweGor[aktualnaPozycjaJezior_Y]] == 3) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        if (iloscSasiadow < 4) {
                            try {
                                assert grid[aktualnaPozycjaJezior_X] != null;
                                grid[aktualnaPozycjaJezior_X][aktualnaPozycjaJezior_Y] = 3;

                                pozostalePolaJezior -= 1;
                            } catch (Exception ex) {

                            }
                        }
                    }
                }
                catch (Exception ex){

                }
            }
        }

        // GENEROWANIE WIOSEK

        grid[miejsceStartoweWiosek[0]][miejsceStartoweWiosek[1]] = 4;

        int pozostalePolaWiosek = iloscWiosek;
        int aktualnaPozycjaWiosek_X = miejsceStartoweWiosek[0];
        int aktualnaPozycjaWiosek_Y = miejsceStartoweWiosek[1];

        while(pozostalePolaWiosek > 0){
            int czyMaSieWygenerowac = (int) (Math.random() * (100));
            if(czyMaSieWygenerowac > 10){
                int wKtoraStroneIdzie = (int) (Math.random() * (4) + 1);


                switch(wKtoraStroneIdzie){
                    case 1:
                        aktualnaPozycjaWiosek_X -= 1;
                        break;
                    case 2:
                        aktualnaPozycjaWiosek_X += 1;
                        break;
                    case 3:
                        aktualnaPozycjaWiosek_Y -= 1;
                        break;
                    case 4:
                        aktualnaPozycjaWiosek_Y += 1;
                        break;
                }

                try {
                    if (grid[aktualnaPozycjaWiosek_X][aktualnaPozycjaWiosek_Y] == 0) {

                        // SPRAWDZANIE ILOSCI SASIADOW
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaWiosek_X - 1]][miejsceStartoweGor[aktualnaPozycjaWiosek_Y - 1]] == 4) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaWiosek_X]][miejsceStartoweGor[aktualnaPozycjaWiosek_Y - 1]] == 4) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaWiosek_X + 1]][miejsceStartoweGor[aktualnaPozycjaWiosek_Y - 1]] == 4) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaWiosek_X + 1]][miejsceStartoweGor[aktualnaPozycjaWiosek_Y]] == 4) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaWiosek_X + 1]][miejsceStartoweGor[aktualnaPozycjaWiosek_Y + 1]] == 4) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaWiosek_X]][miejsceStartoweGor[aktualnaPozycjaWiosek_Y + 1]] == 4) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaWiosek_X - 1]][miejsceStartoweGor[aktualnaPozycjaWiosek_Y + 1]] == 4) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }
                        try {
                            if (grid[miejsceStartoweGor[aktualnaPozycjaWiosek_X - 1]][miejsceStartoweGor[aktualnaPozycjaWiosek_Y]] == 4) {
                                iloscSasiadow += 1;
                            }
                        } catch (Exception ex) {

                        }

                        if (iloscSasiadow < 4) {
                            try {
                                assert grid[aktualnaPozycjaWiosek_X] != null;
                                grid[aktualnaPozycjaWiosek_X][aktualnaPozycjaWiosek_Y] = 4;

                                pozostalePolaWiosek -= 1;
                            } catch (Exception ex) {

                            }
                        }
                    }
                }
                catch (Exception ex){

                }
            }
        }

        //GENEROWANIE RZEK
        

        //GENEROWANIE DROG

        // TEST
        try{
            File f = new File("testText.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));

            for (int[] pole:grid) {
                for(int i = 0; i < wielkoscMapy; i++){
                    writer.write(String.valueOf(pole[i]));
                    writer.write(" ");
                }
                writer.write("\n");
            }
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }


    }

    private void zapisMapyDoPliku(){

    }

    @FXML
    public void initialize() {
        mapa.getItems().addAll(
                "Small",
                "Medium",
                "Large",
                "Huge"
        );

        gora.getItems().addAll(
                "None",
                "Small",
                "Medium",
                "Large"
        );

        las.getItems().addAll(
                "None",
                "Small",
                "Medium",
                "Large"
        );

        jezioro.getItems().addAll(
                "None",
                "Small",
                "Medium",
                "Large"
        );

        rzeka.getItems().addAll(
                "None",
                "Small",
                "Medium",
                "Large"
        );

        wioska.getItems().addAll(
                "None",
                "Small",
                "Medium",
                "Large"
        );

        droga.getItems().addAll(
                "None",
                "Small",
                "Medium",
                "Large"
        );

        mapa.getSelectionModel().selectFirst();
        gora.getSelectionModel().selectFirst();
        las.getSelectionModel().selectFirst();
        jezioro.getSelectionModel().selectFirst();
        rzeka.getSelectionModel().selectFirst();
        wioska.getSelectionModel().selectFirst();
        droga.getSelectionModel().selectFirst();


    }
}