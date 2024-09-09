package mk.ukim.finki.wp.classifiers.service;

import mk.ukim.finki.wp.classifiers.model.Classifier;

import java.util.List;

public interface ClassifierService {

    List<Classifier> listAll();

    Classifier findById(Long id);

    Classifier create(String name, List<String> imageUrls,Double acc);

    Classifier update(Long id, String name, List<String> imageUrls);

    Classifier delete(Long id);

    List<Classifier> findByName(String name);

    List<String> findAllNames();
}
