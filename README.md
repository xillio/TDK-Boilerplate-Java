# TDK-Boilerplate-Java



This TDK is available also in [C#](https://github.com/xillio/TDK-Boilerplate-CSharp)
and [Node.js](https://github.com/xillio/TDK-Boilerplate-Node)

## What is the purpose of the TDK?

Configuring JSON RPC connector via dashboard is a complicated task.

The connector has lots of parameters. Many of then optional but some are mandatory.

It is frequently confusing to the user which parameter has to be provided and which can be ignored because it will not
affect user's workflow with JSON RPC connector.

TDK solves this inconvenience by restricting the amount of parameters that can be provided and operations that can be
performed.

TDK provides only basic functionality needed in 9 of 10 cases.

### Supported operations

TDK allows to perform the following operations:

1. Delivering the metadata of your content
2. Delivering the metadata of a specific content
3. Navigating your content repository
4. Downloading binary content
5. Uploading translations

## How to make the TDK work?

All you need to do is implement:

1. How to access the metadata of your content
2. How to access content itself
3. How to upload content
4. How to validate the configuration parameters (optional)
5. How to authenticate (optional)

After that, the TDK will be ready to use.

## How to implement the described functionality?

With probability of 99% your IDEA will have a functionality to look up **TODO**s.

Go to this tab and see places where you have to implement how to perform these operations with your content system.

If you cannot quickly look up the marked methods, go to:

1. `com.hellotranslate.connector.repository.metadata.MetadataRepositoryImpl`
2. `com.hellotranslate.connector.repository.content.ContentRepositoryImpl`
3. `com.hellotranslate.connector.service.ConfigValidationService`

> **NOTE:** The default url for the ConnectorController is `/sample-connector`. You can change it in the `com.hellotranslate.connector.controller.ConnectorController`.

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
