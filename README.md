# sctest
Standard Chartered Programming Test by Ralph Hu

It is a JVM based application. The output is a jar compiled for Java8 using only Java Core libraries.

### Usage
The application is kind of command line based application.

It waits for user's input and generate output.

Currently only the following commands are supported

* HELP

* QUIT

* DISPLAY

* PUBLISH

Commands can be typed in bother upper case and lower case.

To, use socket mode, just use

    startSocketServer()
    
in the main function.

###### HELP

Print the help information

###### QUIT

Quit the currently running application

###### BYE

Only used in socket mode to close a connection to the server

###### DISPLAY

Display instruments that have already been published

###### PUBLISH

Publish new instruments

Currently, only LME and PRIME type instruments could be published

To publish a LME instrument, please use the following command:

    publish lme PB_03_2018|15-03-2018|17-03-2018|LME_PB|Lead 13 March 2018
    
To publish a PRIME instrument, please use the following command:

    publish prime PRIME_03_2018|15-03-2018|17-03-2018|LME_PB|PB_03_2018|Lead 13 March 2018
    

### Comment

Since the requirement does not quite describe everything, the implementation of story A and B might not satisfy all the actual requirement of the interviewer. And this might be discussed during the f2f interview if I could pass the first round.

