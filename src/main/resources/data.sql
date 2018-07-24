insert into client values(1,'VIVA', 'Natalia@gmail.com', 'Natalia','Adamchuk', '123-456-789');
insert into client values(2,'Group', 'Oleksandr@gmail.com', 'Oleksandr','Iaremii', '987-654-321');

insert into project values(1, 'some text', 'CRM', 2000);
insert into project values(2, 'some text', 'Java', 3000);
insert into project values(3, 'some text', 'DashBoards', 4000);

insert into commission values(1, 15000, 'CRM', sysdate(),1,1);
insert into commission values(2, 11000, 'Boards', sysdate(),1,3);
insert into commission values(3, 1000, 'Java', sysdate(),2,2);


