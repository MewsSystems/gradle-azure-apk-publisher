# Azure Storage Build Publisher

With this plugin you can upload Android build via command line or Android Studio.

# Installation

Update your **root** `build.gradle`:

```
buildscript {
    // other stuff here

    dependencies {
        classpath "gradle.plugin.li.mews.android.gradle-azure-apk-publisher:GradleAzureApkPublisher:[VERSION]"
    }
}
```

And add this to your **app** `build.gradle`:

```
apply plugin: "li.mews.android.gradle-azure-apk-publisher"

azurePublishConfig {
    connectionString "[CONNECTION_STRING]"
    container "[CONTAINER_NAME]"
}
```

# Configuration

| Property | Description |
|----------|-------------|
| connectionString | [Connection string](https://docs.microsoft.com/en-us/azure/storage/common/storage-configure-connection-string) for your Azure Storage |
| container | Blob container name where your build will be uploaded |
| path | A prefix to be prepended to the apk-file. Basically this can be considered as a path, e.g. you can have something like `release/` here to upload to `release` folder.

# Usage

With the plugin installed, a set of new tasks, prefixed "azurePublish" will be added, one for each build type.

For example: to upload a debug build, run the following from terminal:

```
gradlew azurePublishDebug
```
