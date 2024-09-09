package mk.ukim.finki.wp.classifiers.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Classifier {

    public Classifier() {
    }

    public Classifier(String name, List<String> imagePaths,Double accuracy) {
        this.name = name;
        this.imagePaths = imagePaths;
        this.accuracy = accuracy;
    }

    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    private Double accuracy;


    @ElementCollection
    private List<String> imagePaths;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }


    public List<Classifier> toList()
    {
       List<Classifier> clfs = new ArrayList<>();
       clfs.add(this);
       return clfs;
    }



}
