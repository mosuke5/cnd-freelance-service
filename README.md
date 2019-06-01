Freelancer4j freelancer service for Appmod Microservices Advanced course.

Implementation: Spring Boot, PostgreSQL

```
$ sudo docker run --name freelancerdb-test -e POSTGRES_PASSWORD=password -e POSTGRES_DB=freelancerdb-test -d -p 5433:5432 postgres:9.4

$ sudo docker run -it --rm --link freelancerdb-test:db postgres:9.4 psql -h db -U postgres
postgres=# CREATE TABLE freelancer (freelancer_id character varying(255) PRIMARY KEY, first_name character varying(255), last_name character varying(255), email character varying(255), skills text[]);
postgres=# INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values ('1', 'Ken', 'Yasuda', 'ken.yasuda@example.com', '{ "ruby", "php", "mysql"}');
postgres=# INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values ('2', 'Tadashi', 'Komiya', 'tadashi.komiya@example.com', '{ "c#", "windows", "sqlserver"}');
postgres=# INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values ('3', 'Taro', 'Goto', 'taro.goto@example.com', '{ "ruby", "postgresql", "java"}');

$ mvn clean test
```

```
$ export FREELANCER4J_PRJ=<your-ocp-project-name>
$ oc process -f etc/freelancer-service-postgresql-persistent.yaml \
-p FREELANCER_DB_USERNAME=jboss \
-p FREELANCER_DB_PASSWORD=jboss \
-p FREELANCER_DB_NAME=freelancerdb | oc create -f - -n $FREELANCER4J_PRJ

$ oc create configmap freelancer-service \
--from-literal=spring.datasource.url=jdbc:postgresql://freelancer-postgresql:5432/freelancerdb \
--from-literal=spring.datasource.username=jboss \
--from-literal=spring.datasource.password=jboss \
--from-literal=spring.jpa.show-sql=true \
--from-literal=spring.jpa.hibernate.ddl-auto=update \
-n $FREELANCER4J_PRJ
```