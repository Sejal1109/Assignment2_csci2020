Collaborators:
Sejal Shingal - 100738147
Alexander Giannoulis - 100748198

This program was created as a file sharing system. The file sharing system is operated via a UI. On the left hand side 
are user files which can be uploaded to the server, and on the right are files that can be downloaded from the server. 

Some improvements implemented are instead of getting the shared folder path from command line arguments we are using 
a directory chooser to make it more accessible. We also added two extra buttons in order to open the selected file. 
The open local button will open a file from your local client folder, and the open server file button will open a 
button from the server folder. 

Step by step instructions on how to run it:

1. Clone the project into your C drive if you don’t use C drive, you will have to change the destination folder paths 
   in fileServerThread.java
2. Open the project using IntelliJ and add the requiring external libraries to it, as well as VM options
3. Go into the source sample folder labeled “src/sample”
4. Right click on the file server and select the option that says ‘Run fileServer.main()’
5. Ensure the server is up and running
6. Run the ‘main.java’ file
7. Once the program is running, you will be prompted to select a directory and choose your client folder you want to 
   share to the server.
8. Once these steps are completed the system will be available in order for you to upload or download files
