package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repository.MessageRepository;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Log4j
@Controller
public class MainController {

    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public MainController(MessageRepository repository) {
        this.messageRepository = repository;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> map) {
        return "greeting";
    }

    @GetMapping("/main")//required = false не обязательное поле
    public String main(@RequestParam(required = false, defaultValue = "")String filter, Model model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> map,
            @RequestParam ("file")MultipartFile file
    ) {
        Message message = new Message(text, tag, user);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            System.out.println(uploadPath);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            message.setFilename(resultFileName);
            try {
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        map.put("messages", messages);
        return "main";
    }

}
