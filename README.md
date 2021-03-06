# Language Segregator Doc

## Introduction

This application is used for analysis of multiple languages document and separate the scripts of the different language.

The usage scenarios are as follows:

The translator translates one language to another language in the same document and needs a separate language document.

## Heroku Instance

I have deployed the application to the Heroku (free Dyno):

Webapp: https://lang-segregator.herokuapp.com/

The Swagger-UI: https://lang-segregator.herokuapp.com/swagger-ui.html

So it can be quick checked and reviewed including the detail of API doc.

Because of I am using the free Dyno of Heroku, so the performance has limitation, it cannot process too long file (The request will timeout), **please use the these file to test with this Heroku deployment**:

1. [farsi-english-script-short.rtf](doc/test/farsi-english-script-short.rtf)
2. [farsi-english-script-short.txt](doc/test/farsi-english-script-short.txt)

### Steps:

1. Download these file above
2. Choose the file
3. Select `English` and `Persian` (You can press `E` to navigate to `English` and press `P`twice to navigate to `Persian` quickly)
4. Click button `Start to analysis`

## API 

The API can be accessed by using Swagger-UI: 

http://localhost:8080/swagger-ui.html

(online version: https://lang-segregator.herokuapp.com/swagger-ui.html)

![Swagger-UI-screenshot](/doc/img/api-swagger-ui.png)

There are two primary API for separate String and Document:

1. `/api/v1/split`
2. `/api/v1/splitDoc`

If you check the Swagger API Document, you will find there are other configurations for the API. Based on this configuration, the API can support more format of the document. Next section will introduce these option in detail.

![API-Screenshot](/doc/img/api-options.png)

In order to give end-user best User Experience, we format the output of analysis result as a mapping between language. Meanwhile, I think about the title in the document should be shared among the different languages. So finally, the application can provide the result like this: 

![Analysis-Result-Screenshot](/doc/img/webapp-analysis-resuolt.png)

## The API Design and  Option Usage

This section will discuss the detail of API design which includes the reason I design these options and how to use these option to make this API to compatible more format of the source document.

According to the requirement, the document is written by people with a different background who can translate. So the format of the document may be varied.

### The fragment of the document

The first important question is how to divide the document into several fragments to detect language which also called **granular**. Based on given the sample script, there are two symbols can be used for separating the document: **Blank Line** and **Line Break**. Based on the general habit of the writers, the **Line Break** maybe the general usage. Meanwhile, the **Line Break** can separate the different language as more as possible (reduce the possibilities of one fragment contain multiple languages). So the **Line Break** is **Default** option. 

Apart from the **Line Break**, the API also supports the **Blank Line** and **Regular Express**. If choose **Regular Express**, the express string should be provided.

![API-Fragment-Separator](/doc/img/api-option-separator.png)

### Language detection

One of the core technique back of the API is language detection, but every local algorithm should be based on the existing language profile to detect the language, but if the string of fragment in the document is too short, the detection result will be not accurate. So in the design of the API, I need the user specifies the languages of the document contains which can filter some detection false positive.

![API-Contained-Lang-List](/doc/img/api-option-lang-list.png)

### Support title

According to the sample document, I found that there are lots of title or some Uppercase introduction sentence or word. These fragments are not translated. So they are should be shared between the multiple language scripts. Therefore, I design an option for whether support title and the default value is **True**.

![API-Support-Title](/doc/img/api-option-support-title.png)

#### more options of title

If support the title, the question is how to decide one fragment is Title? So I design several options to filter the fragment as a title. Based on the priority, the options are as follows: 

1. whether all capitals (should be Latin Characters that have uppercase style).
2. whether less than a specified length.
3. whether match with a specified Regular Expression.
4. filter by the language (TODO)

![API-Title-Filter-Options](/doc/img/api-options-title-fitler-option.png)

The default option: Filter by all Capitals based on the sample document which can filter nearly all titles.

## The Basic Frontend App

I use Angular and Material Design implement a basic frontend web app to demo the `splitDoc` API. Currently, this web app only supports the default options. So the step-by-step usage:

1. Add document
2. Select the language of the document, which supports keyboard to quick search
3. Click Start to analysis button
4. Waiting for the result

The app will be deployed on the Heroku.

## How to build and run

The application is based on the Spring Boot and Angular. Meanwhile, I have use the `frontend-maven-plugin` build and copy frontend resource to the target, so run the application in local is very easy with the following command.

The default development port is **8080**, please make sure the port is not listened by other application. After run the command, visit the browser : http://localhost:8080

### Linux/Mac OS

```bash
./mvnw spring-boot:run
```

### Windows

```bash
./mvnw.cmd spring-boot:run
```

### Separate Backend and Frontend

If you would like to run the backend and frontend separately, just need to run the frontend with default port `9000`.

```bash
cd src/main/webapp/
npm start
```

The Angular development configuration use the default backend url and port: http://localhost:8080

## Background Technique

### The Fragment handle flowchart

https://www.lucidchart.com/documents/view/b8a831d5-565c-40bb-8898-2b8e7b28a5fa

### Language Detection

The language detection is the core of the API: given a string, return the language of the string. There are several open source projects support this feature: [language-detector](https://github.com/optimaize/language-detector) and [language-detection](https://github.com/shuyo/language-detection). Meanwhile, there are also several online products provide this service, such as [detectlanguage](http://detectlanguage.com/) who has some free daily usage.

Basically, the local detection method is based on Bayes and n-gram. Meanwhile, the dataset should be trained firstly and then use it to detect. Usually, we need to choose some normal language sample to train, for example, Wikipedia of the different language. 

The advantage is the local API only depend on the local dataset and the dataset can be trained by the specified document sample. But the disadvantage is that the short string will be very hard to detect. So I think the online service also use a search engine to match the dictionary to make the result more accurate.

In my API, I combine these two methods: use the local detection firstly and if get an error or local possibility, then try to use the 3rd API to get the accurate result.

### Spring Boot Rest API and Swagger

The backend is based on the Spring Boot with Rest API and Swagger support with the SpringFox

### Angular Frontend Framework

The frontend is based on the Angular with Material Design and several 3rd packages such as `ngx-logger` and `ISO-639-1` and so on.
