package pl.bartek.reciepes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek.reciepes.model.CategoryModel;
import pl.bartek.reciepes.repository.category.CategoryRepository;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public IndexController(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/")
    public String index(){
        final Optional<CategoryModel> categoryOptional = categoryRepository.findByDescription("American");

        System.out.println("Category id is: " + categoryOptional.get().getId());
        return "index";
    }
}
