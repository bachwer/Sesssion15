
Why the update does nothing

Method:
```java
@Query("UPDATE Order o SET o.status =  'CANCELLED' WHERE o.id = :orderId")
void cancelFraudulentOrder(@Param("orderId") Long orderId);
```

Problem
Spring Data JPA treats @Query as SELECT query by default

=> so this update query;
* Is NOt executed as an update;
* dose not modify database
* But also does not throw error


RootCause

```java

@Modifying

```

-> Without it, JPA thinks

"this is a read query"


Also missing transaction

Even if updates runs, without transaction;
* changes may not be commited


Part 2 - Correct implementation


```java

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("UPDATE Order o SET o.status = 'CANCELLED' WHERE o.id = :orderId")
    void cancelFraudulentOrder(@Param("orderId") Long orderId);
}

```

add Transaction (Very Important)
```java

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        orderRepository.cancelFraudulentOrder(orderId);
    }
}

```

 Edge Case Handling (IMPORTANT)
  Case 1: Invalid ID (negative or null)
```java

if (orderId == null || orderId <= 0) {
    throw new IllegalArgumentException("Invalid order ID");
}

```


Case 2: Order already DELIVERED

Check before update
```java

Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));

if (order.getStatus().equals("DELIVERED")) {
    throw new RuntimeException("Cannot cancel delivered order");
}

```



Final handling logic
```java

int updated = orderRepository.cancelFraudulentOrder(orderId);

if (updated == 0) {
    throw new RuntimeException("Cancel failed: invalid ID or already delivered");
}


```





