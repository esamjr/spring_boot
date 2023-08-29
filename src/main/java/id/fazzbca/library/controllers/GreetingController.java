package id.fazzbca.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class GreetingController {
  @GetMapping
  // localhost:8080/hello
  public String greeting() {
    return "Hello World!";
  }

  @GetMapping("/2")
  // request params
  // localhost:8080/hello/2?name=value
  public String greeting(@RequestParam String name) {
    return "Hello " + name + "! dari request param";
  }

  @GetMapping("/3/{name}")
  // path variable
  // localhost:8080/hello/3/value
  public String greeting2(@PathVariable String name) {
    return "Hello " + name + "! dari path variable";
  }

  // request body
  @PostMapping("/4")
  public String greeting3(@RequestBody String name) {
    return "Hello " + name + "! dari request body";
  }
}
