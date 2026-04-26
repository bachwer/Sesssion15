
1 Phân tích và thiết kế

1 input/ Output

input(Query Parameters from URL)


URL:
```text
/orders?page=1&size=10&status=DELIVERING&sortBy=createdDate&direction=desc
```
Tham số: page, size, status, sortBy, direction
Kiểu dữ liệu: int, String

Output( model send to view)
```java

model.addAttribute("orders", pageData.getContent());
model.addAttribute("currentPage", pageData.getNumber());
model.addAttribute("totalPages", pageData.getTotalPages());
model.addAttribute("status", status);
model.addAttribute("sortBy", sortBy);
model.addAttribute("direction", direction);

```

2: Gỉai pháp sử dụng String Data JPA

Bạn sẽ dùng:
- Page<T> -> Chứ dữ liệu + metadata
- Pageable -> Phân Trang
- Sort -> Sắp xếp


=> Core API:
```java

Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
Page<Order> pageData = orderRepository.findByUserIdAndStatus(...);

```

3. Thiết kế luồng xử lý

```text

Browser (URL)
   ↓
Controller (nhận param)
   ↓
Service (validate + xử lý bẫy)
   ↓
Repository (query DB)
   ↓
Service (trả Page)
   ↓
Controller (add Model)
   ↓
View (hiển thị + phân trang)

```

