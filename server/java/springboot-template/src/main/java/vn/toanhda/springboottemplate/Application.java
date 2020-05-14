package vn.toanhda.springboottemplate;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Application implements DisposableBean, CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) {
    String pidFile = System.getProperty("pidfile");

    if (pidFile != null) {
      new File(pidFile).deleteOnExit();
    }
  }

  @Override
  public void destroy() {}
}
