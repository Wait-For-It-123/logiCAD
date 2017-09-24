# LogiCAD

Team project for CSE442 at University at Buffalo Fall 2017

LogiCAD is a digital logic CAD (computer aided design) tool. It can be used to create and test digital logic circuits.

## Target User:
Our target user for LogiCAD is a person who has working knowledge of digital logic circuits (including logic gates) and is capable of designing their own circuits.

## DEPENDENCIES
1. This program is written in Java
2. It uses JRE 8u144, JRE System Library Java SE-1.8 and Java SE Development Kit 8.0
3. To run the executable jar file Java SE Runtime Environment 8u144 must be installed on your machine

## DIRECTIONS FOR USE:
These directions for running LogiCAD are for 64 bit Windows 10 machines only.

### TO RUN THE LOGICAD PROGRAM (EXECUTABLE JAR FILE):
1. Using your favorite internet browser, navigate to this URL: https://github.com/Wait-For-It-123/logiCAD/releases/tag/v0.1.0

      i. Under the downloads section of this page download the executable jar file "logicad.jar"
2. Download and install Java Runtime Environment 8u144

      i. For the JRE use this URL: http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
      
      ii. After accepting the license agreement, select the Windows x64 offline download.
      
      iii. Install the JRE file by double-clicking it and follow the prompts
3. Find the logicad.jar file you downloaded in step 1 on your computer.
4. Double-click the file to launch logicad.

### RUN FROM SOURCE:
1.  Download Eclipse Oxygen Java IDE

      i. For 64 bit use this URL: http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/1.RC4/eclipse-java-oxygen-1-rc4-win32-x86_64.zip
	  
2. Download and install JRE 8 and JDK 8.0

      i. For the JRE use this URL: http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
3. Using your favorite internet browser, navigate to this URL: https://github.com/Wait-For-It-123/logiCAD/releases/tag/v0.1.0
4. Under the downloads section download "Source code (zip)"
5. Unzip the downloaded file "logicad-0.1.0.zip"
6. Launch Eclipse and go to "File -> Open projects from File System"
7. Click "Directory", navigate to the directory where you extracted the zip file and select it.
8. Click "Finish"
9. In the project explorer, click the arrow to the left expand the LogiCAD project directory
10. Navigate to src.code.driver
11. Right click on the file "driver.java" and select Run As -> Java Application
12. The LogiCAD application should be launched 

### RUN FROM CLONE:
1. Install dependencies as listed in steps 1 and 2 in the RUN FROM SOURCE section.
2. Download GitBash from the URL: https://git-for-windows.github.io/ and install it
3. Create a new folder in your filesystem where you would like your local repository to reside
4. Open GitBash andnavigate to the directory you created using the `cd` command
5. Clone the repository using the command `git clone https://github.com/Wait-For-It-123/logiCAD.git`

      i. This will create a local repository in the folder you created in step 1 whose root folder is named "logiCAD"
6. Launch Eclipse and go to "File -> Open projects from File System"
7. Click "Directory", navigate to the "logiCAD" directory from step 3a.
8. Click "Finish"
9. In the project explorer, click the arrow to the left to expand the LogiCAD project directory
10. Navigate to src.code.driver
11. Right click on the file "driver.java" and select Run As -> Java Application
12. The LogiCAD application should be launched 
