# LocHub/HelloTranslate TDK Boilerplate project for Java

[LocHub](https://lochub.com) and [HelloTranslate](https://hellotranslate.com) are translation middleware platforms by [Xillio](https://xillio.com) connecting content owners and the translation providers as seamlessly as possible.

The platforms come with a content framework built by Xillio. After initial definition of a connection to your content, you can navigate the repositories with content browser and pickup content for translation manually or automate the selection based on metadata.

Translation Development Kit (aka TDK) is a way how to integrate your content into this content framework and use all the described features with your content. To integrate your content you can choose your favorite technology and you need to build a web service capable of

* delivering the metadata of your content
* navigating your content repository
* downloading binary content
* uploading translations

Your web service will expose an endpoint and the framework will send requests to this endpoint with JSON payload to specify the operation to perform and its parameters. The web service will respond with a JSON response describing the metadata of the content, deliver the binary content or simply confirm the translation creation.

This repository contains a boilerplate project of such a web service build with Java, Spring Boot and Maven.

Similar boilerplate projects are available also for [C#](https://github.com/xillio/TDK-Boilerplate-CSharp)
and [Node.js](https://github.com/xillio/TDK-Boilerplate-Node)

## How to use TDK?

We recommend reading our [TDK documentation](https://www.hellotranslate.com/translation-development-kit/) first. You will learn the overall design and how to build a custom connector from scratch.

But using the boilerplate project like this is much easier. You need to implement only the communication with your repository and optionally also validation of your custom configuration and the boilerplate takes care about the rest (error handling, parsing the JSON RPC requests, building the JSON RPC responses, etc.) 

### Custom configuration and validation of such a configuration

To point LocHub/HelloTranslate to your custom connector you will need to define a connection where you specify the URL of the endpoint where you listen for delegated requests. Each such a definition can also contain any custom JSON object with configuration for your connector. This can contain for example some authentication values, area of the repository that you want to expose, etc.

The content framework will then copy this custom JSON object to every delegated request. It is recommended to use some authentication mechanism and to validate also other configuration values.

To do this validation implement `ConfigValidationService#validateConfig` method and in case the validation fails, throw the `InvalidConfigException` exception with an error message why it failed. The boilerplate will build the JSON RPC response for you and respond to the content framework.

To authorize the request implement `ConfigValidationService#authorize` (processing your custom authorization values in the custom configuration object) and throw `AuthorizationFailedException` should the authorization failed. 

### Delivering metadata of one entity



### Listing children entities of a container

### Delivering content of an entity

### Uploading a translation of an entity

If you cannot quickly look up the marked methods, go to:

1.`com.hellotranslate.connector.service.ConfigValidationService`
2.`com.hellotranslate.connector.repository.metadata.MetadataRepositoryImpl`
3.`com.hellotranslate.connector.repository.content.ContentRepositoryImpl`

> **NOTE:** The default url for the ConnectorController is `/sample-connector`. You can change it in the `com.hellotranslate.connector.controller.ConnectorController`.

## Config field and its validation

The `config` field, in the request body, provides additional parameters, e.g. credentials for the account where your content is stored.

The value of `config` is defined by you and sent in body of the request.

```
{
    "jsonrpc": "2.0",
    "id": "4ab6901a-a749-4026-afd1-2e13307fa283",
    "method": "entity.get-binary",
    "params": {
        "config": {
            "repository": "tests"
        },
        "xdip": "xdip://my-connector/pages/123"
    }
}
```
Each incoming request is being validated on the correctness of `method`, `jsonrpc` protocol version, presence of `id` etc.

Since we cannot know what kinds of key-value pairs you will be sending with each request, it's completely your responsibility to validate this field.

Implement the validation in `ConfigValidationService#validateConfig` and throw the `InvalidConfigException` with a reason message why it failed.

You also can throw more specific exceptions regarding body validation. The list of them can be found at `com.hellotranslate.connector.exception.jsonrpc.bodyvalidation`.

You can also create custom exceptions by extending `com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.RequestBodyValidationException` and putting there a reason message and error codes of LocHub (`com.hellotranslate.connector.exception.lochub.LocHubErrors`) or JSON RPC (`com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors`).

For the meaning of the LocHub error codes, refer [here]() and for JSON RPC [here](https://www.jsonrpc.org/specification#error_object)

As was mentioned before, you can send credentials in the config body. Try to authorize to your content system in the `ConfigValidationService#authorize` method and throw `AuthorizationFailedException` with our without a specific message if something goes wrong.

## Access the metadata of your content via MetadataRepositoryImpl

In the `com.hellotranslate.connector.repository.metadata.MetadataRepositoryImpl` class you will have 3
methods: `listChildren()`, `listReferences()` and `getEntityByXdip()`.

All of them are having the same parameters in their signatures - `XDIP xdip, Map<String, Object> config`.

`xdip` is the identifier of your content.

We talked about `config` before. Depending on your specific business logic, you can have a need to use some values form it. It's completely up to you how to extract these values.

Implement the methods.

You are responsible for error handling of your system.

As previously, you can use already prepared exceptions from the `com.hellotranslate.connector.exception.jsonrpc.response` package or create a custom ones by extending `com.hellotranslate.connector.exception.jsonrpc.response.ResponseBodyException`.

Further flow is already implemented in TDK.

## Content manipulations via ContentRepositoryImpl

In the `com.hellotranslate.connector.repository.content.ContentRepositoryImpl` class you will have 2
methods: `downloadContent()` and `uploadContent()`.

Everything said about implementation of `MetadataRepositoryImpl` is valid for `ContentRepositoryImpl` except methods have different parameters.

## TDK & Docker

To build the Docker image run:

```
mvn install
```

```
docker build -t <NAME_OF_YOUR_IMAGE> .
```

and run the container:

```
docker run -p 8080:<TCP_PORT_IN_THE_CONTAINER> <NAME_OF_YOUR_IMAGE>
```

By default, port on the Docker host is 8080.

If, for some reason you want to change it, you can do it in the Dockerfile at the line 2nd line - `EXPOSE 8080`
