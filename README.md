[![Build Status](https://travis-ci.org/mosuke5/cnd-freelance-service.svg?branch=master)](https://travis-ci.org/mosuke5/cnd-freelance-service)

Freelancer4j freelancer service for Appmod Microservices Advanced course.

Implementation: Spring Boot, PostgreSQL

## How to develop on local device
```
// Start PostgreSQL with Docker
$ sudo docker run --name my-pg -e POSTGRES_PASSWORD=password -e POSTGRES_DB=freelancerdb_test -d -p 5432:5432 postgres:9.4

// Connect PostgreSQL and load data
$ sudo docker run -it --rm --link my-pg:db postgres:9.4 psql -h db -U postgres -d freelancerdb_test
postgres=# CREATE TABLE freelancer (freelancer_id character varying(255) PRIMARY KEY, first_name character varying(255), last_name character varying(255), email character varying(255), skills text[]);
postgres=# INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values ('1', 'Ken', 'Yasuda', 'ken.yasuda@example.com', '{ "ruby", "php", "mysql"}');
postgres=# INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values ('2', 'Tadashi', 'Komiya', 'tadashi.komiya@example.com', '{ "c#", "windows", "sqlserver"}');
postgres=# INSERT INTO freelancer (freelancer_id, first_name, last_name, email, skills) values ('3', 'Taro', 'Goto', 'taro.goto@example.com', '{ "ruby", "postgresql", "java"}');

// Test
$ mvn clean test
```

## Continuous Integration in Travis CI
This repository is integrated with Travis CI.
When you push to github, Travis CI will test automatically.

Current status is here.
https://travis-ci.org/mosuke5/cnd-freelance-service

## How to deploy to OpenShift 
```
$ export FREELANCER4J_PRJ=<your-ocp-project-name>

// Deploy PostgreSQL to OpenShift with persistent volume
// This include creating the table and loading sample data
$ oc process -f etc/freelancer-service-postgresql-persistent.yaml \
-p FREELANCER_DB_USERNAME=jboss \
-p FREELANCER_DB_PASSWORD=jboss \
-p FREELANCER_DB_NAME=freelancerdb | oc create -f - -n $FREELANCER4J_PRJ

// Create configmap for freelancer service application
$ oc create configmap freelancer-service \
--from-literal=spring.datasource.url=jdbc:postgresql://freelancer-postgresql:5432/freelancerdb \
--from-literal=spring.datasource.username=jboss \
--from-literal=spring.datasource.password=jboss \
--from-literal=spring.jpa.show-sql=true \
--from-literal=spring.jpa.hibernate.ddl-auto=update \
-n $FREELANCER4J_PRJ

// Deploy this service to OpenShift
$ mvn clean fabric8:deploy -Popenshift -Dfabric8.namespace=$FREELANCER4J_PRJ
```

## Functions
| Method | Endpoint |
:-----------|:------------|
 GET | /freelancers |
 GET | /freelancers/{freelancerId} |

 ```
$ export FREELANCER_URL=http://$(oc get route freelancer-service -n $FREELANCER4J_PRJ -o template --template='{{.spec.host}}')

$ curl -X GET "$FREELANCER_URL/freelancers"
[
  {
    "freelancerId": "1",
    "firstName": "Ken",
    "lastName": "Yasuda",
    "email": "ken.yasuda@example.com",
    "skills": [
      "ruby",
      "php",
      "mysql"
    ]
  },
  {
    "freelancerId": "2",
    "firstName": "Tadashi",
    "lastName": "Komiya",
    "email": "tadashi.komiya@example.com",
    "skills": [
      "c#",
      "windows",
      "sqlserver"
    ]
  },
  {
    "freelancerId": "3",
    "firstName": "Taro",
    "lastName": "Goto",
    "email": "taro.goto@example.com",
    "skills": [
      "ruby",
      "postgresql",
      "java"
    ]
  }
]


$ curl -X GET "$FREELANCER_URL/freelancers/1"
{
  "freelancerId": "1",
  "firstName": "Ken",
  "lastName": "Yasuda",
  "email": "ken.yasuda@example.com",
  "skills": [
    "ruby",
    "php",
    "mysql"
  ]
}
 ```