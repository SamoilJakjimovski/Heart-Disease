package mk.ukim.finki.wp.classifiers.config;


import mk.ukim.finki.wp.classifiers.service.ClassifierService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DataInitializer {


    private final ClassifierService classifierService;

    public DataInitializer(ClassifierService classifierService) {

        this.classifierService = classifierService;
    }

    @PostConstruct
    public void initData() {

        List<String> images = new ArrayList<>();
        Collections.addAll(images,"ClassificationReport.png","ConfusionMatrix.png","PrecisionRecallCurve.png","ROCCurve.png");
        classifierService.create("SVM", images, 74.63);
        classifierService.create("Naive Bayes", images, 88.29);
        classifierService.create("Logistic Regression", images,72.68);

    }
}
