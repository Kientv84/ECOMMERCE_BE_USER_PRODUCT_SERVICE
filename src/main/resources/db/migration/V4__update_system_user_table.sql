
ALTER TABLE System_User
    ADD UNIQUE (System_User_Email);

ALTER TABLE System_User
    DROP COLUMN System_User_Permission;

-- 3. Xoá cột System_User_ChangePassword
ALTER TABLE System_User
    DROP COLUMN System_User_ChangePassword;

ALTER TABLE System_User
    MODIFY System_User_PhoneNumber VARCHAR(15);

UPDATE System_User
SET System_User_PhoneNumber = 
    CASE 
        WHEN System_User_PhoneNumber IS NOT NULL AND LEFT(System_User_PhoneNumber, 1) != '0'
            THEN CONCAT('0', System_User_PhoneNumber)
        ELSE System_User_PhoneNumber
    END;
