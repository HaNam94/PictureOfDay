package org.example.picture_of_today.controller;

import org.example.picture_of_today.model.NasaPicture;
import org.example.picture_of_today.service.NasaPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class NasaPictureController {
    @Autowired
    private NasaPictureService nasaPictureService;

    @GetMapping("/picture")
    public String getListPage(Model model){
        model.addAttribute("eList", nasaPictureService.findAll());
        model.addAttribute("el", new NasaPicture());
        return "/list";
    }

    @GetMapping("/picture/create")
    public String getCreatePage(Model model){
        model.addAttribute("el", new NasaPicture());
        return "/list";
    }

    @PostMapping("/picture/create")
    public String getCreateDone(@ModelAttribute NasaPicture el, Model model){
        nasaPictureService.save(el);
        return "redirect:/picture";
    }
}
