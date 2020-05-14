package vn.toanhda.springboottemplate.service;

import vn.toanhda.springboottemplate.PingRequest;
import vn.toanhda.springboottemplate.PingResponse;

/** Created by ToanHDA at Apr 22, 2020 */
public interface PingService {
  PingResponse ping(PingRequest request);
}
