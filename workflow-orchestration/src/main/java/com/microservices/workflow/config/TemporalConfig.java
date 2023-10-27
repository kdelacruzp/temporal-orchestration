package com.microservices.workflow.config;

import com.microservices.workflow.proxy.OrderApi;
import com.microservices.workflow.proxy.ProductApi;
import com.microservices.workflow.service.activity.ActivityService;
import com.microservices.workflow.service.activity.ActivityServiceImpl;
import com.microservices.workflow.service.workflow.WorkflowServiceImpl;
import io.quarkus.arc.DefaultBean;
import io.quarkus.runtime.Startup;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Dependent
@Startup
//@ApplicationScoped
@RequiredArgsConstructor
public class TemporalConfig {

    //private final ProductFeign productFeign;
    @RestClient
    private OrderApi orderApi;

    @RestClient
    private ProductApi productApi;

    @Inject
    @ConfigProperty(name = "temporal.hostname", defaultValue = "127.0.0.1:7233")
    private String HOSTNAME;

    @Inject
    @ConfigProperty(name = "temporal.hostname", defaultValue = "default")
    private String NAMESPACE;

    public static String QUEUE_NAME = "WORKFLOW_LOGISTIC";

    @Produces
    @DefaultBean
    public WorkerFactoryOptions workerFactoryOptions() {
        return WorkerFactoryOptions.newBuilder()
            .setMaxWorkflowThreadCount(90)
            .setWorkflowCacheSize(500)
            .build();
    }

    /**
     * workerOptions.
     *
     * @return WorkerOptions workerOptions
     */
    @Produces
    @DefaultBean
    public WorkerOptions workerOptions() {
        return WorkerOptions.newBuilder()
            .setMaxConcurrentWorkflowTaskExecutionSize(5000)
            .setMaxConcurrentWorkflowTaskPollers(5000)
            .setMaxConcurrentActivityExecutionSize(90)
            .build();
    }

    /**
     * workflowImplementationOptions.
     *
     * @return WorkflowImplementationOptions workflowImplementationOptions
     */
    @Produces
    @DefaultBean
    public WorkflowImplementationOptions workflowImplementationOptions() {
        return WorkflowImplementationOptions
            .newBuilder()
            .setFailWorkflowExceptionTypes(
                Exception.class).build();
    }

    //@Bean
    @Produces
    @ApplicationScoped
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder()
                .setTarget(HOSTNAME).build());
    }

    //@Bean
    @Produces
    @ApplicationScoped
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs, WorkflowClientOptions.newBuilder()
                .setNamespace(NAMESPACE).build());
    }

    //@Bean
    @Produces
    @ApplicationScoped
    public WorkerFactory workerFactory(WorkflowClient workflowClient,
                                       WorkflowImplementationOptions workflowImplementationOptions,
                                       WorkerOptions workerOptions) {
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        this.configureWorker(factory, workflowImplementationOptions, workerOptions);
        return factory;
    }

    //@Bean
    @Produces
    @ApplicationScoped
    public ActivityService signUpActivity(){
        return new ActivityServiceImpl(orderApi, productApi);
    }

    private void configureWorker(WorkerFactory workerFactory,
                                 WorkflowImplementationOptions workflowImplementationOptions,
                                 WorkerOptions workerOptions) {
        Worker worker = workerFactory.newWorker(QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(workflowImplementationOptions, WorkflowServiceImpl.class);
        worker.registerActivitiesImplementations(signUpActivity());
    }

}
