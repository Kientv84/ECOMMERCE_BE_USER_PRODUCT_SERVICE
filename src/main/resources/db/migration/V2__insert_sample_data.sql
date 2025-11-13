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

-- 3. Thêm dữ liệu mẫu cho bảng brand_entity
INSERT INTO brand_entity (
    brand_name,
    brand_code,
    origin,
    description,
    thumbnail_url,
    status,
    created_by,
    updated_by
)
VALUES
    ('Gymshark', 'GYMSHARK', 'UK', 'Thương hiệu thể thao nổi tiếng', 'https://cdn.gymshark.com/logo.jpg', 'ACTIVE', 'SYSTEM', 'SYSTEM'),
    ('Nike', 'NIKE', 'USA', 'Thương hiệu thể thao nổi tiếng toàn cầu', 'https://cdn.nike.com/logo.jpg', 'ACTIVE', 'SYSTEM', 'SYSTEM')
ON CONFLICT DO NOTHING;

-- 4. Thêm dữ liệu mẫu cho bảng category_entity
INSERT INTO category_entity (
    category_name,
    category_code,
    description,
    thumbnail_url,
    status,
    created_by,
    updated_by
)
VALUES
    ('Men', 'MEN', 'Thời trang nam', 'https://cdn.example.com/men.jpg', 'ACTIVE', 'SYSTEM', 'SYSTEM'),
    ('Women', 'WOMEN', 'Thời trang nữ', 'https://cdn.example.com/women.jpg', 'ACTIVE', 'SYSTEM', 'SYSTEM'),
     ('Accessory', 'ACCESSORY', 'Phụ Kiện', 'https://cdn.example.com/accessory.jpg', 'ACTIVE', 'SYSTEM', 'SYSTEM')
ON CONFLICT DO NOTHING;

-- 5. Thêm dữ liệu mẫu cho bảng sub_category_entity
INSERT INTO sub_category_entity (
    sub_category_name,
    sub_category_code,
    description,
    thumbnail_url,
    status,
    category_id,
    created_by,
    updated_by
)
VALUES
    ('T-shirt', 'T_SHIRT', 'Áo thun nam', 'https://cdn.example.com/tshirt.jpg', 'ACTIVE',
     (SELECT id FROM category_entity WHERE category_code='MEN' LIMIT 1), 'SYSTEM', 'SYSTEM'),
    ('Shorts', 'SHORTS', 'Quần short nam', 'https://cdn.example.com/shorts.jpg', 'ACTIVE',
     (SELECT id FROM category_entity WHERE category_code='MEN' LIMIT 1), 'SYSTEM', 'SYSTEM')
ON CONFLICT DO NOTHING;

-- 6. Thêm dữ liệu mẫu cho bảng collection_entity
INSERT INTO collection_entity (
    collection_name,
    collection_code,
    description,
    status,
    created_by,
    updated_by
)
VALUES
    ('Apex', 'APEX', 'Bộ sưu tập Apex', 'ACTIVE', 'SYSTEM', 'SYSTEM'),
    ('Vital', 'VITAL', 'Bộ sưu tập Vital', 'ACTIVE', 'SYSTEM', 'SYSTEM')
ON CONFLICT DO NOTHING;

-- 7. Thêm dữ liệu mẫu cho bảng material_entity
INSERT INTO material_entity (
    material_name,
    material_code,
    description,
    status,
    created_by,
    updated_by
)
VALUES
    ('Polyester', 'POLY', 'Vải Polyester', 'ACTIVE', 'SYSTEM', 'SYSTEM'),
    ('Nylon', 'NYLON', 'Vải Nylon', 'ACTIVE', 'SYSTEM', 'SYSTEM')
ON CONFLICT DO NOTHING;

-- 8. Thêm dữ liệu mẫu cho bảng product_entity
INSERT INTO product_entity (
    product_name,
    product_code,
    description,
    brand_id,
    category_id,
    sub_category_id,
    collection_id,
    material_id,
    base_price,
    discount_percent,
    origin,
    fit_type,
    care_instruction,
    count_in_stock,
    status,
    thumbnail_url,
    rating_average,
    rating_count,
    created_by,
    updated_by
)
VALUES (
    'Apex Seamless T-Shirt',
    'APEX-TSHIRT-001',
    'Áo tập thể thao nam với thiết kế co giãn, thoáng khí.',
    (SELECT id FROM brand_entity WHERE brand_code='GYMSHARK' LIMIT 1),
    (SELECT id FROM category_entity WHERE category_code='MEN' LIMIT 1),
    (SELECT id FROM sub_category_entity WHERE sub_category_code='T_SHIRT' LIMIT 1),
    (SELECT id FROM collection_entity WHERE collection_code='APEX' LIMIT 1),
    (SELECT id FROM material_entity WHERE material_code='NYLON' LIMIT 1),
    499000,
    10.0,
    'Vietnam',
    'Slim Fit',
    'Machine wash cold',
    50,
    'ACTIVE',
    'https://cdn.gymshark.com/products/apex-seamless-tshirt.jpg',
    4.8,
    120,
    'SYSTEM',
    'SYSTEM'
)
ON CONFLICT DO NOTHING;

