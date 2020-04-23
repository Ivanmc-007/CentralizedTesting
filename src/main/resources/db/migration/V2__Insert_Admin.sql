insert into my_user (active, login, password,email)
values (true, 'admin', '1','ivanmc-007@mail.ru');

insert into my_user_role (my_user_id, roles)
values (1,'USER'), (1,'ADMIN');