
DROP TABLE product_picture IF EXISTS;
DROP TABLE category_picture IF EXISTS;
DROP TABLE picture IF EXISTS;

DROP TABLE product_category IF EXISTS;
DROP TABLE product IF EXISTS;
DROP TABLE category IF EXISTS;

CREATE TABLE category (
    category_id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    short_name VARCHAR(100) NOT NULL,
    description VARCHAR(300) NOT NULL
)

CREATE TABLE product (
    product_id INTEGER PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    quantity_per_unit INT NOT NULL,
    unit_weight DECIMAL NOT NULL,
    units_in_stock BIGINT NOT NULL,
    units_in_order BIGINT NOT NULL,
    unit_price DECIMAL NOT NULL,
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
    picture_id INTEGER PRIMARY KEY,
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


