package li.mews.android.azurepublish

import com.android.build.gradle.api.ApplicationVariant
import com.microsoft.azure.storage.CloudStorageAccount
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class AzurePublishTask : DefaultTask() {
    lateinit var extension: AzurePublishExtension
    lateinit var appVariant: ApplicationVariant

    @TaskAction
    fun action() {
        extension.validate()
        val account = CloudStorageAccount.parse(extension.connectionString)
        val client = account.createCloudBlobClient()
        val container = client.getContainerReference(extension.container)
        container.createIfNotExists()

        val apkFile = appVariant.outputs
            .first { it.outputFile.exists() && it.outputFile.extension == "apk" }
            .outputFile

        val outputPath = File(extension.path, apkFile.name).path
        val blob = container.getBlockBlobReference(outputPath)
        blob.upload(apkFile.inputStream(), apkFile.length())

        project.logger.info("Uploaded ${apkFile.name} to ${container.name}:$outputPath")
    }
}
