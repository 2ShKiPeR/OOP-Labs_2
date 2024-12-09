CREATE SCHEMA IF NOT EXISTS lab;

CREATE SEQUENCE functions_id_seq;

CREATE TABLE functions (
                           id INT PRIMARY KEY DEFAULT nextval('functions_id_seq'),
                           function_name VARCHAR(255),
                           count INT CHECK (count >= 2),
                           x_from DOUBLE PRECISION,
                           x_to DOUBLE PRECISION
);

CREATE TABLE point (
                       id INT PRIMARY KEY DEFAULT nextval('point_id_seq'),
                       function_id INT,
                       x_val DOUBLE PRECISION,
                       y_val DOUBLE PRECISION,
                       FOREIGN KEY (function_id) REFERENCES labs.functions(id) ON DELETE CASCADE
);

CREATE TABLE user (
                        id INT PRIMARY KEY DEFAULT,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        role VARCHAR(50) NOT NULL
);
