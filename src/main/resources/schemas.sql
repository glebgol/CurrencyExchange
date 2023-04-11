CREATE TABLE IF NOT EXISTS currencies
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code VARCHAR(3) UNIQUE NOT NULL,
    full_name NVARCHAR(45) UNIQUE NOT NULL,
    sign VARCHAR(2) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_id
ON currencies(id);

CREATE INDEX IF NOT EXISTS idx_code
ON currencies(code);


CREATE TABLE IF NOT EXISTS exchange_rates
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    base_currency_id INTEGER NOT NULL REFERENCES currencies(id) ON DELETE CASCADE,
    target_currency_id INTEGER NOT NULL REFERENCES currencies(id) ON DELETE CASCADE,
    rate DECIMAL(6) NOT NULL,
    CONSTRAINT unique_currencies UNIQUE (base_currency_id, target_currency_id)
);

CREATE INDEX IF NOT EXISTS idx_id
ON exchange_rates(id);

CREATE INDEX IF NOT EXISTS idx_currencies
ON exchange_rates(base_currency_id, target_currency_id);