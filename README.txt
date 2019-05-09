{\rtf1\ansi\ansicpg1252\cocoartf1561\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww19200\viewh14980\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 CS 371: Safety Net: Final Implementation\
Anthony Campos\
Brandon Ihlein\
Anaira Quezada\
Catalina Sanchez-Maes\
=========================================================\
Link to Repository: https://github.com/Aceman2423/SafetyApp\
Link to Download androidStudio: https://developer.android.com/studio/\
=========================================================\
For our project to function properly it has to be implemented on Android studio.\
\
The simplest manner to implement the files from our repository into Android studio is to clone our project onto your device, then to open AndroidStudio and \'93Import Project\'94 and select the cloned repository. This will open the most recent version of our project.\
\
Once in AndroidStudio the best way to run our program is press the green arrow button near the top left of the screen, it will appear as \'93Run \'92app\'92 ^ R\'94 when you hover over it. This will take you to a page where you may have to create a virtual device object if one is not already present.\
\
If the label \'93Nexus 5X API 28\'94 then you must create the device. This can be done by clicking the \'93Create New Virtual Device\'94 button, from there you must make sure that \'93Phone\'94 is highlighted in the \'93Category\'94 column. From there be sure to select \'93Nexus 5X\'94 with a 5.2\'94 size. Then select the \'93Next\'94 button in the bottom right hand corner, then select \'93Pie\'94 as the \'93Release Name\'94, then click \'93Next\'94 and make sure that the device has a Portrait orientation then click finish. Now you can run the virtual device. Click on the \'93Nexus 5X API\'94 device under \'93Available Virtual Devices\'94 and hit \'93OK\'94.\
\
You will then see the terminal on AndroidStudio begin to say Build, you will be able to successfully use the Virtual Device once you see \'93Build: completed successfully.\'94 The virtual device should pop up automatically with the app already open if not you can click on the white circle at the bottom of the virtual phone\'92s screen and click on the android logo (the half alien robot face) and it will open the application with our label \'93Safety App\'94 on top. \
\
If this is the first time you are using the application there will be some permission statements that will appear on the screen of the phone. Once the user gives consent to the application to access the audio and location setting on the phone you will be able to successfully run the application. A toast message will appear once each permission is granted or denied by the user. After the permissions are given by the user they will not be asked for again. \
\
In order for the recording portion of our application to work you must make sure that the microphones are enabled on the virtual phone. This can be done by accessing the phones settings. You will see a  white column filled with small icons alongside the right side of the phone. Click on the \'93\'85\'94 button to open the phone\'92s settings. Once you click it, another window will pop up called \'93Extended Controls.\'94 To turn on the microphone settings click on the \'93Microphone\'94 tab on the left hand side of the window. Then make sure \'93Virtual headset plug inserted,\'94 \'93Virtual headset has microphone,\'94 and \'93Victual microphone uses host audio input\'94 all have green bars underneath them indicating they are enabled. \
\
Before you are able to use the \'93SEND EMAIL\'94 button you will need to set up an email account, preferably gmail on the emulator phone. The first time that you initiate the \'93SEND EMAIL\'94 button you will need to select the email that will be the default for the user. After this is set up, you will be able to use all of the buttons in the application.\
\
When the \'93RECORD\'94 button is initiated the toast message \'93Recording\'85\'94 will appear at the bottom of the screen indicating that the emulator has started recording; it will record for 5 seconds. Once it has finished recording you will see the toast message \'93Stopped Recording\'94 indicating that the emulator has stopped recording. After you see the \'93Stopped Recording\'94 message the application will automatically take you to the  \'93SEND EMAIL\'94 activity page. \
\
When the \'93SEND EMAIL\'94 activity page is executed it will take you to a new page where a pop up menu containing \'93Gmail\'94 and \'93OneDrive\'94 will appear. Select the appropriate email outlet and it will take you to email page. If you set up the default email correctly, the user\'92s email will already appear in the \'93From: \'93 section of the email. From there you must add the recipient\'92s email and a subject header for the email. The message of the email will contain the message \'93The event occurred at:\'94 followed by a timestamp from when the \'93RECORD\'94 button was initiated, and finally a link to GPS Coordinates in Google Maps. \
\
Please note that the message of the email will sometimes appear blank, but this does not affect what the recipient of the message gets. The email received still contains the message, time stamp, and link. \
\
Please note that if you are running the emulator, the virtual phone does not have an actual GPS locator so you will have to go into the phone\'92s settings, as you did earlier to to turn on the microphones. If you would like to test different coordinates you may change the longitudinal and latitudinal coordinates then press send so the emulator has access to them. Please note that the emulator will not let you enter invalid coordinates\
\
After the email function has been completed you will be taken back to the \'93SEND EMAIL\'94 activity page which is now not necessary, click the back button on the screen and you will be taken back to the application\'92s home screen. Now if the \'93PLAY\'94 button is initiatied the audio will begin playing and the toast message \'93Playing\'85\'94 will appear at the bottom of the screen. \
\
 }