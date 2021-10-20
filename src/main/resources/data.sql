DROP TABLE IF EXISTS phone;

CREATE TABLE phone(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id uuid not null,
    phone varchar(255),
    city_code varchar(255),
    country_code varchar(255)
);

DROP TABLE IF EXISTS user;

CREATE TABLE user(
    id uuid default random_uuid() primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    modified_date date,
    created_date date,
    last_login date,
    is_active boolean,
    token varchar(2500)
);

ALTER TABLE phone
    ADD CONSTRAINT phone_user_id FOREIGN KEY (user_id) REFERENCES user (id);