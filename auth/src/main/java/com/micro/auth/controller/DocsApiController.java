package com.micro.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.MessageFormat;

@Controller
public class DocsApiController {
    @GetMapping("/docs-api/")
    public String docIndex() {
        return docPage("index");
    }

    @GetMapping("/docs-api")
    public String docIndex2() {
        return "redirect:/docs-api/";
    }

    @GetMapping("/docs-api/{page}")
    public String docPage(@PathVariable String page) {
        return MessageFormat.format("/asciidoc/{0}.html", page);
    }
}
