DROP TABLE verification_token IF EXISTS;

DROP TABLE app_order IF EXISTS;
DROP TABLE user_role IF EXISTS;
DROP TABLE role IF EXISTS;
DROP TABLE app_user IF EXISTS;

DROP TABLE cart_item IF EXISTS;
DROP TABLE cart IF EXISTS;

DROP TABLE product_picture IF EXISTS;
DROP TABLE category_picture IF EXISTS;
DROP TABLE picture IF EXISTS;

DROP TABLE product_category IF EXISTS;
DROP TABLE product IF EXISTS;
DROP TABLE category IF EXISTS;



CREATE TABLE category (
    category_id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    short_name VARCHAR(100) NOT NULL,
    description VARCHAR(300) NOT NULL
)

CREATE TABLE product (
    product_id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    quantity_per_unit INT NOT NULL,
    unit_weight DECIMAL(10, 3) NOT NULL,
    units_in_stock BIGINT NOT NULL,
    units_in_order BIGINT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    --added_by INTEGER FOREIGN KEY REFERENCES user (user_id),
    origins VARCHAR(250) NOT NULL,
    manufacturer VARCHAR(200) NOT NULL,
    ingredients VARCHAR(200) NOT NULL,
    allergy_information VARCHAR(200) NOT NULL
)

CREATE TABLE product_category (
    product_id INTEGER FOREIGN KEY REFERENCES product (product_id),
    category_id INTEGER FOREIGN KEY REFERENCES category (category_id),
    CONSTRAINT pk_product_category PRIMARY KEY (product_id, category_id)
)

CREATE TABLE picture (
    picture_id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    extension VARCHAR(10) NOT NULL,
    relative_path VARCHAR(100) NOT NULl
)

CREATE TABLE category_picture (
    picture_id INTEGER FOREIGN KEY REFERENCES picture (picture_id),
    category_id INTEGER FOREIGN KEY REFERENCES category (category_id),
    CONSTRAINT pk_category_picture PRIMARY KEY (picture_id, category_id)
)

CREATE TABLE product_picture (
    picture_id INTEGER FOREIGN KEY REFERENCES picture (picture_id),
    product_id INTEGER FOREIGN KEY REFERENCES product (product_id),
    CONSTRAINT pk_product_picture PRIMARY KEY (picture_id, product_id)
)

CREATE TABLE app_user (
    app_user_id INTEGER IDENTITY PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(512) NOT NULL,
    email VARCHAR(100) NOT NULL,
    registration_date TIMESTAMP DEFAULT NOW(),
    enabled BOOLEAN DEFAULT FALSE,
    not_locked BOOLEAN DEFAULT TRUE
)

CREATE TABLE role (
    role_id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
)

CREATE TABLE user_role (
    app_user_id INTEGER FOREIGN KEY REFERENCES app_user (app_user_id),
    role_id INTEGER FOREIGN KEY REFERENCES role (role_id),
    CONSTRAINT pk_user_role PRIMARY KEY (app_user_id, role_id)
)

CREATE TABLE verification_token (
    verification_token_id INTEGER IDENTITY PRIMARY KEY,
    app_user_id INTEGER FOREIGN KEY REFERENCES app_user (app_user_id),
    token VARCHAR(512) NOT NULL,
    expiry_date TIMESTAMP DEFAULT NOW()
)

CREATE TABLE cart (
    cart_id INTEGER IDENTITY PRIMARY KEY,
    discount INTEGER
)

CREATE TABLE cart_item (
    cart_item_id INTEGER IDENTITY PRIMARY KEY,
    cart_id INTEGER FOREIGN KEY REFERENCES cart (cart_id),
    product_id INTEGER FOREIGN KEY REFERENCES product (product_id),
    quantity INTEGER NOT NULL CHECK (quantity > 0)
)

CREATE TABLE app_order (
   app_order_id INTEGER IDENTITY PRIMARY KEY,
   customer_id INTEGER FOREIGN KEY REFERENCES app_user (app_user_id),
   cart_id INTEGER FOREIGN KEY REFERENCES cart (cart_id),
   date_of_creation TIMESTAMP DEFAULT NOW(),
   date_of_arrival TIMESTAMP
)


