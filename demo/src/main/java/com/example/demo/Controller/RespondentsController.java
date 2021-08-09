package com.example.demo.Controller;

import com.example.demo.Entities.Respondents;
import com.example.demo.Repos.RespondentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/respondents")
public class RespondentsController {
    private final RespondentsRepository respondentsRepository;

    @Autowired
    public RespondentsController(RespondentsRepository respondentsRepository) {
        this.respondentsRepository = respondentsRepository;
    }

    @GetMapping("/findAll")
    public List<Respondents> getAllRespondents() {
        List<Respondents> respondents = respondentsRepository.findAll();
        return respondents;
    }
    @GetMapping("/findById/{id}")
    public String findRespondentById(@PathVariable("id") Integer id) {
        Respondents respondent = respondentsRepository.findRespondentById(id);
        if(respondent != null) {
            String respo = respondent.toString();
            return respo;
        }
        else return "Respondent with id: " + id + " was not found!";
    }
    @GetMapping("/findByAge/{age}")
    public List<Respondents> findRespondentsByAge(@PathVariable("age") Integer age) {
        List<Respondents> respondents = respondentsRepository.findRespondentsByAge(age);
        return respondents;
    }
    @GetMapping("/findByAgeAndGender")
    public List<Respondents> findRespondentsByAgeAndGender(@RequestParam("age") Integer age, @RequestParam("gender") String gender) {
        List<Respondents> foundByParameters = respondentsRepository.findRespondentsByAgeAndGender(age, gender);
        return foundByParameters;
    }
    @PostMapping("/add")
    public Respondents addRespondent(@RequestBody Respondents respondent) {
        Respondents newRespondent = respondentsRepository.save(respondent);
        return newRespondent;
    }
    @PostMapping("/addList")
    public List<Respondents> addRespondents(@RequestBody List<Respondents> respondents) {
        return (List<Respondents>) respondentsRepository.saveAll(respondents);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteRespondent(@PathVariable("id") int id) {
        Respondents respondent = respondentsRepository.findRespondentById(id);
        if(respondent != null) {
            respondentsRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @PutMapping("/update")
    public Respondents updateRespondent(@RequestBody Respondents respondent) {
        return respondentsRepository.save(respondent);
    }
}
