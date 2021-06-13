INSERT INTO access_level (level)
VALUES ('CLIENT'),
       ('ADMIN');


INSERT INTO accounts (login, password, first_name, last_name, account_number, account_state, debt, currency, access_level, active)
VALUES ('klient1', '0885c94ed2e94369bafab182efc19c41624dd8f2b12b79fdfd1c4e8a740397f6335cd241a1713b030476a31ec049ef2c5ec0f8ea3baa22246815bb5ccc74f01c', 'Zdzisław', 'Kowalski', '123', 10, 0, 'PLN', 1, true),
       ('klient2', '0885c94ed2e94369bafab182efc19c41624dd8f2b12b79fdfd1c4e8a740397f6335cd241a1713b030476a31ec049ef2c5ec0f8ea3baa22246815bb5ccc74f01c', 'Zdzisław2', 'Kowalski2', '1232', 10, 0, 'EUR', 1, false),
       ('klient3', '0885c94ed2e94369bafab182efc19c41624dd8f2b12b79fdfd1c4e8a740397f6335cd241a1713b030476a31ec049ef2c5ec0f8ea3baa22246815bb5ccc74f01c', 'Zdzisław3', 'Kowalski4', '1233', 10, 0, 'PLN', 1, true),
       ('klient4', '0885c94ed2e94369bafab182efc19c41624dd8f2b12b79fdfd1c4e8a740397f6335cd241a1713b030476a31ec049ef2c5ec0f8ea3baa22246815bb5ccc74f01c', 'Zdzisław4', 'Kowalski5', '1234', 10, 0, 'PLN', 1, true);

INSERT INTO accounts (login, password, first_name, last_name, access_level, active)
VALUES ('admin', '0885c94ed2e94369bafab182efc19c41624dd8f2b12b79fdfd1c4e8a740397f6335cd241a1713b030476a31ec049ef2c5ec0f8ea3baa22246815bb5ccc74f01c', 'Zdzisław', 'Kowalski', 2, true);

INSERT INTO transactions (from_id, from_currency, to_id, to_currency, amount, rate, is_loan)
VALUES (1, 'PLN', 2, 'EUR', 10, 2.50, false),
       (2, 'PLN', 3, 'EUR', 15, 3.25, false),
       (2, 'EUR', 4, 'PLN', 5, 0.9, false);
