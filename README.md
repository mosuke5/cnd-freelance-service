Coolstore Shopping Cart service for Appmod Microservices Advanced course.

Implementation: Spring Boot

sample data
```
CREATE TABLE freelancer (freelancer_id integer PRIMARY KEY, first_name character varying(255), last_name character varying(255), email character varying(255), skills text[]);
INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values (1, 'Ken', 'Yasuda', 'ken.yasuda@example.com', '{ "ruby", "php", "mysql"}');
INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values (2, 'Tadashi', 'Komiya', 'tadashi.komiya@example.com', '{ "c#", "windows", "sqlserver"}');
INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values (3, 'Taro', 'Goto', 'taro.goto@example.com', '{ "ruby", "postgresql", "java"}');
```
