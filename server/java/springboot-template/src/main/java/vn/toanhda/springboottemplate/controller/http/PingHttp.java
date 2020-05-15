package vn.toanhda.springboottemplate.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboottemplate.PingRequest;
import vn.toanhda.springboottemplate.service.impl.PingServiceImpl;

/** Created by ToanHDA at Apr 22, 2020 */
@RestController
@RequestMapping("/api/ping")
@Slf4j
public class PingHttp {
  @Autowired private PingServiceImpl pingService;

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> ping(@RequestBody(required = false) PingRequest request) {
    return ResponseEntity.ok(pingService.ping(request));
  }
}
