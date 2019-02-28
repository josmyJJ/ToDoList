package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

  @Autowired
  ListRepository listRepository;

  @RequestMapping("/")
  public String listIems(Model model){
    model.addAttribute("lists", listRepository.findAll());
    return "list";
  }

  @GetMapping("/add")
  public String listForm(Model model){
    model.addAttribute("list", new List());
    return "listform";
  }

  @PostMapping("/process")
  public String processForm(@Valid List list, BindingResult result){
    if (result.hasErrors()){
      return "listform";
    }
    listRepository.save(list);
    return "redirect:/";
  }

  @RequestMapping("/detail/{id}")
  public String showItem(@PathVariable("id") long id, Model model){
    model.addAttribute("list", listRepository.findById(id).get());
    return "show";
  }

  @RequestMapping("/update/{id}")
  public String updateItem(@PathVariable("id") long id, Model model){
    model.addAttribute("list", listRepository.findById(id).get());
    return "listform";
  }

  @RequestMapping("/delete/{id}")
  public String delItem(@PathVariable("id") long id){
    listRepository.deleteById(id);
    return "redirect:/";
  }
}
