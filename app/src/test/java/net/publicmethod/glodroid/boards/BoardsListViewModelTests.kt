package net.publicmethod.glodroid.boards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import net.publicmethod.glodroid.TestUserCache
import net.publicmethod.glodroid.UserCache
import net.publicmethod.glodroid.viewmodels.StateViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class BoardsListViewModelTests {

    private lateinit var userCache: UserCache

    private lateinit var viewModel: StateViewModel<BoardsListViewState, BoardsListCommands>

    private lateinit var actualState: BoardsListViewState


    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        userCache = TestUserCache()
        viewModel = BoardsListViewModel(userCache)
        viewModel.state.observeForever {
            actualState = it
        }
    }

    @Test
    fun `given Empty authCode when send then state contains consumable`() {
        // Arrange
        val input = HandleAuthenticationCode()
        val expected = Empty()

        // Act
        viewModel.send(input)
        val actual = actualState.consumable

        // Assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given debug when sending Initialize then state contains NavigateToDebugLogin`() {
        // Arrange
        val input = Initialize()
        val expected = NavigateToDebugLogin()

        // Act
        viewModel.send(input)
        val actual = actualState.consumable

        // Assert
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun `given no PAT when sending Initialize then state contains NavigateToDebugLogin`() {
        // Arrange
        val input = Initialize()
        val expected = NavigateToDebugLogin()

        // Act
        viewModel.send(input)
        val actual = actualState.consumable

        // Assert
        Assert.assertEquals(expected, actual)
    }
}