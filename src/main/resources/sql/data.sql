insert into USERS (ID, PASSWORD, USERNAME, ENABLED)
VALUES (0, 'free', 'UnkNoWNuSER', true);
insert into USERS (ID, PASSWORD, USERNAME, ENABLED)
VALUES (1, 'adminas1', 'admin', true);
insert into USERS (ID, PASSWORD, USERNAME, ENABLED)
VALUES (2, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'user', true);
insert into USERS (ID, PASSWORD, USERNAME, ENABLED)
VALUES (3, '$2a$10$TBwMwb.4G19yro/vqD0r.uUYVOYb9hMlGjpMGIpf.V9MSkuJ1wjEa', 'adminas', true);


insert into AUTHORITIES (ID, AUTHORITY)
VALUES (0, 'ROLE_ANONYMOUS');
insert into AUTHORITIES (ID, AUTHORITY)
VALUES (1, 'ROLE_ADMIN');
insert into AUTHORITIES (ID, AUTHORITY)
VALUES (2, 'ROLE_USER');


insert into USERS_AUTHORITIES (USER_ID, AUTHORITY_ID)
VALUES (1, 1);
insert into USERS_AUTHORITIES (USER_ID, AUTHORITY_ID)
VALUES (1, 2);
insert into USERS_AUTHORITIES (USER_ID, AUTHORITY_ID)
VALUES (2, 2);


insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (1, 'Loreem ipsum — (trump. lipsum) tekstas naudojamas spaudos ir grafinio dizaino industrijoje jau nuo XVI amžiaus pradžios. Jis naudojamas parodyti grafinio pristatymo elementus, tokius kaip taipografija, dizainas ar šriftas.',
        '2022-05-25', 'Lorem ipsum', 1);
insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (2, 'Pavadinimas kilęs iš „gėrio ir blogio“ teksto eilučių. Originali eilutė prasidėjo: „Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit“. Kada tai įgavo dabartinę formą nežinoma, tai galėjo įvykti nuo 1500 pradžios iki net 1960.',
        '2022-05-26', '„Lorem ipsum“ kilmė', 1);
insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (3, 'Lorem ipsum - tai fiktyvus tekstas naudojamas spaudos ir grafinio dizaino pasaulyje jau nuo XVI a. pradžios. Lorem Ipsum tapo standartiniu fiktyviu tekstu, kai nežinomas spaustuvininkas atsitiktine tvarka išdėliojo raides atspaudų prese ir tokiu būdu sukūrė raidžių egzempliorių. Šis tekstas išliko beveik nepasikeitęs ne tik penkis amžius, bet ir įžengė i kopiuterinio grafinio dizaino laikus. Jis išpopuliarėjo XX a. šeštajame dešimtmetyje, kai buvo išleisti Letraset lapai su Lorem Ipsum ištraukomis, o vėliau -leidybinė sistema AldusPageMaker, kurioje buvo ir Lorem Ipsum versija.',
        '2022-06-10', 'Kas yra Lorem Ipsum?', 1);
insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (4, 'Jau seniai žinoma, kad vertinant dizainą ir kompoziciją, blaško skaitomas tekstas. Tad Lorem Ipsum naudojamas tam, kad daugiau ar mažiau normalizuotų raidžių paskirstymą vietoje paprasto dubliavimo: „Tekstas tekstas tekstas tekstas...“. Daugelis leidybinių sistemų programų, o taip pat ir Hiperteksto kalbos redaktoriai naudoja Lorem Ipsum kaip demonstracinę numatyto teksto versiją, todel žodžių „Lorem Ipsum“ internetinė paieška parodo daug tinklalapių kurie yra dar besikuriantys. Egzistuoja ne mažai įvairių Lorem Ipsum versijų. Kai kurios iš jų buvo sukurtos per klaidą, kitos - tyčia (pvz. jumoristinės).',
        '2022-06-30', 'Kam jis naudojamas?', 1);
insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (5, 'Yra daug įvarių Lorem Ipsum variantų, bet daugelis jų buvo vienokiu ar kitokiu būdu modifikuoti, pvz. jumoristiniai intarpai arba žodžiai, nė kiek nepanasūs i lotynų kalbą. Jeigu Jums reikia Lorem Ipsum teksto rimtam projektui, Jūs tikriausiai nenorėtumėte viduryje eilutės kokio nors trikdančio išsireiškimo. Visi kiti Lorem Ipsum teksto eilučių generatoriai dažniausiai tik kartoja tą patį tekstą, kol užpildo reikiamus tarpus. Todėl čia siūlomas teksto eilučių generatorius yra vienintelis tikras Lorem Ipsum teksto generatorius. Jame naudojamas žodynas iš daugiau nei 200 lotynų kalbos žodžių, o taip pat keletas ištisų sakinių modelių. Taip surinktas Lorem Ipsum teksto variantas atrodo tikroviškai, nesikartoja, ir jame nėra „neįmanomų“ žodžių.',
        '2022-07-01', 'Kur jį gauti?', 1);
insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (6, 'Daug kas galvoja, kad Lorem Ipsum - tai vien tik atsitiktinai surinktas tekstas, bet taip nėra. Jo šaknis galima rasti klasikiniame Lotynų kalba parašytame kūrinyje, išleistame 45 pr. m. e., t.y. prieš du tūkstančius metų. Richard McClintock, lotynų kalbos profesorius Hampden-Sydney (JAV) koledže, išrinko vieną iš neaiškesnių žodžių iš Lorem Ipsum ištraukos - „consectetur“, ir ėmėsi ieškoti jo klasikinėje lotynų kalbos literatūroje. Tokiu būdu jis rado neabejotiną šio žodžio ir Lorem Ipsum šaltinį knygos „de Finibus Bonorum et Malorum“ (Apie gėrio ir blogio ribas) 1.10.32 ir 1.10.33 pastraipose. Kūrinio autorius – Ciceronas, parašęs jį 45 pr. m. e. Šis etikos teorijos traktatas buvo labai populiarus Renesanso laikais. Pirmoji Lorem Ipsum teksto eilutė, „Lorem ipsum dolor sit amet..“ sukurta remiantis 1.10.32 pastraipa.',
        '2022-07-23', 'Iš kur jis atsirado?', 1);



insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (1, 'labai geras straipsnis ', current_timestamp(), 1, 0);
insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (2, 'Is kur tokia informacija????', current_timestamp(), 1, 2);
insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (3, 'NEITIKETINA!', current_timestamp(), 1, 0);
insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (4, 'MELAGYSTESSSSS!', current_timestamp(), 2, 0);
insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (5, 'Saunu!', current_timestamp(), 2, 1);
insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (6, 'Negaliu patiketi', current_timestamp(), 1, 0);



