-- @author Alexander Krasnyanskiy


-- Clean up
DROP TABLE IF EXISTS product_order_table;
DROP TABLE IF EXISTS order_table;
DROP TABLE IF EXISTS order_table_history;
DROP TABLE IF EXISTS product_table;
DROP TABLE IF EXISTS category_table;
DROP TABLE IF EXISTS user_table;


-- User
CREATE TABLE IF NOT EXISTS user_table (
  id    SERIAL PRIMARY KEY NOT NULL,
  name  CHARACTER(250)     NOT NULL,
  email CHARACTER(250)     NOT NULL
);
ALTER TABLE user_table OWNER TO postgres;


-- Category
CREATE TABLE IF NOT EXISTS category_table (
  id   SERIAL PRIMARY KEY NOT NULL,
  name CHARACTER(250)     NOT NULL
);
ALTER TABLE category_table OWNER TO postgres;


-- Order
CREATE TABLE IF NOT EXISTS order_table (
  id             SERIAL PRIMARY KEY NOT NULL,
  user_id        INT,
  total_price    DECIMAL(12, 2),
  date           TIMESTAMP WITH TIME ZONE DEFAULT now(),
  product_amount INT,
  CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES user_table (id) MATCH FULL
  ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE order_table OWNER TO postgres;


-- Product
CREATE TABLE IF NOT EXISTS product_table (
  id          SERIAL PRIMARY KEY             NOT NULL,
  name        CHARACTER VARYING(250)         NOT NULL,
  price       DECIMAL(12, 2),
  qr_code     BYTEA,
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


CREATE TABLE IF NOT EXISTS order_table_history (
  id             SERIAL PRIMARY KEY NOT NULL,
  order_id       INT,
  user_id        INT,
  total_price    DECIMAL(12, 2),
  date           TIMESTAMP WITH TIME ZONE DEFAULT now(),
  product_amount INT
);
ALTER TABLE order_table_history OWNER TO postgres;


-- balls away!
CREATE OR REPLACE FUNCTION save_order_in_history()
  RETURNS TRIGGER
AS $$
BEGIN
  INSERT INTO order_table_history (order_id, total_price, user_id, product_amount)
  VALUES (NEW.id, NEW.total_price, NEW.user_id, NEW.product_amount);
  RETURN NULL;
END $$ LANGUAGE plpgsql;

CREATE TRIGGER order_trigger AFTER INSERT OR UPDATE
ON "order_table" FOR EACH ROW EXECUTE PROCEDURE save_order_in_history();