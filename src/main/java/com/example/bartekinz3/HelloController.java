package com.example.bartekinz3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    private ComboBox<String> wioska;
    @FXML
    private ComboBox<String> droga;
    @FXML
    private Button GenerujBtn;

    @FXML
    protected void onGenerujButtonClick() {
        ustawProporcjeMapy(mapa.getValue(), gora.getValue(), las.getValue(), jezioro.getValue(), wioska.getValue(), droga.getValue());
    }

    public static int iloscSasiadow = 0;
    public void ustawProporcjeMapy(String mapa, String gora, String las, String jezioro, String wioska, String droga){

        int wielkoscMapy = 0;
        int iloscGor = 0;
        int iloscLasow = 0;
        int iloscJezior = 0;
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

        if (wioska.contains("Small")) {
            iloscWiosek = (int) (Math.pow(wielkoscMapy, 2) * 0.1);
        } else if (wioska.contains("Medium")) {
            iloscWiosek = (int) (Math.pow(wielkoscMapy, 2) * 0.25);
        } else if (wioska.contains("Large")) {
            iloscWiosek = (int) (Math.pow(wielkoscMapy, 2) * 0.40);
        }

        if (droga.contains("Small")) {
            iloscDrog = 3;
        } else if (droga.contains("Medium")) {
            iloscDrog = 5;
        } else if (droga.contains("Large")) {
            iloscDrog = 7;
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

        generowanieMapy(wielkoscMapy, iloscGor, iloscLasow, iloscJezior, iloscWiosek, iloscDrog);
    }

    private void generowanieMapy(int wielkoscMapy,int iloscGor, int iloscLasow, int iloscJezior, int iloscWiosek, int iloscDrog){


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

        int maxLiczbaIteracji = 1000000;
        int iteracjaGor = 0;
        while(pozostalePolaGor > 0){
            iteracjaGor++;
            if(maxLiczbaIteracji < iteracjaGor){
                break;
            }
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

        int iteracjaLasow = 0;
        while(pozostalePolaLasow > 0){
            iteracjaLasow++;
            if(maxLiczbaIteracji < iteracjaLasow){
                break;
            }
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

        int iteracjaJezior = 0;
        while(pozostalePolaJezior > 0){
            iteracjaJezior++;
            if(maxLiczbaIteracji < iteracjaJezior){
                break;
            }
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

        int iteracjaWiosek = 0;
        while(pozostalePolaWiosek > 0){
            iteracjaWiosek++;
            if(maxLiczbaIteracji < iteracjaWiosek){
                break;
            }
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

        //GENEROWANIE DROG

        List<Integer> kolejnoscPoruszaniaSie = new ArrayList<>();
        List<int[]> punktyKoncowe = new ArrayList<>();

        if(iloscDrog == 3){
            int[] miejscePrzejsciaDrogi1 = new int[2];
            int[] miejscePrzejsciaDrogi2 = new int[2];
            int[] miejscePrzejsciaDrogi3 = new int[2];

            miejscePrzejsciaDrogi1[0] = miejsceStartoweWiosek[0];
            miejscePrzejsciaDrogi1[1] = miejsceStartoweWiosek[1];

            miejscePrzejsciaDrogi2[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi2[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi3[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi3[1] = (int) (Math.random() * (wielkoscMapy-1));

            punktyKoncowe.add(miejscePrzejsciaDrogi1);
            punktyKoncowe.add(miejscePrzejsciaDrogi2);
            punktyKoncowe.add(miejscePrzejsciaDrogi3);

            int[] wartosci_X = new int[3];
            int[] wartosci_Y = new int[3];
            double[][] odleglosci = new double[3][3];
            List<double[][]> punkty = new ArrayList<>();
            List<double[][]> punktyPomocnicze = new ArrayList<>();

            wartosci_X[0] = miejscePrzejsciaDrogi1[0];
            wartosci_X[1] = miejscePrzejsciaDrogi2[0];
            wartosci_X[2] = miejscePrzejsciaDrogi3[0];

            wartosci_Y[0] = miejscePrzejsciaDrogi1[1];
            wartosci_Y[1] = miejscePrzejsciaDrogi2[1];
            wartosci_Y[2] = miejscePrzejsciaDrogi3[1];

            for(int i = 0; i<3 ;i++){
                for(int j = 0; j<3 ;j++){
                    odleglosci[i][j] = Math.sqrt(Math.pow(wartosci_X[i] - wartosci_X[j], 2) +
                            Math.pow(wartosci_Y[i] - wartosci_Y[j], 2));
                }
                punkty.add(new double[][]{odleglosci[i]});
                punktyPomocnicze.add(new double[][]{odleglosci[i]});
            }

            int nastepnyPunkt = 0;
            double niepowatrzalny = 0.0;

            for(int j = 0; j < 3; j++){
                kolejnoscPoruszaniaSie.add(nastepnyPunkt);

                int tymaczasowyPunkt = 0;
                double[][] aktualnyPunkt = punkty.get(nastepnyPunkt);
                punktyPomocnicze.remove(aktualnyPunkt);

                double min = 1000000000000000.0; //liczba wieksza niż możliwa do ustawienia
                for(int i = 0; i<3;i++){
                    if(aktualnyPunkt[0][i] > 0.0){
                        if(aktualnyPunkt[0][i] < min && aktualnyPunkt[0][i] != niepowatrzalny){
                            min = aktualnyPunkt[0][i];
                            tymaczasowyPunkt = i;
                        }
                    }
                }
                niepowatrzalny = min;
                nastepnyPunkt = tymaczasowyPunkt;
            }

            System.out.println("Kolejnosc: ");
            for(int i = 0; i < kolejnoscPoruszaniaSie.size(); i++){
                System.out.println(kolejnoscPoruszaniaSie.get(i));
            }
        }

        if(iloscDrog == 5){
            int[] miejscePrzejsciaDrogi1 = new int[2];
            int[] miejscePrzejsciaDrogi2 = new int[2];
            int[] miejscePrzejsciaDrogi3 = new int[2];
            int[] miejscePrzejsciaDrogi4 = new int[2];
            int[] miejscePrzejsciaDrogi5 = new int[2];

            miejscePrzejsciaDrogi1[0] = miejsceStartoweWiosek[0];
            miejscePrzejsciaDrogi1[1] = miejsceStartoweWiosek[1];

            miejscePrzejsciaDrogi2[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi2[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi3[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi3[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi4[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi4[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi5[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi5[1] = (int) (Math.random() * (wielkoscMapy-1));

            punktyKoncowe.add(miejscePrzejsciaDrogi1);
            punktyKoncowe.add(miejscePrzejsciaDrogi2);
            punktyKoncowe.add(miejscePrzejsciaDrogi3);
            punktyKoncowe.add(miejscePrzejsciaDrogi4);
            punktyKoncowe.add(miejscePrzejsciaDrogi5);

            int[] wartosci_X = new int[5];
            int[] wartosci_Y = new int[5];
            double[][] odleglosci = new double[5][5];
            List<double[][]> punkty = new ArrayList<>();
            List<double[][]> punktyPomocnicze = new ArrayList<>();

            wartosci_X[0] = miejscePrzejsciaDrogi1[0];
            wartosci_X[1] = miejscePrzejsciaDrogi2[0];
            wartosci_X[2] = miejscePrzejsciaDrogi3[0];
            wartosci_X[3] = miejscePrzejsciaDrogi4[0];
            wartosci_X[4] = miejscePrzejsciaDrogi5[0];

            wartosci_Y[0] = miejscePrzejsciaDrogi1[1];
            wartosci_Y[1] = miejscePrzejsciaDrogi2[1];
            wartosci_Y[2] = miejscePrzejsciaDrogi3[1];
            wartosci_Y[3] = miejscePrzejsciaDrogi4[1];
            wartosci_Y[4] = miejscePrzejsciaDrogi5[1];

            for(int i = 0; i<5 ;i++){
                for(int j = 0; j<5 ;j++){
                    odleglosci[i][j] = Math.sqrt(Math.pow(wartosci_X[i] - wartosci_X[j], 2) +
                            Math.pow(wartosci_Y[i] - wartosci_Y[j], 2));
                }
                punkty.add(new double[][]{odleglosci[i]});
                punktyPomocnicze.add(new double[][]{odleglosci[i]});
            }

            int nastepnyPunkt = 0;
            List<Integer> niepowatrzalne = new ArrayList<>();

            for(int j = 0; j < 5; j++){
                niepowatrzalne.add(nastepnyPunkt);
                kolejnoscPoruszaniaSie.add(nastepnyPunkt);

                int tymaczasowyPunkt = 0;
                double[][] aktualnyPunkt = punkty.get(nastepnyPunkt);
                punktyPomocnicze.remove(aktualnyPunkt);

                double min = 1000000000000000.0; //liczba wieksza niż możliwa do ustawienia
                for(int i = 0; i<5;i++){
                    if(aktualnyPunkt[0][i] > 0.0){
                        if(aktualnyPunkt[0][i] < min && !niepowatrzalne.contains(i)){
                            System.out.println("WARTOSC I: " + i);
                            min = aktualnyPunkt[0][i];
                            tymaczasowyPunkt = i;
                        }
                    }
                }
                nastepnyPunkt = tymaczasowyPunkt;
            }

            System.out.println("Kolejnosc: ");
            for(int i = 0; i < kolejnoscPoruszaniaSie.size(); i++){
                System.out.println(kolejnoscPoruszaniaSie.get(i));
            }
        }
        if(iloscDrog == 7){

            int[] miejscePrzejsciaDrogi1 = new int[2];
            int[] miejscePrzejsciaDrogi2 = new int[2];
            int[] miejscePrzejsciaDrogi3 = new int[2];
            int[] miejscePrzejsciaDrogi4 = new int[2];
            int[] miejscePrzejsciaDrogi5 = new int[2];
            int[] miejscePrzejsciaDrogi6 = new int[2];
            int[] miejscePrzejsciaDrogi7 = new int[2];

            miejscePrzejsciaDrogi1[0] = miejsceStartoweWiosek[0];
            miejscePrzejsciaDrogi1[1] = miejsceStartoweWiosek[1];

            miejscePrzejsciaDrogi2[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi2[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi3[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi3[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi4[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi4[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi5[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi5[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi6[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi6[1] = (int) (Math.random() * (wielkoscMapy-1));

            miejscePrzejsciaDrogi7[0] = (int) (Math.random() * (wielkoscMapy-1));
            miejscePrzejsciaDrogi7[1] = (int) (Math.random() * (wielkoscMapy-1));

            punktyKoncowe.add(miejscePrzejsciaDrogi1);
            punktyKoncowe.add(miejscePrzejsciaDrogi2);
            punktyKoncowe.add(miejscePrzejsciaDrogi3);
            punktyKoncowe.add(miejscePrzejsciaDrogi4);
            punktyKoncowe.add(miejscePrzejsciaDrogi5);
            punktyKoncowe.add(miejscePrzejsciaDrogi6);
            punktyKoncowe.add(miejscePrzejsciaDrogi7);

            int[] wartosci_X = new int[7];
            int[] wartosci_Y = new int[7];
            double[][] odleglosci = new double[7][7];
            List<double[][]> punkty = new ArrayList<>();
            List<double[][]> punktyPomocnicze = new ArrayList<>();

            wartosci_X[0] = miejscePrzejsciaDrogi1[0];
            wartosci_X[1] = miejscePrzejsciaDrogi2[0];
            wartosci_X[2] = miejscePrzejsciaDrogi3[0];
            wartosci_X[3] = miejscePrzejsciaDrogi4[0];
            wartosci_X[4] = miejscePrzejsciaDrogi5[0];
            wartosci_X[5] = miejscePrzejsciaDrogi6[0];
            wartosci_X[6] = miejscePrzejsciaDrogi7[0];

            wartosci_Y[0] = miejscePrzejsciaDrogi1[1];
            wartosci_Y[1] = miejscePrzejsciaDrogi2[1];
            wartosci_Y[2] = miejscePrzejsciaDrogi3[1];
            wartosci_Y[3] = miejscePrzejsciaDrogi4[1];
            wartosci_Y[4] = miejscePrzejsciaDrogi5[1];
            wartosci_Y[5] = miejscePrzejsciaDrogi6[1];
            wartosci_Y[6] = miejscePrzejsciaDrogi7[1];


            for(int i = 0; i<7 ;i++){
                for(int j = 0; j<7 ;j++){
                    odleglosci[i][j] = Math.sqrt(Math.pow(wartosci_X[i] - wartosci_X[j], 2) +
                            Math.pow(wartosci_Y[i] - wartosci_Y[j], 2));
                }
                punkty.add(new double[][]{odleglosci[i]});
                punktyPomocnicze.add(new double[][]{odleglosci[i]});
            }

            int nastepnyPunkt = 0;
            List<Integer> niepowatrzalne = new ArrayList<>();

            for(int j = 0; j < 7; j++){
                niepowatrzalne.add(nastepnyPunkt);
                kolejnoscPoruszaniaSie.add(nastepnyPunkt);

                int tymaczasowyPunkt = 0;
                double[][] aktualnyPunkt = punkty.get(nastepnyPunkt);
                punktyPomocnicze.remove(aktualnyPunkt);

                double min = 1000000000000000.0; //liczba wieksza niż możliwa do ustawienia
                for(int i = 0; i<7;i++){
                    if(aktualnyPunkt[0][i] > 0.0){
                        if(aktualnyPunkt[0][i] < min && !niepowatrzalne.contains(i)){
                            min = aktualnyPunkt[0][i];
                            tymaczasowyPunkt = i;
                        }
                    }
                }

                nastepnyPunkt = tymaczasowyPunkt;
            }

            System.out.println("Kolejnosc: ");
            for(int i = 0; i < kolejnoscPoruszaniaSie.size(); i++){
                System.out.println(kolejnoscPoruszaniaSie.get(i));
            }
        }

        // generowanie drogi - ustanawianie sciezki

        if(iloscDrog == 3 || iloscDrog == 5 || iloscDrog ==7){
            //kolejnoscPoruszaniaSie
            //punktyKoncowe

            List<int[]> punktyWodpowiedniejKolejnosci = new ArrayList<>();
            int[] aktualnalnaPozycjaDrogi = new int[2];
            int[] punktDocelowy = new int[2];

            for(int i = 0; i < kolejnoscPoruszaniaSie.size(); i++){
                punktyWodpowiedniejKolejnosci.add(punktyKoncowe.get(kolejnoscPoruszaniaSie.get(i)));
            }

            /*
            1 - lewo
            2 - prawo
            3 - gora
            4 - dol
            */

            try{
                for(int i = 0; i < punktyWodpowiedniejKolejnosci.size(); i++){
                    aktualnalnaPozycjaDrogi = punktyWodpowiedniejKolejnosci.get(i);
                    punktDocelowy = punktyWodpowiedniejKolejnosci.get(i+1);

                    while(true){
                        int kierunek_X = aktualnalnaPozycjaDrogi[0] - punktDocelowy[0];
                        int kierunek_Y = aktualnalnaPozycjaDrogi[1] - punktDocelowy[1];

                        try {
                            grid[aktualnalnaPozycjaDrogi[0]][aktualnalnaPozycjaDrogi[1]] = 6;
                        } catch (Exception ex) {

                        }

                        int kierunekRuchu = 0;

                        if(kierunek_X == 0 && kierunek_Y == 0){
                            break;
                        }

                        if(kierunek_X > 0 && kierunek_Y > 0){
                            int[] mozliwyKierunek = new int[]{1,3};
                            Random Rand = new Random();
                            kierunekRuchu = mozliwyKierunek[Rand.nextInt(0, mozliwyKierunek.length)];
                        }
                        if(kierunek_X < 0 && kierunek_Y > 0){
                            int[] mozliwyKierunek = new int[]{2,3};
                            Random Rand = new Random();
                            kierunekRuchu = mozliwyKierunek[Rand.nextInt(0, mozliwyKierunek.length)];
                        }
                        if(kierunek_X > 0 && kierunek_Y < 0){
                            int[] mozliwyKierunek = new int[]{1,4};
                            Random Rand = new Random();
                            kierunekRuchu = mozliwyKierunek[Rand.nextInt(0, mozliwyKierunek.length)];
                        }
                        if(kierunek_X < 0 && kierunek_Y < 0){
                            int[] mozliwyKierunek = new int[]{2,4};
                            Random Rand = new Random();
                            kierunekRuchu = mozliwyKierunek[Rand.nextInt(0, mozliwyKierunek.length)];
                        }
                        if(kierunek_X == 0 && kierunek_Y < 0){
                            int[] mozliwyKierunek = new int[]{1,2,4};
                            Random Rand = new Random();
                            kierunekRuchu = mozliwyKierunek[Rand.nextInt(0, mozliwyKierunek.length)];
                        }
                        if(kierunek_X == 0 && kierunek_Y > 0){
                            int[] mozliwyKierunek = new int[]{1,2,3};
                            Random Rand = new Random();
                            kierunekRuchu = mozliwyKierunek[Rand.nextInt(0, mozliwyKierunek.length)];
                        }
                        if(kierunek_X < 0 && kierunek_Y == 0){
                            int[] mozliwyKierunek = new int[]{2,3,4};
                            Random Rand = new Random();
                            kierunekRuchu = mozliwyKierunek[Rand.nextInt(0, mozliwyKierunek.length)];
                        }
                        if(kierunek_X > 0 && kierunek_Y == 0){
                            int[] mozliwyKierunek = new int[]{1,3,4};
                            Random Rand = new Random();
                            kierunekRuchu = mozliwyKierunek[Rand.nextInt(0, mozliwyKierunek.length)];
                        }


                        switch(kierunekRuchu){
                            case 1:
                                aktualnalnaPozycjaDrogi[0] -= 1;
                                break;
                            case 2:
                                aktualnalnaPozycjaDrogi[0] += 1;
                                break;
                            case 3:
                                aktualnalnaPozycjaDrogi[1] -= 1;
                                break;
                            case 4:
                                aktualnalnaPozycjaDrogi[1] += 1;
                                break;
                        }
                    }
                }
            }
            catch (Exception ex){
            }


        }

        // Generowanie pliku tekstowego mapy
        try{
            File f = new File("mapPrep.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));

            for (int[] pole:grid) {
                for(int i = 0; i < wielkoscMapy; i++){
                    writer.write(String.valueOf(pole[i]));
                    //writer.write(" ");
                }
                writer.write("\n");
            }
            writer.close();
        }

        catch (Exception ex){
            ex.printStackTrace();
        }
        try{
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", "python pngMapGenerator.py");
            Process process = processBuilder.start();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        System.exit(0);
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
        wioska.getSelectionModel().selectFirst();
        droga.getSelectionModel().selectFirst();


    }
}