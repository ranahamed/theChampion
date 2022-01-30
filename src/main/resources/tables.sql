 create table matches (
       id integer not null auto_increment,
        first_group_score integer,
        second_group_score integer,
        first_group_id integer,
        round_id integer,
        second_group_id integer,
        winner_group_id integer,
        primary key (id)
    )

    create table participants (
       id integer not null auto_increment,
        age integer,
        first_name varchar(255),
        last_name varchar(255),
        phone_no varchar(255),
        group_id integer,
        primary key (id)
    )

    create table participants_group (
       id integer not null auto_increment,
        group_name varchar(32),
        primary key (id)
    )

    create table rounds (
       id integer not null auto_increment,
        is_open boolean default false,
        round_name varchar(32),
        primary key (id)
    )


    alter table participants_group
       add constraint UK_edlsoum9tm797u2ri7bhasbi2 unique (group_name)


    alter table rounds
       add constraint UK_5odxeoxv8i2gd3kn8nfs6htqj unique (round_name)


    alter table matches
       add constraint FK9excdn5ktvs99i2j9rqs8k4qe
       foreign key (first_group_id)
       references participants_group (id)


    alter table matches
       add constraint FKjqepgamspeqo5q36vffq2h593
       foreign key (round_id)
       references rounds (id)

    alter table matches
       add constraint FKj9j1t4nxe96m16nbb2ytvvpt3
       foreign key (second_group_id)
       references participants_group (id)


    alter table matches
       add constraint FKg62ru47we4ox3qwnp8picr4dc
       foreign key (winner_group_id)
       references participants_group (id)


    alter table participants
       add constraint FK6ilimhq4vpvevy9fj7kpdjl0u
       foreign key (group_id)
       references participants_group (id)