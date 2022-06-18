AppSuite
=========

This repository contains all the projects categorized based on the programming languages and usability.

These projects should have the basic and core implementations which can be used by other projects.
It might be some of them use any third party library, so the source code available in this repository will be available 
for AS IT IS usage.

## Folder Structure Conventions
    .
    ├── Core                    # Core project files
    ├── HttpClient              # Http-Client project files
    ├── HttpServer              # Http-Server project files
    ├── Jdk                     # Java project files
    ├── mime-craft               # MimeCraft project files
    ├── Tutors                  # TestCases project files
    └── README.md


### Prerequisites

What things you need to install the software and how to install them

Check if you already have java or not. If not, install java ``1.8`` version.

```
java -version
```

Now, check if you already have Maven or not. If not, install Maven ``3.6.0`` or 
latest version.

```
mvn --version
```

Next, make sure you have Eclipse Version ``2019-09 R (4.13.0)`` or later version.

And now check you have the ``apache-tomcat-9.x`` or later version on your 
machine.


And finally, make sure you have permission to check-out the code from the GIT 
repository. Open terminal and go to your workspace. Then run the following 
command to check-out the source code on your machine.

```
git clone https://github.com/rslakra/AppSuite.git
```


If you are missing any of the above mentioned software, please following the 
``Installing`` section below.


### Installing

A step by step series of examples that tell you how to get a development 
environment running

These URLs will help you to download and install the required softwares.
Please follow the instructions and download the latest stable version, as 
supported by your OS version.

* [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java SE Development Kit 8 Downloads
* [Maven](https://maven.apache.org/download.cgi) - Apache Maven
* [Eclipse](https://www.eclipse.org/downloads/) - Eclipse IDE 2019‑09
* [Tomcat](https://tomcat.apache.org/download-90.cgi) - Apache Tomcat® 9.x


### Deployment

Once you have installed all above mentioned software, then you are ready 
to run the server on your local machine and play with UI.

* Run Eclipse
* File -> Import -> Existing Maven Projects -> Next
* Choose Root folder, where the GIT source exists.
* Follow further instructions to configure the project into Eclipse.

Once the source code is imported, make sure it compiles.

Now, configure the ``Apache Tomcat`` into your Eclipse, if not already configured.

* Window -> Show View -> Server

It will display the Server View. Select ``Servers`` Tab, if not already selected.

* Right Click with your mouse on the ``Server`` Tab.
* Select New -> Server
* Choose Apache -> Apache v9.0 Server -> Next
* Select ``Java`` from Left and press ``Add`` and then Finish.

It will configure the ``Apache Tomcat® 9.x`` in your eclipse.
Now you are ready to run the application on your local machine.

* From ``Project Explorer`` right click on ``Java`` and then Choose ``Run As => Run on Server``


If everything went well, your application will start locally on your machine.
It's not surprising that first time, you face some issue, if you are not familiar 
with all the above mentioned tools and those are not configured property.
Please feel free to reach the project owner team for further help.


## Contributing

Please read [CONTRIBUTING.md](https://github.com/rslakra/AppSuite/blob/master/CONTRIBUTING.md) for details on our code of 
conduct, and the process for submitting pull requests to us.


## Authors

* [Rohtash Singh Lakra](https://github.com/rslakra)

See also the list of [contributors](https://github.com/rslakra/AppSuite/contributors) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE.md](https://github.com/rslakra/AppSuite/LICENSE.md) file for details

## Acknowledgments

* Passion
* Inspiration
* Motivation
