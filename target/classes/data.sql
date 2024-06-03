--DDL to create tables

--DROP TABLE IF EXISTS Product;
--DROP TABLE IF EXISTS Category;
--
--CREATE TABLE Category (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    parent_id BIGINT,
--    FOREIGN KEY (parent_id) REFERENCES Category(id)
--);
--
--CREATE TABLE Product (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    description VARCHAR(255),
--    price DOUBLE,
--    available BOOLEAN DEFAULT FALSE,
--    category_id BIGINT,
--    FOREIGN KEY (category_id) REFERENCES Category(id)
--);

-- Inserir dados
-- Categories
INSERT INTO Category (name, parent_id) VALUES ('Electronics', NULL);
INSERT INTO Category (name, parent_id) VALUES ('Computers', 1);
INSERT INTO Category (name, parent_id) VALUES ('Laptops', 2);
INSERT INTO Category (name, parent_id) VALUES ('Smartphones', 1);

-- Products
INSERT INTO Product (name, description, price, available, category_id) VALUES ('Dell XPS 13', 'A high-end laptop', 999.99, true, 3);
INSERT INTO Product (name, description, price, available, category_id) VALUES ('iPhone 13', 'Latest iPhone model', 799.99, false, 4);
INSERT INTO Product (name, description, price, available, category_id) VALUES ('Dell Inspiron 15', 'Intel® Core™ i7-1355U', 7390.00, true, 3);
INSERT INTO Product (name, description, price, available, category_id) VALUES ('Samsung Galaxy S24', '128GB|8GB RAM|Black', 5399.10, false, 4);


