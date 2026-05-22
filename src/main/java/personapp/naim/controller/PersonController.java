package personapp.naim.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import personapp.naim.model.Person;
import personapp.naim.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "persons";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes attributes) {
        return personService.findById(id)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "person-form";
                })
                .orElseGet(() -> {
                    attributes.addFlashAttribute("errorMessage", "Person not found.");
                    return "redirect:/persons";
                });
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "person-form";
        }
        personService.save(person);
        return "redirect:/persons";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        personService.deleteById(id);
        return "redirect:/persons";
    }
}
