CREATE TABLE System_Employee (
    Id BIGINT PRIMARY KEY,
    System_Employee_Display VARCHAR(255),
    System_Employee_Salary BIGINT,
    Status VARCHAR(50),
    CreatedBy VARCHAR(100),
    CreatedDate DATE,
    UpdateBy VARCHAR(100),
    UpdateDate DATE,
    CONSTRAINT fk_employee FOREIGN KEY (Id) REFERENCES System_User(Id) ON DELETE CASCADE  -- Id nếu xóa trong bảng system_user thì bảng system_employee cũng xóa
);

INSERT INTO System_Employee (
    Id,
    System_Employee_Display,
    System_Employee_Salary,
    Status,
    CreatedBy
)
VALUES (
    1, -- Id phải tồn tại trong SystemUser
    'Senior Developer',
    25000000,
    'Active',
    'admin'
);
