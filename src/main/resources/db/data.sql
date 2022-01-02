INSERT INTO BOOK (id, guid, isbn10, isbn13, language, name, published_date)
Values (1, '93945629-734B-475E-99CE-6AA7AFA43259', '0486820394', '9780486820392', 'English', 'The Mysterious Island',
        'May 26, 2015'),
       (2, '21945629-734B-475E-99CE-6AA7AFA43259', '0307785165', '9780307785169', 'English',
        'From the Earth to the Moon', 'December 23, 2021'),
       (3, '89945629-734B-475E-99CE-6AAD4FA43259', '9781853260', '9781853260315', 'French',
        '20,000 Leagues Under the Sea', 'December 30, 1998'),
       (4, '7894629-734B-475E-99CE-6AAD4FA43259', '1503215156', '978-1503215153', 'English',
        'Around the World in 80 Days', 'November 6, 2018');

INSERT INTO AUTHOR (id, about_author, last_name, name)
VALUES (1, 'Jules Verne (1828-1905) was a French author best known for his tales of adventure, including ' ||
           'Twenty Thousand Leagues under the Sea, Journey to the Center of the Earth, and Around the World ' ||
           'in Eighty Days. A true visionary, Verne foresaw the skyscraper, the submarine, and the airplane, ' ||
           'among many other inventions, and is now',
        'Verne', 'Jules');

INSERT INTO BOOK_AUTHOR (book_id, author_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1);

INSERT INTO CATEGORY (id, name)
VALUES (1, 'Action and Adventure'),
       (2, 'Classics'),
       (3, 'Comic Book or Graphic Novel'),
       (4, 'Detective and Mystery'),
       (5, 'Fantasy'),
       (6, 'Historical Fiction'),
       (7, 'Horror'),
       (8, 'Literary Fiction');

INSERT INTO BOOK_CATEGORY (book_id, category_id)
VALUES (1, 1),
       (1, 2),
       (1, 5),
       (2, 1),
       (2, 2),
       (2, 5),
       (3, 1),
       (4, 1);

INSERT INTO USER (id, last_name, username)
VALUES (1, 'Barrow', 'John'),
       (2, 'Last', 'Jack'),
       (3, 'Auto', 'Tom');

INSERT INTO RESERVED (id, due_date, reserve_date, reserve_days, return_date, book_id, user_id)
VALUES (1, '2022-01-31', '2021-12-30', 32, null, 3, 1),
       (2, '2022-01-31', '2021-12-30', 32, null, 2, 1),
       (3, '2022-01-31', '2021-12-30', 32, null, 1, 1);


