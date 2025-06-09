AppSuite
=========
---

This repository contains all the projects categorized based on the modules and learning purposes.

These projects should have the basic and core implementations which can be used by other projects.
It might be some of them use any third party library, so the source code available in this repository will be available 
for AS IT IS usage.

## Build Status


| Build | Tests | Pages                                                                                                                                                                                                         |
|:------|:------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|       |       | [![pages-build-deployment](https://github.com/rslakra/AppSuite/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/rslakra/AppSuite/actions/workflows/pages/pages-build-deployment) |



## Folder Structure Conventions

---

```
    /
    ├── Core                    # The Core Project
    ├── Example                 # The Example Project
    ├── HBase                   # The HBase Project
    ├── Identity                # The Identity Project
    ├── Jdk                     # The Jdk Project
    ├── Metrics                 # The Metrics Project
    ├── pdf-tools               # The pdf-tools Project
    ├── protocols               # The protocols Project
    ├── spring                  # The spring Project
    ├── tutot                   # The tutor Project
    └── README.md
```

## Prerequisites

---

What things you need to install the software and how to install them

Check if you already have java or not. If not, install java ``11`` version.

```
java -version
```

Now, check if you already have Maven or not. If not, install Maven ``3.6.0`` or 
latest version.

```
mvn --version
```

Next, make sure you have an IDE either Eclipse or IntelliJ.

## How to set up

---

You should have GIT account to check out the code.

### 1. Clone the repository in your GIT account or local machine

Open terminal and go to your workspace. Then run the following command to check out the source code.

> ```
>   git clone https://github.com/rslakra/AppSuite.git
> ```

### 2. Build the project

> ```
>   cd AppSuite
> ./buildMaven.sh
> ```

### 3. Run the program

Run the program with the following command
  
> ```./runMaven.sh```


If you are missing either any of the above-mentioned steps or software,
please following the following ``Installing`` section.

## Installing

A step by step series of examples that tell you how to get a development 
environment running on your local machine.

The following URLs will help you to download and install the required software.
Please follow the instructions and download the latest stable version, as 
supported by your OS version.

* [Open JDK 11](https://openjdk.org/projects/jdk/11/) - Open JDK 11 Development Kit
* [Brew JDK 11](https://formulae.brew.sh/formula/openjdk@11) - Brew Formula Open JDK 11 Development Kit
* [Oracle JDK 11](https://www.oracle.com/java/technologies/downloads/#java11-mac) - Oracle JDK 11 Development Kit
* [Maven](https://maven.apache.org/download.cgi) - Apache Maven
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=mac) - Download IntelliJ IDEA
* [Eclipse](https://www.eclipse.org/downloads/) - Eclipse IDE 2019‑09
* [Tomcat](https://tomcat.apache.org/download-90.cgi) - Apache Tomcat® 9.x

### Deployment

---

Once you have installed all the above-mentioned software, then you are ready to run the code on your local machine.

## Pipeline

---


[![Pipeline Status][status-image]][status-url]

[status-image]: AppSuite/badges/master/pipeline.svg

[status-url]: AppSuite/badges/master/pipeline.svg

## Contributing

---

Please read [CONTRIBUTING.md](https://github.com/rslakra/AppSuite/blob/master/CONTRIBUTING.md) for details on our code
of conduct, and the process for submitting pull requests to us.

## Reference

- [Workflow syntax for GitHub Actions](https://docs.github.com/en/actions/writing-workflows/workflow-syntax-for-github-actions#about-yaml-syntax-for-workflows)
- [Github action build chain. Cross-repo builds](https://github.com/marketplace/actions/github-action-build-chain-cross-repo-builds)


## Authors

---

* [Rohtash Lakra](https://github.com/rslakra)

See also the list of [contributors](https://github.com/rslakra/AppSuite/contributors) who participated in this project.

## License

---

This project is licensed under the Apache License - see the [LICENSE.md](https://github.com/rslakra/AppSuite/LICENSE.md)
file for details

## Acknowledgments

---

* Passion
* Inspiration
* Motivation


## Thoughts

```shell
"Thoughts lead on to purposes; purposes go forth in action; actions form habits; habits decide character; and character fixes our destiny."

- Tryon Edwards
```
