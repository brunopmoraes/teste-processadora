CREATE TABLE credit_card (
    id UUID PRIMARY KEY,
    number VARCHAR(16) NOT NULL,
    approved_credit DECIMAL(10, 2) NOT NULL,
    balance DECIMAL(10,2) NOT NULL,
    UNIQUE (number),
    CHECK (balance >= 0)
);

CREATE TABLE transaction_credit_card(
    id UUID PRIMARY KEY,
    action VARCHAR(20) NOT NULL,
    authorization_code VARCHAR(6),
    code VARCHAR(20) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    credit_card_id UUID,
    UNIQUE (authorization_code),
    CONSTRAINT fk_transaction_credit_card FOREIGN KEY (credit_card_id) REFERENCES credit_card(ID)
);