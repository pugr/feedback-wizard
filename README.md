# feedback-wizard

This is a simple web application wizard used to collect feedback from attendees of training courses.

The application consists of a main page where user can start entering feedback. On this page the user can also see the list of already sent feedbacks as well as various messages after certain actions are performed, such as storing a new feedback in the database feedback cancellation.
The feedback collection process is divided into three pages - first two pages are used to collect actual feedback, the third page is used to review the feedback and to store it in database.


Running the application with Jetty plugin
=========================================

Please be sure that you have installed Maven and its bin directory is placed in your PATH variable.
To download maven please visit site http://maven.apache.org

To run this project in Jetty (this will start Jetty running on port 8080 and serving the project. Jetty will continue to run until the plugin is explicitly stopped, for example, by a <CTRL-C>):
  mvn jetty:run
  
  The main page of the application is available under URL "/feedback/welcome".

To make a WAR:
  mvn clean package

To run all tests:
  mvn clean test
