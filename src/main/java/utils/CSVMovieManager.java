package main.java.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.Link;
import main.java.LinkList;
import main.java.entities.Movie;

/**
 * Class to load/write data from/a Movie.csv
 * 
 * @author tonycituk
 * @author FerMendezA
 */
public class CSVMovieManager {

    /**
     * Path to reach the CSV
     */
    private String pathToCSV;

    /**
     * Linked List to store the movies
     */
    private LinkList<Movie> moviesLL;
    private ArrayList<Movie> moviesAL;

    /**
     * 
     * @param pathToCSV path to the CSV file to read movies from
     */
    public CSVMovieManager(String pathToCSV) {
        this.pathToCSV = pathToCSV;
        this.moviesLL = new LinkList<Movie>();
        this.moviesAL = new ArrayList<Movie>();
    }

    /**
     * 
     * loads data from the CSV in Crescent order using the title as reference also
     * runs some LinkedList methods for homework purposes
     * 
     * @param lines total lines to read
     * @throws NumberFormatException
     * @throws IOException
     * @throws ExcepcionDynamicList
     */
    public void loadData(int lines) throws NumberFormatException, IOException, ExcepcionDynamicList {

        try (BufferedReader br = new BufferedReader(new FileReader(pathToCSV))) {
            String linea = "";

            int i = 0;
            br.readLine(); // saltar los nombres de columna
            while ((linea = br.readLine()) != null && i < lines) {
                String[] datos = linea.split(",");
                for (int j = 0; j < datos.length; j++) {
                    if (datos[j].equals("")) {
                        datos[j] = "0";
                    }
                }
                // Movie movie = new Movie(datos[1], Double.parseDouble(datos[7]));
                Movie movie = new Movie(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]),
                        Integer.parseInt(datos[7]),
                        Float.parseFloat(datos[9]));
                this.moviesLL.insertCrescent(movie);
                // usr.imprimirInfo(); //Eliminar después
                i++;
            }
            // System.out.println(moviesLL.getSize());
        }
    }

    public void loadDataToArray(int lines) throws NumberFormatException, IOException, ExcepcionDynamicList {

        try (BufferedReader br = new BufferedReader(new FileReader(pathToCSV))) {
            String linea = "";

            int i = 0;
            br.readLine(); // saltar los nombres de columna
            while ((linea = br.readLine()) != null && i < lines) {
                String[] datos = linea.split(",");
                for (int j = 0; j < datos.length; j++) {
                    if (datos[j].equals("")) {
                        datos[j] = "0";
                    }
                }
                // Movie movie = new Movie(datos[1], Double.parseDouble(datos[7]));
                Movie movie = new Movie(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]),
                        Integer.parseInt(datos[7]),
                        Float.parseFloat(datos[9]));
                this.moviesAL.add(movie);
                // usr.imprimirInfo(); //Eliminar después
                i++;
            }
            // System.out.println(moviesLL.getSize());
        }
    }

    public void testListMethods() throws ExcepcionDynamicList {
        moviesLL.displayList();
        moviesLL.deleteAt(5);
        moviesLL.deleteFirst();
        moviesLL.deleteLast();
        moviesLL.insertCrescent(moviesLL.first().getDato());
        moviesLL.deleteFirst();
        // moviesLL.last();
        moviesLL.deleteWhere(moviesLL.first());
        moviesLL.replaceAtWith(2, new Link<Movie>(new Movie(9999, "Sponge Bob Squarepants", 180, 120000, 10.0f)));

        String searchMovie = "Spectre";
        System.out.println("Searching index of " + searchMovie + "...");
        if (moviesLL.buscar(searchMovie) != -1) {
            System.out.println(searchMovie + " found at: " + moviesLL.buscar(searchMovie));
        } else {
            System.out.println(searchMovie + " was not found.");
        }
        moviesLL.insertAfter(moviesLL.first().getDato(),
                new Movie(9998, "En busca de la felicidad", 180, 12324, 10.0f));
        moviesLL.insertBefore(moviesLL.last().getDato(), new Movie(9997, "Masacre en Texas", 180, 654345, 17.0f));

        moviesLL.displayList();

    }

    /**
     * Writes the movie LL to a CSV
     * 
     * @param path Path to store the file at
     * @throws ExcepcionDynamicList
     * @throws FileNotFoundException
     */
    public void writeToCSV(String path) throws ExcepcionDynamicList, FileNotFoundException {
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[] { "movie_id", "movie_title", "movie_duration", "movie_budget", "movie_rate" });

        while (this.moviesLL.getSize() > 0) {
            Movie xMovie = this.moviesLL.deleteFirst().getDato();
            dataLines.add(new String[] { String.valueOf(xMovie.getId()), xMovie.getTitle(),
                    String.valueOf(xMovie.getDuration()),
                    String.valueOf(xMovie.getBudget()), String.valueOf(xMovie.getRate()) });
        }

        File csvOutputFile = new File(path);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    /**
     * Writes the movie LL to a CSV from an array
     * 
     * @param path Path to store the file at
     * @throws ExcepcionDynamicList
     * @throws FileNotFoundException
     */
    public void writeToCSVFromArray(String path) throws ExcepcionDynamicList, FileNotFoundException {
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[] { "movie_id", "movie_title", "movie_duration", "movie_budget", "movie_rate" });

        for (Movie xMovie : moviesAL) {
            dataLines.add(new String[] { String.valueOf(xMovie.getId()), xMovie.getTitle(),
                    String.valueOf(xMovie.getDuration()),
                    String.valueOf(xMovie.getBudget()), String.valueOf(xMovie.getRate()) });
        }

        File csvOutputFile = new File(path);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    /**
     * Helper to convert a String array to a comma separated string.
     * 
     * @param data
     * @return Comma separated string.
     */
    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }

}
