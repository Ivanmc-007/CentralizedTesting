CREATE TABLE choice (
    id bigserial NOT NULL,
    accept boolean NOT NULL,
    date_creation date NOT NULL,
    my_user_id int8 NOT NULL,
    permission_id int8,
    subject_id int8 NOT NULL,
    university_id int8 NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE my_user_role (
    my_user_id int8 not null,
    roles varchar(255)
);

CREATE TABLE my_user (
    id bigserial NOT NULL,
    activation_code varchar(255),
    active boolean NOT NULL,
    email varchar(255),
    login varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    passport_id int8,
    PRIMARY KEY (id)
);

CREATE TABLE passport (
    id bigserial NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    number varchar(255) NOT NULL,
    series varchar(255) NOT NULL,
    primary key (id)
);


CREATE TABLE permission (
    id bigserial not null,
    address varchar(255),
    date_start_test timestamp,
    language varchar(255),
    way_information varchar(1024),
    choice_id int8,
    PRIMARY KEY (id)
);

CREATE TABLE subject (
    id bigserial NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE subject_university (
    university_id int8 NOT NULL,
    subject_id int8 NOT NULL,
    PRIMARY KEY (subject_id, university_id)
);

CREATE TABLE university (
    id bigserial NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

alter table if exists choice add constraint choice_my_user_fk foreign key (my_user_id) references my_user;
alter table if exists choice add constraint choice_permission_fk foreign key (permission_id) references permission;
alter table if exists choice add constraint choice_subject_fk foreign key (subject_id) references subject;
alter table if exists choice add constraint choice_university_fk foreign key (university_id) references university;
alter table if exists my_user_role add constraint FKg8v6djpl2s8vl2wqnol1c50ry foreign key (my_user_id) references my_user;
alter table if exists my_user add constraint my_user_passport_fk foreign key (passport_id) references passport;
alter table if exists permission add constraint permission_choice_fk foreign key (choice_id) references choice;
alter table if exists subject_university add constraint subject_university_subject_fk foreign key (subject_id) references subject;
alter table if exists subject_university add constraint subject_university_university_fk foreign key (university_id) references university;
