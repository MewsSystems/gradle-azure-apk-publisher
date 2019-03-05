package li.mews.android.azurepublish

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AzurePublishPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("azurePublishConfig", AzurePublishExtension::class.java)

        project.extensions.getByType(AppExtension::class.java).applicationVariants.all {
            val task = project.tasks.create("azurePublish${it.name.capitalize()}", AzurePublishTask::class.java)
            task.dependsOn(it.assembleProvider.get())
            task.description = "Upload '${it.name}' to Azure Storage"
            task.extension = extension
            task.appVariant = it
        }
    }
}
