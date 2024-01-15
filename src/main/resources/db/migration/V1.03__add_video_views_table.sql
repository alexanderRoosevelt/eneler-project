CREATE TABLE videos
(
    id           SERIAL PRIMARY KEY,
    url          VARCHAR(255) NOT NULL,
    image_url    VARCHAR(255),
    created_date TIMESTAMP,
    access       DATE,
    short_info   VARCHAR(255),
    full_info    TEXT
);

CREATE TABLE user_video_views
(
    user_id           BIGINT,
    video_id          BIGINT,
    watched_until_end BOOLEAN,
    PRIMARY KEY (user_id, video_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (video_id) REFERENCES videos (id)
);
