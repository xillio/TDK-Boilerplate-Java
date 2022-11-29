# LocHub TDK Boilerplate project for Java

[LocHub](https://lochub.com) is a translation middleware platform by [Xillio](https://xillio.com) connecting content owners and the translation providers as seamlessly as possible.

The platform comes with a content framework built by Xillio. After initial definition of a connection to a content repository, user can navigate the repository with a content browser and pickup content for translation manually or automate the selection based on metadata.

Translation Development Kit (aka TDK) is a way how to integrate any content into this content framework and use all the described features with the content. To integrate your content repository you can choose your favorite technology, and build a web service capable of

* delivering the metadata of your content
* navigating your content repository
* downloading binary content
* uploading translations

Your web service will expose an endpoint and the framework will send requests to this endpoint with JSON payload to specify the operation to perform and its parameters. The web service will respond with a JSON response describing the metadata of the content, deliver the binary content or simply confirm the translation creation.

This repository contains a boilerplate project of such a web service build with Java, Spring Boot and Maven.

Similar boilerplate projects are available also for [C#](https://github.com/xillio/TDK-Boilerplate-CSharp)
and [Node.js](https://github.com/xillio/TDK-Boilerplate-Node)

## How to use TDK?

We recommend reading our TDK Documentation first. You will learn the overall design and how to build a custom connector from scratch.

But using the boilerplate project like this is much easier. You need to implement only the communication with your repository and optionally also validation of your custom configuration and the boilerplate takes care about the rest (error handling, parsing the JSON RPC requests, building the JSON RPC responses, etc.) 

### Custom configuration and validation of such a configuration

To point LocHub/HelloTranslate to your custom connector you will need to define a connection where you specify the URL of the endpoint where you listen for delegated requests. Each such a definition can also contain any custom JSON object with configuration for your connector. This can contain for example some authentication values, area of the repository that you want to expose, etc.

The content framework will then copy this custom JSON object to every delegated request. It is recommended to use some authentication mechanism and to validate also other configuration values.

To do this validation implement `ConfigValidationService#validateConfig` method and in case the validation fails, throw the `InvalidConfigException` exception with an error message why it failed. The boilerplate will build the JSON RPC response for you and respond to the content framework.

To authorize the request implement `ConfigValidationService#authorize` (processing your custom authorization values in the custom configuration object) and throw `AuthorizationFailedException` should the authorization failed. 

### Delivering metadata of one entity

To expose the metadata of an entity implement the `com.hellotranslate.connector.repository.metadata.MetadataRepositoryImpl.getEntityByXdip` method.

The method has two parameters to receive the identification of the requested entity and your custom configuration object shall you need any values to finish the operation.

To return the metadata we have modeled Entity class that you will return. This class encapsulates all the metadata as described in the TDK Documentation. Fill in all possible decorators that make sense for your content.

When building the XDIPs (custom URLs to reference your content) you can choose any strategy that fits your content repository. It might be ID based, path based, it might combine content type and ID. The choice is yours. The XDIPs follow the URL standard so do not forget to URL-encode/decode it whenever necessary.

We have also modeled a couple of exceptions you can use in case of expected or unexpected errors. The core of the connector will handle it and for corresponding response to our content framework. These are

- NoSuchEntityException - throw this exception when the requested entity could not be found.
- ConnectorOperationFailedException - throw this exception whenever something unexpected happened.

### Listing children entities of a container

This functionality is covered with methods

- `com.hellotranslate.connector.repository.metadata.MetadataRepositoryImpl.listChildren` and
- `com.hellotranslate.connector.repository.metadata.MetadataRepositoryImpl.listReferences`.

The first one returns a list of full entities, the second one just the list of XDIPs of the entities.

In case of a request for children of an entity that is not a container (this should never happen when used via LocHub/HelloTranslate) you can throw a ConnectorOperationFailedException or simply return an empty list like we do in our demo implementation.

### Delivering content of an entity

To expose the actual binary content of your entities implement method `com.hellotranslate.connector.repository.content.ContentRepositoryImpl.downloadContent`.

As a parameter of this method you will receive the identification of the requested entity (again as an XDIP). You need to return the content in form of an InputStream and the core of the connector will base64 encode it and form the JSON RPC response for our content framework.  

Exceptions you can use in case of expected or unexpected errors are

- NoSuchEntityException - throw this exception when the requested entity could not be found.
- NoBinaryContentException - throw this exception when the requested entity could not have content.
- ConnectorOperationFailedException - throw this exception whenever something unexpected happened.

### Uploading a translation of an entity

To upload the translation of an entity implement `com.hellotranslate.connector.repository.content.ContentRepositoryImpl.uploadContent`.

As parameters, you will receive

- XDIP of the parent container,
- your custom configuration object,
- metadata of the translation (including the mandatory Language decorator with the language code of the translation and the reference of the original master entity),
- binary content of the translation in form of InputStream.

As a result of this operation either throw an exception describing the problem or the metadata of the newly created entity in form of Entity instance.

## TDK & Docker

In case you are using Docker when developing or deploying the solution, we have also prepared a simple Dockerfile for you. This image contains also our demo content. You want to remove it for your tests and deployment.

To quickly run the connector you take the following steps.

```
mvn install
```

```
docker build -t <NAME_OF_YOUR_IMAGE> .
```

and run the container:

```
docker run -p 8080:8080 <NAME_OF_YOUR_IMAGE>
```
