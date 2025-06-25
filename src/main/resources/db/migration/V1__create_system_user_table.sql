CREATE TABLE IF NOT EXISTS System_User (
    System_User_Code VARCHAR(50) PRIMARY KEY,
    System_User_Name VARCHAR(100) NOT NULL,
    System_User_BirthDay DATE,
    System_User_Gender VARCHAR(20),
    System_User_PhoneNumber BIGINT,
    System_User_Address VARCHAR(255),
    System_User_Email VARCHAR(100),
    System_User_Roles BIGINT,
    System_User_Password VARCHAR(255) NOT NULL,
    System_User_Permission VARCHAR(100),
    Status VARCHAR(50),
    System_User_Avatar LONGBLOB,
    System_User_ChangePassword BOOLEAN DEFAULT FALSE,
    CreateBy VARCHAR(100),
    CreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdateBy VARCHAR(100),
    UpdateDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
