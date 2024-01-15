ALTER TABLE users
    ADD COLUMN user_token_id uuid;

CREATE TABLE about_user
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    text    VARCHAR(1000),
    FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT unique_user_id UNIQUE (user_id)
);
