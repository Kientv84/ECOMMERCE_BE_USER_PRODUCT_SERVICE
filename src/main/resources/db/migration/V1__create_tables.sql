-- ======================================
-- V1__create_tables.sql
-- ======================================

-- 1. Tạo bảng role_entity
CREATE TABLE IF NOT EXISTS role_entity (
    id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    role_description TEXT,
    status VARCHAR(50),
    created_by VARCHAR(500),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_by VARCHAR(500),
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tạo bảng user_entity
CREATE TABLE IF NOT EXISTS user_entity (
    id BIGSERIAL PRIMARY KEY,
    user_code VARCHAR(50) UNIQUE,
    user_name VARCHAR(100) NOT NULL,
    user_birthday DATE,
    user_gender VARCHAR(20),
    user_phone_number VARCHAR(50),
    user_address VARCHAR(255),
    user_email VARCHAR(100) UNIQUE,
    role_id BIGINT REFERENCES role_entity(id) ON DELETE SET NULL,
    user_password VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    user_avatar VARCHAR(255),
    created_by VARCHAR(100),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Tạo bảng product_entity
CREATE TABLE IF NOT EXISTS product_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    brand VARCHAR(100),
    category VARCHAR(100),
    sub_category VARCHAR(100),
    collection VARCHAR(100),
    base_price NUMERIC(18,2),
    discount_percent REAL,
    origin VARCHAR(255),
    material VARCHAR(255),
    fit_type VARCHAR(100),
    care_instruction VARCHAR(255),
    status VARCHAR(100),
    thumbnail_url TEXT,
    rating_average DOUBLE PRECISION,
    rating_count INTEGER,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Optional index để tăng hiệu năng truy vấn sản phẩm
--CREATE INDEX IF NOT EXISTS idx_product_category ON product_entity (category);
--CREATE INDEX IF NOT EXISTS idx_product_status ON product_entity (status);
