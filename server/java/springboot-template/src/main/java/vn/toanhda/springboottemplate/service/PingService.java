package vn.toanhda.springboottemplate.service;

import springboottemplate.PingRequest;
import springboottemplate.PingResponse;

/** Created by ToanHDA at Apr 22, 2020 */
public interface PingService {
  PingResponse ping(PingRequest request);
}
