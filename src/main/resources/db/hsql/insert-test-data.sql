
INSERT INTO category (category_id, name, short_name, description)
    VALUES (1, 'Coffee', 'coffee', 'Best coffee in the world'),
           (2, 'Diary Products', 'diaryProducts', 'Diary products related to coffee'),
           (3, 'Sweets', 'sweets', 'Best sweets in the world');

INSERT INTO product (product_id,
                     name,
                     description,
                     quantity_per_unit,
                     unit_weight,
                     units_in_stock,
                     units_in_order,
                     unit_price,
                     origins,
                     manufacturer,
                     ingredients,
                     allergy_information)
    VALUES (1, '1Holy beans', 'Coffee beans with magic power', 5, 200, 10, 3, 11.99, 'Brazil', 'SISCO', 'Beans', 'None'),
           (2, '2Jelly Beans', 'It is not coffee beans, but it is beans', 5, 200, 10, 3, 11.99, 'Argentina', 'SISCO', 'Beans', 'None'),
           (3, '3Chocolate Beans', 'It is not coffee beans, but it is beans', 5, 200, 10, 3, 11.99, 'Argentina', 'SISCO', 'Beans', 'None'),
           (4, '4Wooden Beans', 'It is not coffee beans, but it is beans', 5, 200, 10, 3, 11.99, 'Argentina', 'SISCO', 'Beans', 'None'),
           (5, '5Milk', 'It is not coffee beans, but it is beans', 5, 200, 10, 3, 11.99, 'Argentina', 'SISCO', 'Beans', 'None'),
           (6, '6Cheese', 'It is not coffee beans, but it is beans', 5, 200, 10, 3, 11.99, 'Argentina', 'SISCO', 'Beans', 'None'),
           (7, '7Chocolate cream', 'It is not coffee beans, but it is beans', 5, 200, 10, 3, 11.99, 'Argentina', 'SISCO', 'Beans', 'None'),
           (8, '8Ultra cool beans', 'Just plain coffee beans here, nothing special', 5, 200, 10, 3, 11.99, 'Russia', 'SISCO', 'Beans', 'None');

INSERT INTO product_category (product_id, category_id)
    VALUES (1, 1),
           (2, 1),
           (2, 2),
           (2, 3),
           (3, 1),
           (4, 1),
           (5, 2),
           (6, 3),
           (7, 1),
           (8, 2);

INSERT INTO picture (picture_id, name, extension, relative_path)
    VALUES (1, 'coffee', 'jpg', 'categories/'),
           (2, 'diaryProducts', 'jpg', 'categories/'),
           (3, 'sweets', 'jpg', 'categories/');

INSERT INTO category_picture (picture_id, category_id)
    VALUES (1, 1),
           (2, 2),
           (3, 3);

INSERT INTO app_user (app_user_id, username, password, email)
    VALUES (1, 'cody', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'HamsterGo2014@yandex.ru');

INSERT INTO role (role_id, name)
    VALUES (1, 'customer'),
           (2, 'admin');

INSERT INTO user_role (app_user_id, role_id)
    VALUES (1, 1);
