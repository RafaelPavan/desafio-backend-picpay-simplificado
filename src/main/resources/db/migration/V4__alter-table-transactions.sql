ALTER TABLE transactions RENAME COLUMN receiver_id TO receiver_email;

ALTER TABLE transactions ALTER COLUMN receiver_email TYPE VARCHAR(255);

ALTER TABLE public.users ADD CONSTRAINT email_unique UNIQUE (email);

ALTER TABLE public.transactions ADD CONSTRAINT fk_receiver_email FOREIGN KEY (receiver_email) REFERENCES public.users(email);
