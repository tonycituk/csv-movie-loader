package main.java.entities;

/**
 * Class to store the movie
 * 
 * @author Tonymartin15
 */
public class Movie {

    private int id;
    private String title;
    private int duration;
    private int budget;
    private float rate;

    public Movie(int id, String title, int duration, int budget, float rate) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.budget = budget;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getOrder() {
        return duration;
    }

    public String toString() {
        return this.title;
    }

}
