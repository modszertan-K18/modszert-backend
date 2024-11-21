ALTER TABLE product_images
DROP
CONSTRAINT fkhrvh0hklwgllpdjlra6hx7p9u;

ALTER TABLE product
    ADD buyer_id VARCHAR(255);

DROP TABLE product_images CASCADE;

ALTER TABLE product
DROP
COLUMN bid_increment;

ALTER TABLE product
DROP
COLUMN transaction_id;

DROP SEQUENCE app_user_seq CASCADE;