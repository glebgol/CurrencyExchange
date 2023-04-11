INSERT INTO currencies (code, full_name, sign)
VALUES ('EUR', 'Euro', '€'),
       ('USD', 'US Dollar', '$'),
       ('BYN', 'Belarussian Ruble', 'Br'),
       ('Russian Ruble', 'RUB', '₽ ');


INSERT INTO exchange_rates (base_currency_id, target_currency_id, rate)
VALUES (1, 2, 1.09),
       (1, 3, 2.76),
       (1, 4, 89.70),
       (2, 1, 0.92),
       (2, 3, 2.53),
       (2, 4, 82.16),
       (3, 1, 0.36),
       (3, 2, 0.4),
       (3, 4, 32.53),
       (4, 1, 0.011),
       (4, 2, 0.012),
       (4, 3, 0.031);

