server:
    port: ${servicePort}
spring:
    application:
        name: ${artifactId}
logging:
    level:
        root: INFO
        org.hibernate: INFO
        org.hibernate.type.descriptor.sql.BasicBinder: TRACE
        org.hibernate.type.descriptor.sql.BasicExtractor: TRACE
        ${packagePath}: ERROR
eureka:
    instance:
        prefer-ip-address: true
        instance-id: ${r"${spring.cloud.client.ipAddress}"}:${r"${server.port}"}
        health-check-url: /health
    client:
        healthcheck:
            enabled: true
        service-url:
            defaultZone: ${discovery}