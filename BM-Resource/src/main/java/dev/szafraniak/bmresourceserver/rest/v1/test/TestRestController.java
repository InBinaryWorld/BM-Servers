package dev.szafraniak.bmresourceserver.rest.v1.test;

import dev.szafraniak.bmresourceserver.config.SecurityContextUtils;
import dev.szafraniak.bmresourceserver.rest.v1.entity.TestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class TestRestController {

    @GetMapping(path = "/hello")
    public TestEntity getHello() {
        return new TestEntity(1,"hello_not_secured");
    }

    @GetMapping(path = "/api/v1/hello")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public TestEntity getSecured() {
        return new TestEntity(2,"hello_secured");
    }




}
