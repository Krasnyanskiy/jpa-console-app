-- @author Alexander Krasnyanskiy


-- Category
CREATE TABLE IF NOT EXISTS category_table (
  id   SERIAL PRIMARY KEY NOT NULL,
  name CHARACTER(250)     NOT NULL
);
ALTER TABLE category_table OWNER TO postgres;


-- Order
CREATE TABLE IF NOT EXISTS order_table (
  id             SERIAL PRIMARY KEY NOT NULL,
  total_price    DECIMAL(12, 2),
  date           TIMESTAMP WITH TIME ZONE DEFAULT now(),
  product_amount INT
);
ALTER TABLE order_table OWNER TO postgres;


-- Product
CREATE TABLE IF NOT EXISTS product_table (
  id          SERIAL PRIMARY KEY             NOT NULL,
  name        CHARACTER VARYING(250)         NOT NULL,
  price       DECIMAL(12, 2),
  category_id INT,
  CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES category_table (id) MATCH FULL
  ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE product_table OWNER TO postgres;


-- ProductOrder
CREATE TABLE IF NOT EXISTS product_order_table (
  product_id INT,
  order_id   INT,
  CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES product_table (id) MATCH FULL
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT order_fk FOREIGN KEY (order_id) REFERENCES order_table (id) MATCH FULL
  ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE product_order_table OWNER TO postgres;


-- fill the category table with data
INSERT INTO category_table (name) VALUES ('Electronics');
INSERT INTO category_table (name) VALUES ('Food');
INSERT INTO category_table (name) VALUES ('Media & Entertainment');
INSERT INTO category_table (name) VALUES ('Children''s Products');
INSERT INTO category_table (name) VALUES ('Sports');


-- fill the Product table with some data
INSERT INTO product_table (name, price, category_id) VALUES ('Samsung 32 Inches HD Television', 468.35, 1);
INSERT INTO product_table (name, price, category_id) VALUES ('iPhone 6 Space Gray 64Gb', 1000.55, 1);
INSERT INTO product_table (name, price, category_id) VALUES ('Banana', 0.15, 2);
INSERT INTO product_table (name, price, category_id) VALUES ('Strawberry', 2.45, 2);
INSERT INTO product_table (name, price, category_id) VALUES ('Nike Air Zoom Pegasus 31', 100.00, 5);
INSERT INTO product_table (name, price, category_id) VALUES ('Nike Free 5.0 TR Fit 4 Printed', 110.00, 5);
INSERT INTO product_table (name, price, category_id) VALUES ('Lego 10242 MINI Cooper', 99.99, 4);
INSERT INTO product_table (name, price, category_id) VALUES ('Lego Technic Mobile-Crane-MK-II-42009', 219.99, 4);
INSERT INTO product_table (name, price, category_id) VALUES ('Assassin''s Creed III', 12.99, 3);
INSERT INTO product_table (name, price, category_id) VALUES ('WatchDogs', 32.99, 3);
INSERT INTO product_table (name, price, category_id) VALUES ('FIFA 2015', 56.45, 3);


-- fill the Order table
INSERT INTO order_table (total_price, product_amount) VALUES (156.54, 2);
-- 100.00 + 56.45
INSERT INTO order_table (total_price, product_amount) VALUES (1115.99, 4);
-- 1000.55 + 2.45 + 100.00 + 12.99
INSERT INTO order_table (total_price, product_amount) VALUES (156.54, 2);
-- 100.00 + 56.45
INSERT INTO order_table (total_price, product_amount) VALUES (1880.77, 7);
-- 468.35 + 56.45 + 99.99 + 219.99 + 2.45 + 1000.55 + 32.99
INSERT INTO order_table (total_price, product_amount) VALUES (1980.77, 8);
-- 468.35 + 56.45 + 99.99 + 219.99 + 2.45 + 1000.55 + 32.99 + 100.00
INSERT INTO order_table (total_price, date, product_amount) VALUES (159.00, '5-09-2015', 3);


-- fill the ProductOrder table
INSERT INTO product_order_table (product_id, order_id) VALUES (5, 1);
INSERT INTO product_order_table (product_id, order_id) VALUES (11, 1);

INSERT INTO product_order_table (product_id, order_id) VALUES (2, 2);
INSERT INTO product_order_table (product_id, order_id) VALUES (4, 2);
INSERT INTO product_order_table (product_id, order_id) VALUES (5, 2);
INSERT INTO product_order_table (product_id, order_id) VALUES (9, 2);

INSERT INTO product_order_table (product_id, order_id) VALUES (5, 3);
INSERT INTO product_order_table (product_id, order_id) VALUES (11, 3);

INSERT INTO product_order_table (product_id, order_id) VALUES (1, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (11, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (7, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (8, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (4, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (2, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (10, 4);

INSERT INTO product_order_table (product_id, order_id) VALUES (1, 5);
INSERT INTO product_order_table (product_id, order_id) VALUES (11, 5);
INSERT INTO product_order_table (product_id, order_id) VALUES (7, 5);
INSERT INTO product_order_table (product_id, order_id) VALUES (8, 5);
INSERT INTO product_order_table (product_id, order_id) VALUES (4, 5);
INSERT INTO product_order_table (product_id, order_id) VALUES (2, 5);
INSERT INTO product_order_table (product_id, order_id) VALUES (10, 5);
INSERT INTO product_order_table (product_id, order_id) VALUES (5, 5);

INSERT INTO product_order_table (product_id, order_id) VALUES (5, 6);
INSERT INTO product_order_table (product_id, order_id) VALUES (11, 6);

-- simple select
SELECT
  prod.name,
  prod.price,
  cat.name
FROM product_table AS prod
  JOIN category_table AS cat
    ON prod.category_id = cat.id;


-- all categories
SELECT
  *
FROM category_table;
SELECT
  *
FROM product_table;
SELECT
  *
FROM order_table;
SELECT
  *
FROM product_order_table;


-- delete all bananas form products
-- DELETE FROM product_table
-- WHERE name = 'Banana';

-- drop these bitches
DROP TABLE product_order_table;
DROP TABLE order_table;
DROP TABLE product_table;
DROP TABLE category_table;


-- change type of the column
-- ALTER TABLE product_table
-- ALTER COLUMN price DROP DEFAULT,
-- ALTER COLUMN price TYPE DECIMAL(12, 2);




SELECT
  o.date,
  o.total_price
FROM order_table AS o
  JOIN product_order_table AS po ON o.id = po.order_id
  JOIN product_table AS p ON p.id = po.product_id
WHERE o.total_price > 1000.00
  GROUP BY o.date, o.total_price;


INSERT INTO order_table (total_price, date, product_amount) VALUES (100.00, '10-02-2012', 1);
INSERT INTO product_order_table (product_id, order_id) VALUES (5, 7);