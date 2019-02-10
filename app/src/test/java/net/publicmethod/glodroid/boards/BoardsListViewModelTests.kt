package net.publicmethod.glodroid.boards

import net.publicmethod.glodroid.viewmodels.StateViewModel
import org.junit.Assert
import org.junit.Before

import org.junit.Test

class BoardsListViewModelTests {

    private lateinit var viewModel: StateViewModel

    private lateinit var actualState: BoardsListViewState

    @Before
    fun setUp() {
        viewModel = BoardsListViewModel()
    }

    @Test
    fun `given Empty authCode when send then state contains consumable`() {
        // Arrange
        val input = HandleAuthenticationCode()
        val expected = BoardsListViewState(
            input.authenticationCode,
            NavigateToLogin()
            )

        // Act
        viewModel.send(input)
        val actual: NavigateToLogin = actualState.consumable as NavigateToLogin

        // Assert
        Assert.assertEquals(expected, actual)
    }
}