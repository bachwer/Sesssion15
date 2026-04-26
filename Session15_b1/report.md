Phần 1 - Phân Tích Sai sót

Hàm Hiện tại

```java

List<Product> findByCategoryAndPriceLessThan(String category, Double maxPrice);

```


Issue 1: Using LessThan instead of LessThanEqual

* LessThan means: < maxPrice
* Business requirement: price ≤ maxPrice


=> Product priced exactly equal to the max price are excluded


Example test Case:


|id|category|price|stockQuantity
|1|phone|10,000,000|5


call:

```java

findByCategoryAndPriceLessThan("Phone", 10000000.0);

```


=> product is not returned 
=> But is should be included


Issue 2: Missing stock condition (StockQuantity > 0)
the current method dose not filter stock

-> It return out-of-stock products, which violates requirements

Example test Case:

|id|category|price|stockQuantity|
|2|Phone|9000000|0|

-> this product is returned
-> but it should be excluded

Summary of Problems
Problem | Cause
Missing products at exact max price | Using less Than
Showing out-of-stock product | Missing stock condition



Part2 _ Correct Method Naming

```java

List<Product> findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
        String category, Double maxPrice, Integer stockQuantity
);

```


How to call it

```java

productRepository.findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
    "Phone",
    10000000.0,
    0
);



```


Meaning:

* price ≤ 10,000,000
* stockQuantity > 0

Final Conclusion

To match the business rules:

* Replace LessThan → LessThanEqual
* Add StockQuantityGreaterThan

If you want, I can also show:

* JPQL (@Query) version
* Full Controller + Service implementation