# Eurovision song contest voting system

In this exercise, you will need to implement a small system to count the results of the
Eurovision Song Contest.
The Eurovision Song Contest is an annual international song competition. Each
participating country submits an original song to be performed on live television. During
the contest people from all over the world vote for their favorite songs.
In this exercise, we will need to implement a system supporting the voting process.
Each time a person votes for his favorite song via SMS, mobile app or any other way,
our system receives a REST call.

`POST /votes/2020{ "countryFrom": "Netherlands", "votedFor": "Belgium"}`

The above example means that a person from The Netherlands voted for the song from
Belgium.
The system aggregates these votes and able to report 1st, 2nd and the 3rd place for
each year:

`GET /votes/2018{ "first": "Israel", "second": "Cyprus", "third": "Austria"}GET /votes/2
019{ "first": "Netherlands", "second": "Italy", "third": "Russia"}`

Rating is calculated as simple as a total number of votes for a country. The country that
had the most votes wins.
In addition to the above API, we would like to find out the top three favorite songs for
each country:

`GET /votes/2019/Netherlands{ "first": "Italy", "second": "Sweden", "third": "Russia"}`

The above takes in account votes coming from the Netherland only as if other countries
were not participating in the voting process.

## Install

`docker-compose up`






# eurovision
