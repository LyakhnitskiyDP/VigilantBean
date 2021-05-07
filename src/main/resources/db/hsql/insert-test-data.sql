
INSERT INTO category (category_id, name, short_name, description)
    VALUES (1, 'Coffee', 'coffee', 'Best coffee in the world'),
           (2, 'Dairy Products', 'diaryProducts', 'Diary products related to coffee'),
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
    VALUES (1, 'Irish Beans', 'The best beans from Ireland', 1, 300, 10, 0, 11.95, 'Ireland', 'Leprechaun INC.', 'Coffee Beans, Magic', 'Might contain nuts'),
           (2, 'Jelly Beans', 'It is not coffee beans, but it is beans', 1, 500, 15, 0, 5.5, 'Argentina', 'SISCO', 'Sugar, Gelatine, Juice', 'None'),
           (3, 'Chocolate Beans', 'Delicious coffee beans covered in black chocolate', 1, 350, 20, 0, 6.35, 'Russian Federation', 'Krasnaya Zena', 'Dark chocolate, Coffee Beans, Sugar', 'None'),
           (4, 'Powder Coffee', 'Coffee Powder for bakery', 1, 600, 10, 0, 29.99, 'Germany', 'Vigilant Bean INC.', 'Bean powder', 'None'),
           (5, 'Powder Milk', 'Milk powder to add to your morning coffee', 1, 500, 10, 0, 10.00, 'Argentina', 'SISCO', 'Milk', 'None'),
           (6, 'Cheese', 'It is not coffee beans, but it is beans', 5, 200, 10, 3, 11.99, 'Argentina', 'SISCO', 'Beans', 'None'),
           (7, 'Chocolate cream', 'It is not coffee beans, but it is beans', 5, 200, 10, 3, 11.99, 'Argentina', 'SISCO', 'Beans', 'None'),
           (8, 'Ultra cool beans', 'Just plain coffee beans here, nothing special', 5, 200, 10, 3, 11.99, 'Russia', 'SISCO', 'Beans', 'None');

INSERT INTO product_category (product_id, category_id)
    VALUES (1, 1),
           (2, 3),
           (3, 1),
           (3, 3),
           (4, 1),
           (5, 2),
           (6, 2),
           (7, 3),
           (8, 1);

INSERT INTO picture (picture_id, name, extension, relative_path)
    VALUES (1, 'coffee', 'jpg', 'categories/'),
           (2, 'diaryProducts', 'jpg', 'categories/'),
           (3, 'sweets', 'jpg', 'categories/');

INSERT INTO picture (picture_id, name, extension, relative_path)
    VALUES (4, '1', 'png', 'products/'),
           (5, '2', 'jpg', 'products/'),
           (6, '3', 'png', 'products/'),
           (7, '4', 'jpg', 'products/'),
           (8, '5', 'jpg', 'products/'),
           (9, '6', 'jpg', 'products/'),
           (10, '7', 'jpg', 'products/'),
           (11, '8', 'jpg', 'products/');


INSERT INTO category_picture (picture_id, category_id)
    VALUES (1, 1),
           (2, 2),
           (3, 3);

INSERT INTO product_picture (picture_id, product_id)
    VALUES (4, 1),
           (5, 3),
           (6, 4),
           (7, 5),
           (8, 2),
           (9, 6),
           (10, 7),
           (11, 8);

INSERT INTO app_user (app_user_id, username, password, email, enabled)
    VALUES (1, 'cody', '$e0801$Naq1GPMzFd9ipZVdjZvLB63lC3cVKf64u8yuocm0iAxdeJg7N7hXiJb2EHfTIOgVykM/ZdDp4KFKJcfpevP62Q==$6jTWTHsUXGr/23I2ILXM2qaRApTs09dvY6ysv9ibl7w=', 'HamsterGo2014@yandex.ru', true);

INSERT INTO role (role_id, name)
    VALUES (1, 'ROLE_CUSTOMER'),
           (2, 'ROLE_ADMIN');

INSERT INTO user_role (app_user_id, role_id)
    VALUES (1, 2);

INSERT INTO cart (cart_id, discount)
    VALUES (1, 0);

INSERT INTO coupon (coupon_value, discount_percentage)
    VALUES ('OFF50', 50);
