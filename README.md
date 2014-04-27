timetagger
==========

Keeps track of where you're spending your time

Currently uses JETTY server to host page and store state. Most of the actual logic is in the page Javascript.

Page is rendered with angular and it initializes and stores it's state by transfering it as JSON.


Todo: 
•	add automatic idle category
•	add better synchronization if using multiple clients (website accessed by different devices.)
•	add hierarchal categories
•	android app
•	multiuser
•	authentication
•	hooks to automatically log certain events like going to certain websites or running certain programs?
•	PHP/normal web hosting backend
•	Documentation
•	Pretty up UI
•	Add more precise event logging (start and stop of each event)
•	Add visualizations for more precise event logging (average commute time for each day of week/month of year, mean median mode etc.)

