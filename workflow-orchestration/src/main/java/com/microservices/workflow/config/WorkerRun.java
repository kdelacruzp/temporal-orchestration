package com.microservices.workflow.config;

import io.quarkus.runtime.StartupEvent;
import io.temporal.worker.WorkerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class WorkerRun {
  private final WorkerFactory workerFactory;

  void onStart(@Observes StartupEvent ev) {
    log.info("Starting WorkerFactory Connection");
    this.workerFactory.start();
  }
}
