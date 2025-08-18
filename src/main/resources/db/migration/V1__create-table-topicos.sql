create table topicos(

    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensaje varchar(255) not null,
    fecha datetime not null,
    status tinyint not null,
    autor varchar(100) not null,
    curso varchar(255) not null,

    primary key(id)
);