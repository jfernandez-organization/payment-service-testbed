package com.example.payment.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import org.apache.commons.text.StringEscapeUtils;

@RestController
@RequestMapping("/api/payments/config")
public class YamlController {


    @PostMapping(consumes = "application/x-yaml")
    public ResponseEntity<String> load(@RequestBody String body) {
        Yaml yaml = new Yaml();
        Object parsed = yaml.load(body);
        return ResponseEntity.ok("loaded:" + (parsed == null ? "null" : parsed.getClass().getName()));
    }

    @PostMapping(value = "/xss", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> xss(@RequestBody String input) {
    String safeInput = StringEscapeUtils.escapeHtml4(input);
    String html = "<html><body>"
            + "<h1>Payment Configuration</h1>"
            + "<p>Configuration: " + safeInput + "</p>"
            + "</body></html>";

    return ResponseEntity.ok()
            .header("Content-Type", "text/html")
            .body(html);
    }

    @PostMapping(value = "/xss2", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> xss2(@RequestBody String input) {
    String safeInput = StringEscapeUtils.escapeHtml4(input);
    String html = "<html><body>"
            + "<h1>Payment Configuration</h1>"
            + "<p>Configuration: " + safeInput + "</p>"
            + "</body></html>";

    return ResponseEntity.ok()
            .header("Content-Type", "text/html")
            .body(html);
    }   


}
