package com.microservices.workflow;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class WorkflowOrchestrationApplication {

  public static void main(String[] args) {
    Quarkus.run(args);
  }
}
