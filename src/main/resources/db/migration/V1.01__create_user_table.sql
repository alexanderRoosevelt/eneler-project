CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP,
    user_name    VARCHAR(255),
    email        VARCHAR(255),
    role         VARCHAR(50),
    registered   BOOLEAN
);
