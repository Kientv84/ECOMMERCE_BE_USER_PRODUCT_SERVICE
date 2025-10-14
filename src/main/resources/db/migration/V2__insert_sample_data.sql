-- ======================================
-- V2__insert_sample_data.sql
-- ======================================

-- 1. Thêm dữ liệu mẫu cho bảng role_entity
INSERT INTO role_entity (
    role_name,
    role_description,
    status,
    created_by
)
VALUES
    ('ROLE_ADMIN', 'Quản trị viên hệ thống', 'ACTIVE', 'SYSTEM'),
    ('ROLE_USER', 'Người dùng thông thường', 'ACTIVE', 'SYSTEM')
ON CONFLICT DO NOTHING;

-- 2. Thêm dữ liệu mẫu cho bảng user_entity
INSERT INTO user_entity (
    user_code,
    user_name,
    user_birthday,
    user_gender,
    user_phone_number,
    user_address,
    user_email,
    role_id,
    user_password,
    status,
    created_by,
    updated_by
)
VALUES (
    'USER001',
    'Trương Chí Kiên',
    '2002-11-15',
    'Nam',
    '0968727900',
    '268/23 Lê Văn Việt, Tăng Nhơn Phú B, Quận 9, TP.HCM',
    'truongchikien2021@example.com',
    (SELECT id FROM role_entity WHERE role_name = 'ROLE_ADMIN' LIMIT 1),
    '$2a$10$j0xNytrAvZkRCwf2XPzChe9bH5LsEqdyGmxsYF7GvN4WMF.C9WyNu',
    'ACTIVE',
    'admin',
    'admin'
)
ON CONFLICT DO NOTHING;

-- 3. Thêm dữ liệu mẫu cho bảng product_entity
INSERT INTO product_entity (
    product_name,
    description,
    brand,
    category,
    sub_category,
    collection,
    base_price,
    discount_percent,
    origin,
    material,
    fit_type,
    care_instruction,
    status,
    thumbnail_url,
    rating_average,
    rating_count,
    created_by,
    updated_by
)
VALUES (
    'Apex Seamless T-Shirt',
    'Áo tập thể thao nam với thiết kế co giãn, thoáng khí.',
    'Gymshark',
    'Men',
    'T-shirt',
    'Apex',
    499000,
    10.0,
    'Vietnam',
    'Nylon, Polyester',
    'Slim Fit',
    'Machine wash cold',
    'ACTIVE',
    'https://cdn.gymshark.com/products/apex-seamless-tshirt.jpg',
    4.8,
    120,
    'SYSTEM',
    'SYSTEM'
)
ON CONFLICT DO NOTHING;
