package com.vdab.rdcar.controllers;


import com.vdab.rdcar.domain.Contact;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/contactpage")
    public String showContactPage(Model model) {
    model.addAttribute("newContact" , new Contact());
        return "contact";
    }

    @PostMapping(value = "/sendEmail")
    public String sendEmail(@ModelAttribute Contact contact){
        contactService.sendMail(contact);
        return "redirect:/";
    }

}
