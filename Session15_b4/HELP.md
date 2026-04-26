
Phần A - Phân tích & Đề xuất

1 Input / Output

Input ( từ controller)

```iava

categoryName (string)
discountPercentage(double)

```


Output(Service Tra ve)

String message

Ví dụ:

* "Cập nhật thành công 50000 sản phẩm"
*  "Không tìm thấy sản phẩm"
*  "Discount không hợp lệ"

2. ĐỀ XUẤT 2 GIẢI PHÁP
   Giải pháp 1: Load toàn bộ → update → saveAll

-> Cách làm

```java

List<Product> list = repo.findByCategoryAndStatus(...);

for(Product p : list){
    p.setPrice(newPrice);
}

repo.saveAll(list);

```

* Load 50.000 record vào RAM
* Tốn memory 💣
* Chậm (50.000 update SQL)


Giải pháp 2: UPDATE trực tiếp DB (JPQL)

👉 Cách làm

```java


@Modifying
@Query("""
UPDATE Product p
SET p.price = p.price - (p.price * :discount / 100)
WHERE p.category = :category
AND p.status = 'ACTIVE'
""")

```

Ưu điểm

* ❌ Không load entity
* ✔ Chạy 1 câu SQL duy nhất
* ✔ Rất nhanh


KẾT LUẬN

👉 Với 50.000 record → bắt buộc chọn: GIẢI PHÁP 2 (JPQL UPDATE)


LUỒNG XỬ LÝ

```text


1. Nhận category + discount
2. Check discount hợp lệ ❗
3. Check có sản phẩm không ❗
4. Thực hiện UPDATE (JPQL)
5. Trả kết quả

```