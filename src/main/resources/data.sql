insert into Tenant (id, name, weekly_rent_amount, paid_to_date, credit) values(0, 'Bill', 300, '2016-12-06 00:00:00', 0)
insert into Tenant (id, name, weekly_rent_amount, paid_to_date, credit) values(1, 'Mary', 350, '2016-12-06 00:00:00', 0)
insert into Tenant (id, name, weekly_rent_amount, paid_to_date, credit) values(2, 'Someone else', 300, '2016-12-06 00:00:00', 0)

COMMIT

insert into Receipt (id, amount, tenant_id, created_date) values(0, 300, 0, '2016-11-06 00:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(1, 300, 0, '2016-11-06 00:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(2, 300, 0, '2016-11-06 00:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(3, 300, 0, '2016-11-06 00:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(4, 300, 0, '2016-11-06 00:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(5, 300, 1, '2016-12-06 08:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(6, 300, 1, '2016-12-06 09:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(7, 300, 1, '2016-12-06 10:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(8, 300, 1, '2016-12-06 11:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(9, 300, 1, '2016-12-06 12:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(10, 300, 2, '2016-12-06 10:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(11, 300, 2, '2016-12-06 11:00:00')
insert into Receipt (id, amount, tenant_id, created_date) values(12, 300, 2, '2016-12-06 12:00:00')