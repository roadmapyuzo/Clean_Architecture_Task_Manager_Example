# Introduction

This project was created with the purpose of studying and applying Clean Architecture principles in a real backend scenario.

The goal was to design a system with clear separation of concerns, explicit dependency boundaries, and infrastructure isolation, enabling high testability, maintainability, and evolvability.

Beyond architectural exploration, the project also focuses on practical engineering aspects such as asynchronous messaging, observability, automated workflows, and developer experience improvements.

---

## Engineering Highlights

### Architecture
- Hexagonal layered separation (domain, application, infra, interface)
- Constructor-based dependency injection
- Explicit domain event propagation
- Infrastructure isolation through ports and adapters

---

### Domain Events & Consistency
- Aggregates raise Domain Events
- EventDispatcher orchestrates in-process event propagation
- DomainEventHandlers convert Domain Events into Integration Events
- Outbox Pattern implementation for reliable event publishing
- EventBus with scheduler responsible for polling and publishing unpublished messages

#### Event Flow Overview

```
Aggregate
   ↓ (raise domain event)
EventDispatcher
   ↓
DomainEventHandler
   ↓ (map to integration event)
OutboxAdapter
   ↓ (persist outbox message)
Outbox Storage
   ↓ (scheduler polling)
EventBus
   ↓
Message Broker (RabbitMQ)
```

This guarantees:
- Transactional consistency between database state and message publication
- At-least-once delivery
- Decoupled integration boundaries
- Resilience against broker downtime

---

### Reliability
- Global exception handler
- In-memory repository for endpoint testing
- Outbox ensures message durability before external publication

---

### Messaging
- Asynchronous communication via RabbitMQ
- Producer/consumer implementation
- Integration events published via EventBus
- Scheduled outbox processor

---

### Observability
- Metrics exposed via Prometheus
- Grafana dashboard for runtime monitoring

---

### DevEx
- CI workflow with automated validation
- Automatic PR generation on successful pipeline