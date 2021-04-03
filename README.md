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

Working UI:
1. Directory Chooser
![image](https://user-images.githubusercontent.com/56410993/113494406-e5c7cc80-94b5-11eb-85e3-f3f56ca1d840.png)

2. UI

![image](https://user-images.githubusercontent.com/56410993/113494416-fc6e2380-94b5-11eb-9c5f-e736cff92a6d.png)

3. Download button before

![image](https://user-images.githubusercontent.com/56410993/113494433-1f003c80-94b6-11eb-9d42-0450b8a4d099.png)

After:

![image](https://user-images.githubusercontent.com/56410993/113494441-29223b00-94b6-11eb-8cc9-d4cb21e93fb4.png)


4. Upload Button before

![image](https://user-images.githubusercontent.com/56410993/113494449-39d2b100-94b6-11eb-9175-b88f920f25d1.png)

after:

![image](https://user-images.githubusercontent.com/56410993/113494458-4f47db00-94b6-11eb-98cb-79081bcfa821.png)

(Also outputs the contents of the file on  terminal)


5. Open Local File:

![image](https://user-images.githubusercontent.com/56410993/113494475-79010200-94b6-11eb-8b2a-813146234e8c.png)

6. Open Server File:

![image](https://user-images.githubusercontent.com/56410993/113494478-87e7b480-94b6-11eb-997c-6cd8df789072.png)




