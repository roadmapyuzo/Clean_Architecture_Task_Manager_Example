# Introduction 

This project was created with the purpose of studying and applying Clean Architecture principles in a real backend scenario.

The goal was to design a system with clear separation of concerns, explicit dependency boundaries, and infrastructure isolation, enabling high testability, maintainability, and evolvability.

Beyond architectural exploration, the project also focuses on practical engineering aspects such as asynchronous messaging, observability, automated workflows, and developer experience improvements.

## Engineering Highlights

### Architecture
- Hexagonal layered separation (domain, application, infra, interface)
- Constructor-based dependency injection

### Reliability
- Global exception handler
- In-memory repository for endpoint testing

### Observability
- Metrics exposed via Prometheus
- Grafana dashboard for runtime monitoring

### Messaging
- Asynchronous communication via RabbitMQ
- Producer/consumer implementation

### DevEx
- CI workflow with automated validation
- Automatic PR generation on successful pipeline