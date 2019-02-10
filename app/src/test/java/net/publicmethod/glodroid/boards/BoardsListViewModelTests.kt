package net.publicmethod.glodroid.boards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import net.publicmethod.glodroid.viewmodels.StateViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class BoardsListViewModelTests {

    private lateinit var viewModel: StateViewModel<BoardsListViewState>

    private lateinit var actualState: BoardsListViewState

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = BoardsListViewModel()
        viewModel.state.observeForever {
            actualState = it
        }
    }

    @Test
    fun `given Empty authCode when send then state contains consumable`() {
        // Arrange
        val input = HandleAuthenticationCode()
        val expected = true

        // Act
        viewModel.send(input)
        val actual = actualState.consumable is NavigateToLogin

        // Assert
        Assert.assertEquals(expected, actual)
    }
}