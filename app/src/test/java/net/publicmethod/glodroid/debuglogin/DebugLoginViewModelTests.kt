package net.publicmethod.glodroid.debuglogin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import net.publicmethod.glodroid.*
import net.publicmethod.glodroid.debuglogin.DebugLoginConsumable.*
import net.publicmethod.glodroid.viewmodels.StateViewModel
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before

import org.junit.Rule
import org.junit.Test

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

    @Before
    fun setUp() {
        userCache = TestUserCache()
        mockWebServer = MockWebServer()
        gloService = TestGloService(
            mockWebServer
        )
        gloRepository = GloRepositoryImpl(gloService)
        viewModel = DebugLoginViewModel(userCache, gloRepository)
        viewModel.state.observeForever {
            actualState = it
        }
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
        mockWebServer.shutdown()

        // Assert
        Assert.assertEquals(expected, actual)
    }

}