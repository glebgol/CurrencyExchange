CREATE TABLE IF NOT EXISTS currencies
(
    id INTEGER
        CONSTRAINT PK_currencies_id
            PRIMARY KEY AUTOINCREMENT,
    code VARCHAR(3)
        CONSTRAINT UQ_currencies_code
            UNIQUE NOT NULL,
    full_name NVARCHAR(45)
        CONSTRAINT UQ_currencies_full_name
            UNIQUE NOT NULL,
    sign VARCHAR(2) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_id
ON currencies(id);

CREATE INDEX IF NOT EXISTS idx_code
ON currencies(code);


CREATE TABLE IF NOT EXISTS exchange_rates
(
    id INTEGER
        CONSTRAINT PK_exchange_rates_id
            PRIMARY KEY AUTOINCREMENT,
    base_currency_id INTEGER NOT NULL
        CONSTRAINT FK_exchange_rates_base_currency_id
            REFERENCES currencies(id) ON DELETE CASCADE,
    target_currency_id INTEGER NOT NULL
        CONSTRAINT FK_exchange_rates_target_currency_id
            REFERENCES currencies(id) ON DELETE CASCADE,
    rate DECIMAL(6) NOT NULL,

    CONSTRAINT CK_not_equals_currencies CHECK (base_currency_id != target_currency_id)
);

CREATE INDEX IF NOT EXISTS idx_id
ON exchange_rates(id);

CREATE UNIQUE INDEX IF NOT EXISTS idx_unique_currencies
ON exchange_rates(base_currency_id, target_currency_id);
