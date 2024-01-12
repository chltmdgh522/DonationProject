create table member
(
    id               varchar(255)                          not null
        primary key,
    email            varchar(255)                          not null,
    login_id         varchar(16)                           not null,
    name             varchar(255)                          not null,
    password         varchar(255)                          not null,
    description      varchar(255)                          null,
    point            int                                   null,
    gender           varchar(255)                          not null,
    session          varchar(255)                          null,
    total_give_point int         default 0                 null,
    role             varchar(10) default 'X'               null,
    date             datetime    default CURRENT_TIMESTAMP null,
    profile          text                                  null
);
----------------------

create table board
(
    id           int auto_increment
        primary key,
    board_id     varchar(255)             not null,
    title        varchar(255)             not null,
    content      text                     null,
    member_name  varchar(255)             not null,
    member_id    varchar(255)             not null,
    view_count   int          default 0   null,
    date         varchar(255)             null,
    modify       varchar(255) default 'X' null,
    board_type   tinyint      default 0   not null,
    give_point   int          default 0   null,
    board_image  text                     null,
    option_point int          default 0   null,
    constraint board_ibfk_1
        foreign key (member_id) references member (id)
            on delete cascade
);

create index member_id
    on board (member_id);

----------------------

create table comment
(
    id          int auto_increment
        primary key,
    content     varchar(255)                       not null,
    member_name varchar(255)                       null,
    member_id   varchar(255)                       not null,
    board_id    int                                null,
    date        datetime default CURRENT_TIMESTAMP null,
    constraint comment_ibfk_1
        foreign key (member_id) references member (id)
            on delete cascade,
    constraint comment_ibfk_2
        foreign key (board_id) references board (id)
            on delete cascade
);
----------------------

create table give
(
    id         int auto_increment
        primary key,
    name       varchar(255)                       null,
    give_point int                                null,
    email      varchar(255)                       null,
    give_text  varchar(255)                       null,
    board_id   int                                null,
    member_id  varchar(255)                       null,
    date       datetime default CURRENT_TIMESTAMP null,
    constraint give_ibfk_1
        foreign key (board_id) references board (id)
            on delete cascade,
    constraint give_ibfk_2
        foreign key (member_id) references member (id)
            on delete cascade
);

create index boardId
    on give (board_id);

create index memberId
    on give (member_id);


--------------

create table visitant
(
    id    int not null
        primary key,
    today int not null,
    total int null
);