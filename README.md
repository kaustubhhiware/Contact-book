# Contact-book
java application for maintaining contacts

HOW TO START THIS PROJECT:

<applet>
First go to internet explorer(Firefox works sometimes , and sometimes it doesn't).Set the option to allow scripting of java applets to Enable.You may need to restart your computer.
Alternately , check here :http://superuser.com/questions/487471/how-to-make-internet-explorer-support-java-applets
After that , go to Control Panel->
Java->Security and add the Contact.htm (in bin folder) to the exception list in the format : file:///<Complete workspace location or where software package is saved>/Contact_Interface/bin/Contact.htm
Then , launch Contact.htm in bin folder in internet explorer .You can now proceed to use it as the user wishes.
Screenshots have been added for the working applet in a separate folder.

<Application>
Directly run Phone.jar if it runs .In ubuntu , you might have to write this in terminal
cd <location>
sudo chmod a+x Phone.jar
If none of this works , start it in the default way by running in Eclipse/NetBeans


USER INSTRUCTIONS:

<Application>
>>visual.java is the main file . Each button when clicked , opens a new frame .

>>createContact.java is the menu for type of contact.Each button has its separate frame .DateChooser is used here.

>>SearchContact.java is similar to search option in phones.
>>The file handling was not working in my previous submission. I have ensured it works this time around.

<Applet>
>>The complete code of application is used , frames are replaced by panels , with the exception of MessageDialogs.Images have been removed as applet(which runs in browser ) cannot handle files on a local system . (^^See how to start this project).