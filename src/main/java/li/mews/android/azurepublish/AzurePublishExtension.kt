package li.mews.android.azurepublish

import java.lang.IllegalArgumentException

open class AzurePublishExtension {
    var connectionString: String = ""
    var container: String = ""
    var path: String = ""

    fun validate() {
        if (connectionString.isEmpty()) throw IllegalArgumentException("Provide non-empty 'connectionString' property")
        if (container.isEmpty()) throw IllegalArgumentException("Provide non-empty 'container' property")
    }
}
