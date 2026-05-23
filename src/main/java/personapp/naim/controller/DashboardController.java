package personapp.naim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import personapp.naim.model.Person;
import personapp.naim.service.PersonService;

@Controller
public class DashboardController {

    private final PersonService personService;

    public DashboardController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/dashboard")
   public String dashboard(Model model) {

    var people = personService.findAll();

    var total = people.size();

    var averageAge = people.stream()
        .map(Person::getAge)
        .mapToInt(age -> age == null ? 0 : age)
        .average()
        .orElse(0.0);

    var recentPerson = personService.findMostRecentPerson();

    model.addAttribute("totalPersons", total);
    model.addAttribute("averageAge", Math.round(averageAge));
    model.addAttribute("recentPersons", recentPerson);

    return "dashboard";
}
}
