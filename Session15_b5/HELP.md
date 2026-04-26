PHẦN A – THIẾT KẾ KIẾN TRÚC

⸻

🧩 1. ERD (Database Design)

✔️ 3 bảng chính:

1. users

* id
* username

⸻

2. vouchers

* id
* code (UNIQUE)
* discount
* quantity (số lượt còn lại)
* status (ACTIVE / INACTIVE)
* expiryDate
* version (quan trọng để lock)

⸻

3. user_voucher (bảng trung gian)

* id
* user_id
* voucher_id
* used_date

👉 Dùng để:

* ❌ chặn user dùng lại voucher
* ✔ tracking lịch sử

⸻

🔗 Quan hệ

* User ⟶ ManyToMany ⟶ Voucher (thông qua user_voucher)

⸻

⚠️ 2. TẠI SAO BỊ RACE CONDITION?

Kịch bản:

```java

quantity = 1

User A đọc → thấy còn 1
User B đọc → thấy còn 1

A update → OK
B update → vẫn OK ❌


```
👉 Vì:

* Không có lock
* 2 thread đọc cùng lúc

⸻

🧠 3. GIẢI PHÁP

🔥 CHỌN: PESSIMISTIC LOCKING (chuẩn nhất cho flash sale)

```java

@Lock(LockModeType.PESSIMISTIC_WRITE)

```

👉 Ý nghĩa:

* Khi 1 user lấy voucher → DB lock luôn row
* User khác phải chờ

👉 Đảm bảo:
✔ Không bao giờ vượt quá số lượng

⸻

❓ Tại sao KHÔNG dùng Optimistic?
