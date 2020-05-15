package vn.toanhda.springboottemplate.controller.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import springboottemplate.PingRequest;
import springboottemplate.PingResponse;
import springboottemplate.PingServiceGrpc;
import vn.toanhda.springboottemplate.service.impl.PingServiceImpl;

/** Created by ToanHDA at Apr 22, 2020 */
@Slf4j
@GRpcService
public class PingRpc extends PingServiceGrpc.PingServiceImplBase {
  @Autowired private PingServiceImpl pingService;

  @Override
  public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
    responseObserver.onNext(pingService.ping(request));
    responseObserver.onCompleted();
  }
}
