-- ======================================
-- V2__insert_sample_data.sql
-- ======================================

-- 1. Thêm dữ liệu mẫu cho bảng role_entity
INSERT INTO role_entity (
    role_name,
    role_description,
    status,
    created_by
) VALUES (
    'Admin',
    'Đây là admin',
    'ACTIVE',
    'SYSTEM'
)
ON CONFLICT DO NOTHING;  -- tránh lỗi nếu chạy lại migration

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
) VALUES (
    'USER001',
    'Trương Chí Kiên',
    '2002-11-15',
    'Nam',
    '0968727900',
    '268/23 Lê Văn Việt, Tăng Nhơn Phú B, Quận 9, TP.HCM',
    'truongchikien2021@example.com',
    (SELECT id FROM role_entity WHERE role_name = 'Admin' LIMIT 1),  -- đảm bảo FK hợp lệ
    '$2a$10$j0xNytrAvZkRCwf2XPzChe9bH5LsEqdyGmxsYF7GvN4WMF.C9WyNu',
    'ACTIVE',
    'admin',
    'admin'
)
ON CONFLICT DO NOTHING;
