#
#                       _       _                     _
#    ___  ___ _ __ __ _| |_ ___| |__  _ __   __ _  __| |
#   / __|/ __| '__/ _` | __/ __| '_ \| '_ \ / _` |/ _` |
#   \__ \ (__| | | (_| | || (__| | | | |_) | (_| | (_| |
#   |___/\___|_|  \__,_|\__\___|_| |_| .__/ \__,_|\__,_|
#                                    |_|
#   v1.0.0
#   YourOtherLeft software

Scratchpad is a sample Spring Boot application that implements a REST API.


PREREQUISITES
=============

Make sure you have a Java 8 JRE installed.


HOW DO I RUN SCRATCHPAD?
========================

1.  Open a command prompt or terminal window.
2.  Navigate to the scratchpad root directory.  Here, you'll find a file whose name is scratchpad-[version].war.
3.  Execute this command:

    $ java -jar scratchpad-[version].war

    This application runs on port 80 by default, but if you want to choose your own port, supply it as a runtime argument.  For example:

    $ java -jar -Dserver.port=8080 scratchpad-[version].war

4.  Using a browser or your favorite REST client tool (e.g., Postman), issue a request to this endpoint.  If you get an "OK" response, then you're good to go.

    http://localhost:[port]/api/ping