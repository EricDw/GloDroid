package net.publicmethod.glodroid.debuglogin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.publicmethod.glodroid.*
import net.publicmethod.glodroid.boards.NavigateToDebugLogin
import net.publicmethod.glodroid.debuglogin.DebugLoginConsumable.Empty
import net.publicmethod.glodroid.scopes.IOScope
import net.publicmethod.glodroid.scopes.UIScope
import net.publicmethod.glodroid.viewmodels.StateViewModel
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import kotlin.coroutines.CoroutineContext

class DebugLoginViewModelTests {

    private lateinit var userCache: UserCache

    private lateinit var mockWebServer: MockWebServer

    private lateinit var gloService: GloService

    private lateinit var gloRepository: GloRepository

    private lateinit var viewModel: StateViewModel<DebugLoginViewState, DebugLoginCommand>

    private lateinit var actualState: DebugLoginViewState

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        userCache = TestUserCache()
        mockWebServer = MockWebServer()
        gloService = TestGloService(
            mockWebServer
        )
        gloRepository = GloRepositoryImpl(gloService)
        viewModel = DebugLoginViewModel(
            userCache,
            gloRepository,
            object : IOScope {
                override val coroutineContext: CoroutineContext =
                    Dispatchers.Unconfined
            },
            object : UIScope {
                override val coroutineContext: CoroutineContext =
                    Dispatchers.Unconfined
            }

        )
        viewModel.state.observeForever {
            actualState = it
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `given invalid PAT when send ValidateToken then isLoginButtonEnabled is false`() {
        // Arrange
        val input = ValidateTokenCommand("")
        val expected = DebugLoginViewState(consumable = Empty)

        // Act
        viewModel.send(input)
        val actual = actualState

        // Assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given valid PAT when send ValidateToken then isLoginButtonEnabled is true`() {
        // Arrange
        val input = ValidateTokenCommand(
            generateValidPersonalAuthenticationTokenString()
        )
        val expected = DebugLoginViewState(
            isLoginButtonEnabled = true,
            consumable = Empty
        )

        // Act
        viewModel.send(input)
        val actual = actualState

        // Assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given valid PAT when send AttemptLoginCommand then NavigateToBoardsList`() {
        // Arrange
        userCache.personalAuthenticationToken = PersonalAuthenticationToken(
            generateValidPersonalAuthenticationTokenString()
        )
        val input = AttemptLogin
        val expected = DebugLoginViewState(
            consumable = NavigateToBoardsList()
        )

        // Act
        viewModel.send(input)
        val actual = actualState


        // Assert
        Assert.assertEquals(expected, actual)
    }

}