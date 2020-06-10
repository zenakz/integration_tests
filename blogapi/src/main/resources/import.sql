--this script initiates db for h2 db (used in test profile)
insert into user (account_status, email, first_name, last_name) values ('CONFIRMED', 'john@domain.com', 'John', 'Steward')
insert into user (account_status, email, first_name) values ('NEW', 'brian@domain.com', 'Brian')
insert into user (account_status, email, first_name, last_name) values ('CONFIRMED', 'test@domain.com', 'Stefan', 'Confirmed')
insert into user (account_status, email, first_name, last_name) values ('REMOVED', 'deleted@domain.com', 'Adam', 'Adma')

insert into blog_post ( entry, user_id) values ('Test Blog post', 1)
