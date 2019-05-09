CS 371: Safety Net: Final Implementation
Anthony Campos
Brandon Ihlein
Anaira Quezada
Catalina Sanchez-Maes
=========================================================
Link to Repository: https://github.com/Aceman2423/SafetyApp
Link to Download androidStudio: https://developer.android.com/studio/
=========================================================
For our project to function properly it has to be implemented on Android studio.

The simplest manner to implement the files from our repository into Android studio is to clone our project onto your device, then to open AndroidStudio and “Import Project” and select the cloned repository. This will open the most recent version of our project.

Once in AndroidStudio the best way to run our program is press the green arrow button near the top left of the screen, it will appear as “Run ’app’ ^ R” when you hover over it. This will take you to a page where you may have to create a virtual device object if one is not already present.

If the label “Nexus 5X API 28” then you must create the device. This can be done by clicking the “Create New Virtual Device” button, from there you must make sure that “Phone” is highlighted in the “Category” column. From there be sure to select “Nexus 5X” with a 5.2” size. Then select the “Next” button in the bottom right hand corner, then select “Pie” as the “Release Name”, then click “Next” and make sure that the device has a Portrait orientation then click finish. Now you can run the virtual device. Click on the “Nexus 5X API” device under “Available Virtual Devices” and hit “OK”.

You will then see the terminal on AndroidStudio begin to say Build, you will be able to successfully use the Virtual Device once you see “Build: completed successfully.” The virtual device should pop up automatically with the app already open if not you can click on the white circle at the bottom of the virtual phone’s screen and click on the android logo (the half alien robot face) and it will open the application with our label “Safety App” on top. 

If this is the first time you are using the application there will be some permission statements that will appear on the screen of the phone. Once the user gives consent to the application to access the audio and location setting on the phone you will be able to successfully run the application. A toast message will appear once each permission is granted or denied by the user. After the permissions are given by the user they will not be asked for again. 

In order for the recording portion of our application to work you must make sure that the microphones are enabled on the virtual phone. This can be done by accessing the phones settings. You will see a  white column filled with small icons alongside the right side of the phone. Click on the “…” button to open the phone’s settings. Once you click it, another window will pop up called “Extended Controls.” To turn on the microphone settings click on the “Microphone” tab on the left hand side of the window. Then make sure “Virtual headset plug inserted,” “Virtual headset has microphone,” and “Victual microphone uses host audio input” all have green bars underneath them indicating they are enabled. 

Before you are able to use the “SEND EMAIL” button you will need to set up an email account, preferably gmail on the emulator phone. The first time that you initiate the “SEND EMAIL” button you will need to select the email that will be the default for the user. After this is set up, you will be able to use all of the buttons in the application.

When the “RECORD” button is initiated the toast message “Recording…” will appear at the bottom of the screen indicating that the emulator has started recording; it will record for 5 seconds. Once it has finished recording you will see the toast message “Stopped Recording” indicating that the emulator has stopped recording. After you see the “Stopped Recording” message the application will automatically take you to the  “SEND EMAIL” activity page. 

When the “SEND EMAIL” activity page is executed it will take you to a new page where a pop up menu containing “Gmail” and “OneDrive” will appear. Select the appropriate email outlet and it will take you to email page. If you set up the default email correctly, the user’s email will already appear in the “From: “ section of the email. From there you must add the recipient’s email and a subject header for the email. The message of the email will contain the message “The event occurred at:” followed by a timestamp from when the “RECORD” button was initiated, and finally a link to GPS Coordinates in Google Maps. 

Please note that the message of the email will sometimes appear blank, but this does not affect what the recipient of the message gets. The email received still contains the message, time stamp, and link. 

Please note that if you are running the emulator, the virtual phone does not have an actual GPS locator so you will have to go into the phone’s settings, as you did earlier to to turn on the microphones. If you would like to test different coordinates you may change the longitudinal and latitudinal coordinates then press send so the emulator has access to them. Please note that the emulator will not let you enter invalid coordinates

After the email function has been completed you will be taken back to the “SEND EMAIL” activity page which is now not necessary, click the back button on the screen and you will be taken back to the application’s home screen. Now if the “PLAY” button is initiatied the audio will begin playing and the toast message “Playing…” will appear at the bottom of the screen. 

 
