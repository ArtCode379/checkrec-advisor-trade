package checkrec.advisor.trade.data.repository

import checkrec.advisor.trade.data.datastore.ERSIJOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ERSIJOnboardingRepo(
    private val ersijOnboardingStoreManager: ERSIJOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return ersijOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            ersijOnboardingStoreManager.setOnboardedState(state)
        }
    }
}