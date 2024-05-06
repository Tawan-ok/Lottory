-- Drop tables if they exist
DROP TABLE IF EXISTS user_ticket CASCADE;
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS admin CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create tables
CREATE TABLE lottery (
    lottery_id SERIAL PRIMARY KEY,
    ticket_number VARCHAR(6) UNIQUE NOT NULL,
    price DECIMAL NOT NULL,
    amount INT NOT NULL
);

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    unique_id VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE admin (
    admin_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_ticket (
    user_ticket_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    lottery_id INT NOT NULL,
    purchase_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (lottery_id) REFERENCES lottery(lottery_id)
);


