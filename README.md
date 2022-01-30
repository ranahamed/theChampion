# theChampion

## Requirements
- java 11 
- MYSQL
- Springboot

## How to run
- git clone https://github.com/ranahamed/theChampion.git
- change spring.datasource.username value in application.properties file
- change spring.datasource.password value in application.properties file
- Navigate to project location
- run command: mvn clean install
- run command: mvn spring-boot:run
- server port is 8090

## Run Test files
- run command: mvn test

## Code Architecture
- **Code packages**
  - controllers
  - dto
  - exceptions
  - models
  - repositories
  - services
  - utils
  
- **Test packages**
  - controllers
  
- **Database**
  - SQL create table statements exist in resources/tables.sql

## Requests
- Submit a participant request
  - **POST:** http://localhost:8090/league/create-participant 
  - **Body:** {
    "firstName":"Rana",
    "lastName":"Hamed",
    "age":28,
    "phoneNo":"0123456789"}
  - **Result:** new participant is created
  
- Get a list of all participants
  - **GET:** http://localhost:8090/league/participants
  - **Result:** [
    {
        "id": 1,
        "firstName": "Rana",
        "lastName": "Hamed",
        "age": 28,
        "phoneNo": "0123456789"
    },
    {
        "id": 2,
        "firstName": "sara",
        "lastName": "Ahmed",
        "age": 28,
        "phoneNo": "0123456789"
    },
    {
        "id": 3,
        "firstName": "jana",
        "lastName": "Ahmed",
        "age": 28,
        "phoneNo": "0123456789"
    },
    {
        "id": 4,
        "firstName": "tala",
        "lastName": "Ahmed",
        "age": 28,
        "phoneNo": "0123456789"
    }
]
- Group randomly participants into (n) groups
  - **GET:** http://localhost:8090/league/group-participants/2
  - **Result:** partcicipants grouping done

- Get a list of all automatically created the first-round matches
  - **GET:** http://localhost:8090/match/first-round
  - **Result:** [
    {
        "id": 1,
        "firstGroup": {
            "id": 1,
            "groupName": null,
            "participantList": [
                {
                    "id": 1,
                    "firstName": "Rana",
                    "lastName": "Hamed",
                    "age": 28,
                    "phoneNo": "0123456789"
                },
                {
                    "id": 2,
                    "firstName": "sara",
                    "lastName": "Ahmed",
                    "age": 28,
                    "phoneNo": "0123456789"
                }
            ]
        },
        "secondGroup": {
            "id": 2,
            "groupName": null,
            "participantList": [
                {
                    "id": 3,
                    "firstName": "jana",
                    "lastName": "Ahmed",
                    "age": 28,
                    "phoneNo": "0123456789"
                },
                {
                    "id": 4,
                    "firstName": "tala",
                    "lastName": "Ahmed",
                    "age": 28,
                    "phoneNo": "0123456789"
                }
            ]
        },
        "winner": null,
        "firstGroupScore": null,
        "secondGroupScore": null
    }
]
- Update match winner and results
  - **PUT:** http://localhost:8090/match/update-result
  - **Body:** {
    "id":1,
    "firstGroupScore":30,
    "secondGroupScore":20}
  - **Result:** {
    "id": 1,
    "firstGroup": {
        "id": 1,
        "groupName": null,
        "participantList": [
            {
                "id": 1,
                "firstName": "Rana",
                "lastName": "Hamed",
                "age": 28,
                "phoneNo": "0123456789"
            },
            {
                "id": 2,
                "firstName": "sara",
                "lastName": "Ahmed",
                "age": 28,
                "phoneNo": "0123456789"
            }
        ]
    },
    "secondGroup": {
        "id": 2,
        "groupName": null,
        "participantList": [
            {
                "id": 3,
                "firstName": "jana",
                "lastName": "Ahmed",
                "age": 28,
                "phoneNo": "0123456789"
            },
            {
                "id": 4,
                "firstName": "tala",
                "lastName": "Ahmed",
                "age": 28,
                "phoneNo": "0123456789"
            }
        ]
    },
    "winner": {
        "id": 1,
        "groupName": null,
        "participantList": [
            {
                "id": 1,
                "firstName": "Rana",
                "lastName": "Hamed",
                "age": 28,
                "phoneNo": "0123456789"
            },
            {
                "id": 2,
                "firstName": "sara",
                "lastName": "Ahmed",
                "age": 28,
                "phoneNo": "0123456789"
            }
        ]
    },
    "firstGroupScore": 30,
    "secondGroupScore": 20
}
- Close round
  - **GET:** http://localhost:8090/rounds/close-round/1
  - **Result:** round is closed
  
  
























