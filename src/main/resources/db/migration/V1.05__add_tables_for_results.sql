CREATE TABLE results (
    id BIGSERIAL NOT NULL PRIMARY KEY ,
    user_id BIGINT REFERENCES users (id),
    count INT,
    part VARCHAR(50)
);