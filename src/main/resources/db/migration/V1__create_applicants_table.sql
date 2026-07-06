CREATE TABLE applicants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    application_id VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    sex VARCHAR(255) NOT NULL,
    desired_position VARCHAR(255) NOT NULL,
    application_date DATE NOT NULL,
    application_status VARCHAR(255) NOT NULL,
    training_status VARCHAR(255) NOT NULL,
    image VARCHAR(255)
);
