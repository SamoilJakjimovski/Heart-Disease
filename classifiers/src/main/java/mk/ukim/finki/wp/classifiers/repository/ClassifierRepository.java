package mk.ukim.finki.wp.classifiers.repository;

import mk.ukim.finki.wp.classifiers.model.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {
    Classifier findByName(String name);
}
