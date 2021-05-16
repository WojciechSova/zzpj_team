INSERT INTO access_level (level)
VALUES ('CLIENT'),
       ('EMPLOYEE'),
       ('ADMIN');


INSERT INTO accounts (login, password, first_name, last_name, account_number, account_state, debt, currency, access_level)
VALUES ('klient1', 'password?', 'Zdzisław', 'Kowalski', '123', 10, 0, 'PLN', 1),
       ('klient2', 'password?', 'Zdzisław2', 'Kowalski2', '1232', 10, 0, 'EUR', 1),
       ('klient3', 'password?', 'Zdzisław3', 'Kowalski4', '1233', 10, 0, 'PLN', 1),
       ('klient4', 'password?', 'Zdzisław4', 'Kowalski5', '1234', 10, 0, 'PLN', 1);

INSERT INTO accounts (login, password, first_name, last_name, access_level)
VALUES ('admin', 'password?', 'Zdzisław', 'Kowalski', 3),
       ('pracownik', 'password?', 'Zdzisław2', 'Kowalski2', 2);


INSERT INTO transactions ("from", "to", amount)
VALUES (1, 2, 10),
       (2, 3, 15),
       (2, 4, 5);
