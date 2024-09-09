package mk.ukim.finki.wp.classifiers.service.impl;

import mk.ukim.finki.wp.classifiers.model.exceptions.InvalidClassifierIdException;
import mk.ukim.finki.wp.classifiers.model.Classifier;
import mk.ukim.finki.wp.classifiers.repository.ClassifierRepository;
import mk.ukim.finki.wp.classifiers.service.ClassifierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassifierServiceImpl implements ClassifierService {

    private final ClassifierRepository repository;

    public ClassifierServiceImpl(ClassifierRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Classifier> listAll() {
        return repository.findAll();
    }

    @Override
    public Classifier findById(Long id) {
        return repository.findById(id).orElseThrow(InvalidClassifierIdException::new);
    }

    @Override
    public Classifier create(String name, List<String> imageUrls, Double acc) {
        return repository.save(new Classifier(name, imageUrls,acc));
    }

    @Override
    public Classifier update(Long id, String name, List<String> imageUrls) {
        Classifier clf = this.findById(id);
        clf.setName(name);
        clf.setImagePaths(imageUrls);
        return repository.save(clf);
    }

    @Override
    public Classifier delete(Long id) {
        Classifier clf = this.findById(id);
        repository.deleteById(id);
        return clf;
    }

    @Override
    public List<Classifier> findByName(String name) {
        if (name == null)
        {
            return repository.findAll();
        }

        return repository.findByName(name).toList();
    }

    @Override
    public List<String> findAllNames() {
        return this.listAll().stream().map(Classifier::getName).collect(Collectors.toList());
    }
}
