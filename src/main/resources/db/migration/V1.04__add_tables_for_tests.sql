CREATE TABLE questions
(
    id            SERIAL PRIMARY KEY,
    question_text VARCHAR(1000),
    options       VARCHAR(1000)
);

CREATE TABLE users_questions_attached
(
    id          SERIAL NOT NULL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES users (id),
    question_id INT    NOT NULL REFERENCES questions (id)
);