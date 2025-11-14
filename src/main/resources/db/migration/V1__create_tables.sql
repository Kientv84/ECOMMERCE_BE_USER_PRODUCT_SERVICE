-- ======================================
-- V1__create_tables.sql
-- ======================================
CREATE EXTENSION IF NOT EXISTS unaccent;

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

CREATE TABLE IF NOT EXISTS user_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
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

CREATE TABLE IF NOT EXISTS brand_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    brand_name VARCHAR(255) NOT NULL,
    brand_code VARCHAR(100) UNIQUE,
    origin VARCHAR(255),
    description TEXT,
    thumbnail_url TEXT,
    status VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS category_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_name VARCHAR(255) NOT NULL,
    category_code VARCHAR(100) UNIQUE,
    description TEXT,
    thumbnail_url TEXT,
    status VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS sub_category_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sub_category_name VARCHAR(255) NOT NULL,
    sub_category_code VARCHAR(100) UNIQUE,
    description TEXT,
    thumbnail_url TEXT,
    status VARCHAR(50),
    category_id UUID NOT NULL REFERENCES category_entity(id) ON DELETE CASCADE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS collection_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    collection_name VARCHAR(255) NOT NULL,
    collection_code VARCHAR(100) UNIQUE,
    description TEXT,
    status VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS material_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    material_name VARCHAR(255) NOT NULL,
    material_code VARCHAR(100) UNIQUE,
    description TEXT,
    status VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- 8. Product
CREATE TABLE IF NOT EXISTS product_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_name VARCHAR(255) NOT NULL,
    product_code VARCHAR(255),
    description TEXT,

    brand_id UUID NOT NULL REFERENCES brand_entity(id) ON DELETE CASCADE,
    category_id UUID NOT NULL REFERENCES category_entity(id) ON DELETE CASCADE,
    sub_category_id UUID REFERENCES sub_category_entity(id) ON DELETE SET NULL,
    collection_id UUID REFERENCES collection_entity(id) ON DELETE SET NULL,
    material_id UUID REFERENCES material_entity(id) ON DELETE SET NULL,

    base_price NUMERIC(18,2),
    discount_percent REAL,
    origin VARCHAR(255),
    fit_type VARCHAR(100),
    care_instruction VARCHAR(255),
    count_in_stock INTEGER,
    status VARCHAR(100),
    thumbnail_url TEXT,
    rating_average DOUBLE PRECISION,
    rating_count INTEGER,

    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE INDEX IF NOT EXISTS idx_product_category ON product_entity (category_id);
CREATE INDEX IF NOT EXISTS idx_product_brand ON product_entity (brand_id);
CREATE INDEX IF NOT EXISTS idx_product_status ON product_entity (status);

-- 1. Thêm cột tsvector
ALTER TABLE user_entity
ADD COLUMN document_tsv tsvector;

-- 2. Cập nhật dữ liệu hiện có
UPDATE user_entity
SET document_tsv = to_tsvector(
    'simple',
    coalesce(user_name,'') || ' ' || coalesce(user_email,'') || ' ' || coalesce(user_phone_number,'')
);

-- 3. Tạo GIN index để search nhanh
CREATE INDEX idx_user_document_tsv ON user_entity USING GIN(document_tsv);

-- 4. Tạo function trigger để cập nhật tự động khi insert/update
CREATE FUNCTION user_tsv_trigger() RETURNS trigger AS $$
BEGIN
    NEW.document_tsv := to_tsvector(
        'simple',
        coalesce(NEW.user_name,'') || ' ' || coalesce(NEW.user_email,'') || ' ' || coalesce(NEW.user_phone_number,'')
    );
    RETURN NEW;
END
$$ LANGUAGE plpgsql;

-- 5. Tạo trigger gắn vào bảng
CREATE TRIGGER tsvectorupdate_user BEFORE INSERT OR UPDATE
ON user_entity FOR EACH ROW EXECUTE PROCEDURE user_tsv_trigger();