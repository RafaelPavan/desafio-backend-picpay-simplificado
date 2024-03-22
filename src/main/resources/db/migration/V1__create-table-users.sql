CREATE TABLE IF NOT EXISTS  users
(
    id         UUID           NOT NULL,
    first_name VARCHAR(100)        NOT NULL,
    last_name  VARCHAR(100)        NOT NULL,
    document   VARCHAR(11) UNIQUE  NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    balance    NUMERIC             NOT NULL,
    user_type  VARCHAR(11)         NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);