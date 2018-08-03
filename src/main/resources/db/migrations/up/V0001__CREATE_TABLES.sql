CREATE TABLE credit_card (
    id UUID PRIMARY KEY,
    number BIGINT NOT NULL,
    approved_credit DECIMAL(10, 2) NOT NULL,
    balance DECIMAL(10,2) NOT NULL
);

CREATE TABLE transaction_credit_card(
    id UUID PRIMARY KEY,
    action VARCHAR(20) NOT NULL,
    authorization_code VARCHAR(6),
    code VARCHAR(20) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    credit_card_id UUID NOT NULL,
    CONSTRAINT fk_transaction_credit_card FOREIGN KEY (credit_card_id) REFERENCES credit_card(ID)
);