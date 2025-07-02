ALTER TABLE System_User
ADD CONSTRAINT fk_user_role
FOREIGN KEY (System_User_Roles)
REFERENCES System_Role(id);
