package main.java;

import java.io.IOException;

import main.java.utils.CSVMovieManager;
import main.java.utils.ExcepcionDynamicList;

/**
 * Simple app class to run the program
 */
public class App {

    /**
     * Just a main function tu run the App
     * @param args
     */
    public static void main(String[] args){

        String rutaCSVEntrada = "/home/tony/workspace/csv-movie-loader/Movie.csv";
        String rutaCSVSalida = "/home/tony/workspace/csv-movie-loader/";
        try {
            CSVMovieManager csvMovieManager = new CSVMovieManager(rutaCSVEntrada);
            csvMovieManager.loadDataToArray(2000);

            // Uncomment to test the LL methods
            //csvMovieManager.testListMethods();

            csvMovieManager.writeToCSVFromArray(rutaCSVSalida, "MovieFinal.csv");
            csvMovieManager.writeToCSVFromArray(rutaCSVSalida, "MovieFinalQuickSort.csv");
            csvMovieManager.writeToCSVFromArray(rutaCSVSalida, "MovieFinalHeapSort.csv");



        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExcepcionDynamicList e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
