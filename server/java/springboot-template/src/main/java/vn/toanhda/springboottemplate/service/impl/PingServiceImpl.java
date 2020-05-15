package vn.toanhda.springboottemplate.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springboottemplate.PingRequest;
import springboottemplate.PingResponse;
import vn.toanhda.springboottemplate.service.PingService;

/** Created by ToanHDA at Apr 22, 2020 */
@Service
@Slf4j
public class PingServiceImpl implements PingService {
  private static final String SYSTEM_NAME = "Springboot template";

  @Override
  public PingResponse ping(PingRequest request) {
    System.out.println(
        "Received PingService, method: ping, request={}" + new Gson().toJson(request));
    return PingResponse.newBuilder()
        .setTimestamp(System.currentTimeMillis())
        .setSystemName(SYSTEM_NAME)
        .build();
  }
}
