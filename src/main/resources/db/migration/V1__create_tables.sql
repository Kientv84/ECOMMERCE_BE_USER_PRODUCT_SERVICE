-- ======================================
-- V1__create_tables.sql
-- ======================================

-- 1. Tạo bảng RoleEntity (role_entity)
CREATE TABLE IF NOT EXISTS role_entity (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    role_description TEXT,
    status VARCHAR(50),
    created_by VARCHAR(500),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_by VARCHAR(500),
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tạo bảng UserEntity (user_entity)
CREATE TABLE IF NOT EXISTS user_entity (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_code VARCHAR(50) UNIQUE,
    user_name VARCHAR(100) NOT NULL,
    user_birthday DATE,
    user_gender VARCHAR(20),
    user_phone_number VARCHAR(50),
    user_address VARCHAR(255),
    user_email VARCHAR(100) UNIQUE,
    role_id BIGINT,
    user_password VARCHAR(255) NOT NULL,
    status VARCHAR(50),
   user_avatar VARCHAR(50),
    created_by VARCHAR(100),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role_entity(id)
);
