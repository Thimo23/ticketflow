CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    location VARCHAR(40) NOT NULL,
    date DATE NOT NULL,
    maximum_capacity INT NOT NULL
);

CREATE TABLE section (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    section_type VARCHAR(40) NOT NULL,
    maximum_capacity INT NOT NULL,
    price DOUBLE NOT NULL,
    event_id BIGINT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE
);

CREATE TABLE seat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_number INT NOT NULL,
    section_id BIGINT NOT NULL,
    seat_status VARCHAR(40) NOT NULL,
    UNIQUE (seat_number, section_id),
    FOREIGN KEY (section_id) REFERENCES section(id) ON DELETE CASCADE
);

CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_method VARCHAR(40) NOT NULL,
    transaction_status VARCHAR(40) NOT NULL,
    transaction_date DATETIME NOT NULL
);

CREATE TABLE reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL UNIQUE,
    transaction_id BIGINT NULL,
    reservation_status VARCHAR(40) NOT NULL,
    reservation_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seat(id) ON DELETE CASCADE,
    FOREIGN KEY (transaction_id) REFERENCES transaction(id) ON DELETE SET NULL
);

