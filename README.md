# LogiCAD

Team project for CSE442 - Software Engineering at University at Buffalo, Fall 2017.

LogiCAD is a Computer-Aided Design application for creating digtial circuits using seven basic logic gates: AND, OR, NOT, XOR, NAND, NOR, and XNOR.  LogiCAD provides the user with a visual interface with which to interact with the circuit building blocks, making the design process easier.  Testing of the circuit is achieved by changing input signal values; the output signal values of the circuit are then calculated through appropriate signal propagation, allowing the user to verify circuit correctness.  Circuits built in LogiCAD can be saved using the save function located under File->Save.  Files are saved in .lca ('l' as in LogiCAD) format.  Circuits are saved in an unevaluated state.  Files with extension .lca can be loaded by using the Load function located under File->Load.  The circuit that was previously saved will appear in the workspace in an unevaluated state.

## Target End-User:
Our target end-user for LogiCAD is a person who has working knowledge of digital logic circuits (including logic gates) and is capable of designing their own circuits.

## DEPENDENCIES
1. This program is written in Java
2. It depends on JRE 8u144, JRE System Library Java SE-1.8 and Java SE Development Kit 8.0
3. To run the executable jar file Java SE Runtime Environment 8u144 must be installed on your machine

## DIRECTIONS FOR USE:
These directions for running LogiCAD are for 64-bit machines running Windows 10 only.

### TO RUN THE LOGICAD PROGRAM (EXECUTABLE JAR FILE):
1. Using your favorite internet browser, navigate to this URL: https://github.com/Wait-For-It-123/logiCAD/releases/tag/v1.0.0

      i. Under the downloads section of this page download the executable jar file "logicad.jar"
2. Download and install the Java Runtime Environment 8u144 (you may skip steps 2(i) through 2(iii) if JRE 8u144 is already installed on your computer)

      i. To download the JRE, use this URL: http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
      
      ii. After accepting the license agreement, select the Windows x64 offline download, and download it.
      
      iii. Find the file downloaded in step 2(ii) and install the JRE file by double-clicking it and following the prompts
3. Find the logicad.jar file you downloaded in step 1 on your computer.
4. Double-click the file to launch logicad.

### RUN FROM SOURCE:
1.  Download and install Eclipse Oxygen Java IDE (You may skip this step if you already have this version of Eclipse installed on your computer)

      i. For 64-bit, use this URL: http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/1.RC4/eclipse-java-oxygen-1-rc4-win32-x86_64.zip
	  
2. Download and install JRE 8u144 and JDK 8.0 (You may skip steps 2(i) and 2(ii) if both the JRE 8u144 and JDK 8.0 are already installed on your computer)

      i. For the JRE, use this URL: http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html and download the Windows x64 offline download.
      
      ii. For the JDK, use this URL: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html and download the Java SE Development Kit 8u144 Windows x64 download.
3. Using your favorite internet browser, navigate to this URL: https://github.com/Wait-For-It-123/logiCAD/releases/tag/v1.0.0
4. Under the downloads section download "Source code (zip)"
5. Unzip the downloaded file "logicad-1.0.0.zip".  The files will be unzipped into a directory called "logiCAD-1.0.0"
6. Launch Eclipse and go to "File -> Open projects from File System"
7. Click "Directory" and navigate to the location where you extracted the zip file. Select the directory called "logiCAD-1.0.0" and click "OK"
8. Click "Finish"
9. In the project explorer, click the arrow to the left to expand the LogiCAD project directory
10. Navigate to src.code.driver
11. Right click on the file "driver.java" and select Run As -> Java Application
12. The LogiCAD application should be launched 

### RUN FROM CLONE:
1. Follow steps 1 and 2 in the RUN FROM SOURCE section.
2. Download GitBash from the URL: https://git-for-windows.github.io/ and install it
3. Create a new folder in your filesystem where you would like your local repository to reside
4. Open GitBash andnavigate to the directory you created using the `cd` command
5. Clone the repository using the command `git clone https://github.com/Wait-For-It-123/logiCAD.git`

      i. This will create a local repository in the folder you created in step 1 whose root folder is named "logiCAD"
6. Launch Eclipse and go to "File -> Open projects from File System"
7. Click "Directory" and navigate to the "logiCAD" directory from step 5(i). Select the directory "logiCAD" and click "OK"
8. Click "Finish"
9. In the project explorer, click the arrow to the left to expand the LogiCAD project directory
10. Navigate to src.code.driver
11. Right click on the file "driver.java" and select Run As -> Java Application
12. The LogiCAD application should be launched 

### NEW FEATURES:
1. Saving and Loading

      i. The save button is located under File -> Save. A file explorer will pop up for you to choose the location
      
      ii. All LogiCAD files must be saved as .lca files ('l' as in LogiCAD). Circuits are saved in unevaluated states.
      
      iii. The load button is located under File -> Load. A file explorer window will pop up for you to choose the (.lca) file. 

      iv. This will restore the workspace to the state from when it was saved.  Any circuit(s) will be in an unevaluated state(s) 








The following Copyright is from code we used from an Oracle tutorial that handles putting elements into the
workspace:

 Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 
   - Redistributions of source code must retain the above copyright
     notice, this list of conditions and the following disclaimer.
 
   - Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.
 
   - Neither the name of Oracle or the names of its
     contributors may be used to endorse or promote products derived
     from this software without specific prior written permission.
 
 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
