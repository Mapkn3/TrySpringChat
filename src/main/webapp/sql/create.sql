CREATE TABLE role (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
    name VARCHAR(20) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE profile (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE account (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
    nickname VARCHAR(30) UNIQUE,
    password VARCHAR(30) NOT NULL,
    profile_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (profile_id) REFERENCES profile(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE message (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
    body VARCHAR(255),
    from_id BIGINT NOT NULL,
    to_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (from_id) REFERENCES account(id),
    FOREIGN KEY (to_id) REFERENCES account(id)
);