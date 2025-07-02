CREATE TABLE IF NOT EXISTS System_Role (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    System_Role_Name VARCHAR(100),
    System_Role_Description TEXT,
    Status VARCHAR(50),
    CreateBy VARCHAR(100),
    CreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdateBy VARCHAR(100),
    UpdateDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO System_Role (
    System_Role_Name,
    System_Role_Description,
    Status,
    CreateBy
) VALUES (
    "Admin",
    "Đây là admin",
    "ACTIVE",
    "ADMIN"
);
