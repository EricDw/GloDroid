package net.publicmethod.glodroid.debuglogin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import net.publicmethod.glodroid.TestUserCache
import net.publicmethod.glodroid.UserCache
import net.publicmethod.glodroid.generateValidPersonalAuthenticationToken
import net.publicmethod.glodroid.viewmodels.StateViewModel
import org.junit.Assert
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class DebugLoginViewModelTests {

    private lateinit var userCache: UserCache

    private lateinit var viewModel: StateViewModel<DebugLoginViewState, DebugLoginCommand>

    private lateinit var actualState: DebugLoginViewState

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        userCache = TestUserCache()
        viewModel = DebugLoginViewModel(userCache)
        viewModel.state.observeForever {
            actualState = it
        }
    }

    @Test
    fun `given invalid PAT when send ValidateToken then isLoginButtonEnabled is false`() {
        // Arrange
        val input = ValidateTokenCommand("")
        val expected = DebugLoginViewState()

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
            generateValidPersonalAuthenticationToken()
        )
        val expected = DebugLoginViewState(
            isLoginButtonEnabled = true
        )

        // Act
        viewModel.send(input)
        val actual = actualState

        // Assert
        Assert.assertEquals(expected, actual)
    }

}