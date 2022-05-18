# TDK-Boilerplate-Java

This TDK is also available in [C#](https://github.com/xillio/TDK-Boilerplate-CSharp)
and [Node.js](https://github.com/xillio/TDK-Boilerplate-Node)

## What is the purpose of the TDK?

Configuring JSON RPC connector via the dashboard of LocHub is a complicated task.

The connector has lots of parameters. Many of then are optional.

It is frequently confusing which parameter has to be provided and which can be ignored because it will not affect your workflow with JSON RPC connector in a specific case.

TDK solves this inconvenience by restricting the amount of parameters that can be provided and for limited amount of operations that can be performed.

## Supported operations

1. Delivering the metadata of your content
2. Navigating your content repository
3. Downloading binary content of a file
4. Uploading files

## How to make the TDK work?

All you need to do is implement:

1. How to access the metadata of your content
2. How to access content itself
3. How to upload content
4. How to validate the configuration parameters (optional)
5. How to authenticate (optional)

## How to implement the described functionality?

With probability of 99% your IDEA will have a functionality to look up **TODO**s.

Go to this tab and see places where you have to implement how to perform these operations with your content system.

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
