package mk.ukim.finki.wp.classifiers.web;

import mk.ukim.finki.wp.classifiers.model.Classifier;
import mk.ukim.finki.wp.classifiers.service.ClassifierService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ClassifierController {
    private final ClassifierService service;

    public ClassifierController(ClassifierService service) {
        this.service = service;
    }

    @GetMapping(value = {"/", "/classifiers"})
    public String showList(@RequestParam(required = false) String name, Model model) {
        List<Classifier> classifiers;
        if (name == null || name.isEmpty() ) {
            classifiers = this.service.listAll();
        } else {
            classifiers = this.service.findByName(name);
        }

        model.addAttribute("classifiers",classifiers);
        model.addAttribute("names", service.findAllNames());
        return "listClassifiers";
    }


    @GetMapping("/classifiers/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        Classifier clf = service.findById(id);
        model.addAttribute("classifier",clf);
        return "visualizeClassifier";
    }

    @PostMapping("/classifiers/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.service.delete(id);
        return "redirect:/classifiers";
    }

    @GetMapping("/dataset")
    public String showDataset(Model model)
    {
        List<String> imagePaths = new ArrayList<>();
        Collections.addAll(imagePaths, "Describe.png", "CountplotTarget.png", "CountplotSex.png","CrosstabAge.png","CrosstabSex.png","Scatter.png","CrosstabFBS.png","CrosstabChestPain.png");
        model.addAttribute("imagePaths",imagePaths);
        return "dataset";
    }

    @GetMapping("/compare")
    public String showComparison()
    {
        return "compareModels";
    }
    @GetMapping("/download-dataset")
    public ResponseEntity<InputStreamResource> downloadCSV() throws FileNotFoundException {
        String filePath = "src/main/resources/static/files/heart.csv";

        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.parseMediaType("application/csv"))
                .contentLength(file.length())
                .body(resource);
    }

}
