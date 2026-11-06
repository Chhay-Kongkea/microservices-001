# Order Service -> Payment Service gRPC demo

This archive adds one synchronous gRPC call (`PayOrder`) while retaining the existing Order Service modules and code.

## What changed

- Added `istad-common/istad-common-grpc-contract` with the single shared `payment.proto`.
- Added a `PaymentServiceClient` output port and `PayOrderUseCase` to the existing Order Service application module.
- Added a gRPC client adapter in the existing Order Service client module.
- Added a minimal Payment Service with `payment-domain-core`, `payment-application-service`, `payment-service-grpc`, and `payment-service-main` modules.
- Added `POST /api/orders/pay` to Order Service for the demo.
- Existing Customer/Business HTTP clients, persistence, Liquibase and Consul configuration were not intentionally changed.

## Build

Run from the repository root (Windows: use `gradlew.bat`):

```bash
./gradlew :istad-common:istad-common-grpc-contract:generateProto
./gradlew :payment-service:payment-service-main:bootJar
./gradlew :order-service:order-service-main:bootJar
```

These commands use the root composite build, which includes both `istad-common` and `microservices` so the shared contract project is available to both services.

## Run

Start Payment Service first:

```bash
./gradlew :payment-service:payment-service-main:bootRun
```

Then start Order Service (its existing Consul and database dependencies still apply):

```bash
./gradlew :order-service:order-service-main:bootRun
```

Payment gRPC listens on `localhost:9091`. Order Service keeps its existing HTTP port `20000`.

## Test

```http
POST http://localhost:20000/api/orders/pay
Content-Type: application/json
```

```json
{
  "orderId": "ORD-001",
  "customerId": "CUS-001",
  "amount": "50.00"
}
```

The response contains a generated `paymentId`, the order ID, `successful: true`, and a demo message.

## Important

Payment is simulated in memory. This demo does not charge a real payment method, persist a payment, or update an existing Order aggregate/status. The shared `.proto` is stored only once in `istad-common` and is consumed by both services as a Gradle composite-build dependency.
