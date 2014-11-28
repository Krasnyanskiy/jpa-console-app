-- @author Alexander Krasnyanskiy


-- fill {User} table with data


-- fill {Category} table with data
INSERT INTO category_table (name) VALUES ('Electronics');
INSERT INTO category_table (name) VALUES ('Food');
INSERT INTO category_table (name) VALUES ('Media & Entertainment');
INSERT INTO category_table (name) VALUES ('Children''s Products');
INSERT INTO category_table (name) VALUES ('Sports');


-- fill {Product} table with some data
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


-- fill {Order} table
INSERT INTO order_table (total_price, product_amount) VALUES (156.54, 2);
INSERT INTO order_table (total_price, product_amount) VALUES (1115.99, 4);
INSERT INTO order_table (total_price, product_amount) VALUES (156.54, 2);
INSERT INTO order_table (total_price, product_amount) VALUES (1880.77, 7);
INSERT INTO order_table (total_price, product_amount) VALUES (1980.77, 8);
INSERT INTO order_table (total_price, date, product_amount) VALUES (156.54, '5-09-2015', 2);


-- fill {ProductOrder} table
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

INSERT INTO product_order_table (product_id, order_id) VALUES (1, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (11, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (7, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (8, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (4, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (2, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (10, 4);
INSERT INTO product_order_table (product_id, order_id) VALUES (5, 4);

INSERT INTO product_order_table (product_id, order_id) VALUES (5, 6);
INSERT INTO product_order_table (product_id, order_id) VALUES (11, 6);