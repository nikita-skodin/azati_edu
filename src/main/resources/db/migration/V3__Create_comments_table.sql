CREATE TABLE comments
(
    id         BIGSERIAL PRIMARY KEY,
    content    TEXT   NOT NULL,
    user_id    BIGINT NOT NULL,
    post_id    BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_comment FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_post_comment FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
);
