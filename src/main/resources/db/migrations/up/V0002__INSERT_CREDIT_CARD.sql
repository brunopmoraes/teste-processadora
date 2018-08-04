create extension "uuid-ossp";

insert into credit_card values(uuid_generate_v4(), '1234567890982312', 1000, 1000);
insert into credit_card values(uuid_generate_v4(), '1234123412341234', 1000, 1000);