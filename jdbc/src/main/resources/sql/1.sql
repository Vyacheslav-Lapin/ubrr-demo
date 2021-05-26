set mode postgresql;

create table Person
(
    id         int primary key auto_increment,
    first_name varchar(255) not null,
    last_name  varchar(255),
    permission boolean default false,
    dob        date,
    email      varchar(255) not null,
    password   varchar(255) not null,
    address    varchar(255),
    telephone  varchar(15)
);

insert into Person (first_name, last_name, permission, dob, email, password, address, telephone)
values ('Jose', 'Eglesias', true, '1980-06-15', 'Jose_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');
