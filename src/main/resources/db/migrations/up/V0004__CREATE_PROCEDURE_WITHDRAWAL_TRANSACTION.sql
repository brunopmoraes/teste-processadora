create or replace function sp_withdrawal_transaction
(
	credit_card_number varchar(16),
	amount decimal(10,2),
	out status varchar(2)
)
AS
$$
declare
	v_balance decimal(10,2);
begin
	
	select balance into v_balance from credit_card where number = credit_card_number for update;

	if (v_balance < amount) then
		status := '51';
		return;
	end if;
	
	update credit_card set balance = v_balance - amount where number = credit_card_number;

	status := '00';
end;
$$
LANGUAGE plpgsql;