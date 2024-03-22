CREATE TABLE IF NOT EXISTS transactions
(
    id UUID NOT NULL,
    amount numeric(38, 2),
    sender_id UUID NULL,
    receiver_id UUID NULL,
    transaction_time timestamp NULL,
    CONSTRAINT sender_fkey FOREIGN KEY (sender_id) REFERENCES users(id),
    CONSTRAINT receiver_fkey FOREIGN KEY (receiver_id) REFERENCES users(id),
    CONSTRAINT transactions_pkey PRIMARY KEY (id)
)